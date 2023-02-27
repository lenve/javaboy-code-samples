package org.javaboy.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

import java.io.IOException;

/**
 * @author baize
 * @date 2023/2/17
 * @site www.qfedu.com
 */
public class LoginServer {
    Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        LoginServer server = new LoginServer();
        server.start();
        server.blockUntilShutdown();
    }

    public void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new LoginServiceImpl())
                .addService(ServerInterceptors.intercept(new HelloServiceImpl(), new AuthInterceptor()))
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LoginServer.this.stop();
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}

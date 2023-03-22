package org.javaboy.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.internal.ServerImplBuilder;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.ClientAuth;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author baize
 * @date 2023/2/17
 * @site www.qfedu.com
 */
public class LoginServer4 {
    Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        LoginServer4 server = new LoginServer4();
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
            LoginServer4.this.stop();
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

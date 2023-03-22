package org.javaboy.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
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
public class LoginServer3 {
    Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        LoginServer3 server = new LoginServer3();
        server.start();
        server.blockUntilShutdown();
    }

    public void start() throws IOException {
        int port = 50051;
        File certFile = Paths.get( "certs", "server.crt").toFile();
        File keyFile = Paths.get("certs", "server.pem").toFile();
        File caFile = Paths.get("certs", "ca.crt").toFile();
        server = NettyServerBuilder.forPort(port)
                .addService(new LoginServiceImpl())
                .addService(ServerInterceptors.intercept(new HelloServiceImpl(), new AuthInterceptor()))
                .sslContext(GrpcSslContexts.forServer(certFile,keyFile).trustManager(caFile).clientAuth(ClientAuth.REQUIRE).build())
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LoginServer3.this.stop();
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

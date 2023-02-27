package org.javaboy.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class BookServiceServer {
    Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        BookServiceServer server = new BookServiceServer();
        server.start();
        server.blockUntilShutdown();
    }

    public void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new BookServiceImpl())
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            BookServiceServer.this.stop();
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}

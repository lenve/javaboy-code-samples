package org.javaboy.server;

import io.grpc.*;

import java.io.IOException;
import java.util.Set;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

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
                .addService(ServerInterceptors.intercept(new BookServiceImpl(), new ServerInterceptor() {
                    @Override
                    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
                        System.out.println("call.getAuthority() = " + call.getAuthority());
                        String fullMethodName = call.getMethodDescriptor().getFullMethodName();
                        System.out.println(fullMethodName + ":pre");
                        Set<String> keys = headers.keys();
                        for (String key : keys) {
                            System.out.println(key + ">>>" + headers.get(Metadata.Key.of(key, ASCII_STRING_MARSHALLER)));
                        }
                        return new BookServiceCallListener<>(next.startCall(new BookServiceCall(call), headers));
                    }
                }))
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

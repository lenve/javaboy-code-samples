package org.javaboy.server;

import io.grpc.ForwardingServerCallListener;
import io.grpc.ServerCall;

public class BookServiceCallListener<R> extends ForwardingServerCallListener<R> {
    private final ServerCall.Listener<R> delegate;

    public BookServiceCallListener(ServerCall.Listener<R> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected ServerCall.Listener<R> delegate() {
        return delegate;
    }

    @Override
    public void onMessage(R message) {
        System.out.println("这是客户端发来的消息，可以在这里进行预处理："+message);
        super.onMessage(message);
    }
}

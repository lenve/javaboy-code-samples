package org.javaboy.server;

import io.grpc.ForwardingServerCall;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import org.javaboy.grpc.demo.Book;

public class BookServiceCall<ReqT,RespT> extends ForwardingServerCall.SimpleForwardingServerCall<ReqT,RespT> {
    protected BookServiceCall(ServerCall<ReqT, RespT> delegate) {
        super(delegate);
    }

    @Override
    protected ServerCall<ReqT, RespT> delegate() {
        return super.delegate();
    }

    @Override
    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return super.getMethodDescriptor();
    }

    @Override
    public void sendMessage(RespT message) {
        System.out.println("这是服务端返回给客户端的消息："+message);
        super.sendMessage(message);
    }
}

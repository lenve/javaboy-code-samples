package org.javaboy.grpc.server;

import com.google.protobuf.StringValue;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import org.javaboy.grpc.api.AuthConstant;
import org.javaboy.grpc.api.HelloServiceGrpc;

/**
 * @author baize
 * @date 2023/2/17
 * @site www.qfedu.com
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(StringValue request, StreamObserver<StringValue> responseObserver) {
        String clientId = AuthConstant.AUTH_CLIENT_ID.get();
        responseObserver.onNext(StringValue.newBuilder().setValue(clientId + " say hello:" + request.getValue()).build());
        responseObserver.onCompleted();
    }
}

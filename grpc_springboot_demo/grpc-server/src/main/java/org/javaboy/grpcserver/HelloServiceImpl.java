package org.javaboy.grpcserver;

import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.catalina.security.SecurityUtil;
import org.javaboy.grpc.api.HelloServiceGrpc;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(StringValue request, StreamObserver<StringValue> responseObserver) {
        String value = request.getValue();
        responseObserver.onNext(StringValue.newBuilder().setValue("hello " + value).build());
        responseObserver.onCompleted();
        System.out.println("==================");
    }
}

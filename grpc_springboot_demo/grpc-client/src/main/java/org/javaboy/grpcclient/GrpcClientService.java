package org.javaboy.grpcclient;

import com.google.protobuf.StringValue;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.inject.GrpcClientBean;
import org.javaboy.grpc.api.HelloServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class GrpcClientService {

//    @GrpcClient("myClient")
    @GrpcClient("grpc_server")
    HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    public void hello() {
        StringValue s = helloServiceBlockingStub.sayHello(StringValue.newBuilder().setValue("javaboy").build());
        System.out.println("s = " + s.getValue());
    }
}

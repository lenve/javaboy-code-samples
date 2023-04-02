package org.javaboy.grpcclient.controller;

import com.google.protobuf.StringValue;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.javaboy.grpc.api.HelloServiceGrpc;
import org.javaboy.grpcclient.GrpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    GrpcClientService grpcClientService;

    @GetMapping("/hello")
    public void hello() {
        grpcClientService.hello();
    }

}

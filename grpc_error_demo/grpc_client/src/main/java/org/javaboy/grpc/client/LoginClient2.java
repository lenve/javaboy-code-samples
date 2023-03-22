package org.javaboy.grpc.client;

import com.google.protobuf.StringValue;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.javaboy.grpc.api.HelloServiceGrpc;
import org.javaboy.grpc.api.LoginBody;
import org.javaboy.grpc.api.LoginResponse;
import org.javaboy.grpc.api.LoginServiceGrpc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author baize
 * @date 2023/2/17
 * @site www.qfedu.com
 */
public class LoginClient2 {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        LoginServiceGrpc.LoginServiceBlockingStub stub = LoginServiceGrpc.newBlockingStub(channel).withDeadline(Deadline.after(3, TimeUnit.SECONDS));
        login(stub);
    }

    private static void login(LoginServiceGrpc.LoginServiceBlockingStub stub) throws InterruptedException {
        try {
            LoginResponse resp = stub.withCompression("gzip").login(LoginBody.newBuilder().setUsername("javaboy").setPassword("123").build());
            System.out.println("resp.getToken() = " + resp.getToken());
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}

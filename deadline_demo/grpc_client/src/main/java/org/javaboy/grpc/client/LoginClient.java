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
public class LoginClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        LoginServiceGrpc.LoginServiceStub stub = LoginServiceGrpc.newStub(channel).withDeadline(Deadline.after(3, TimeUnit.SECONDS));
        login(stub);
//        sayHello(channel);
    }

    private static void sayHello(ManagedChannel channel) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        HelloServiceGrpc.HelloServiceStub helloServiceStub = HelloServiceGrpc.newStub(channel);
        helloServiceStub
                .withCallCredentials(new JwtCredential("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqYXZhYm95In0.IMMp7oh1dl_trUn7sn8qiv9GtO-COQyCGDz_Yy8VI4fIqUcRfwQddP45IoxNovxL"))
                .sayHello(StringValue.newBuilder().setValue("wangwu").build(), new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue stringValue) {
                System.out.println("stringValue.getValue() = " + stringValue.getValue());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("throwable.getMessage() = " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    private static void login(LoginServiceGrpc.LoginServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        stub.login(LoginBody.newBuilder().setUsername("javaboy").setPassword("123").build(), new StreamObserver<LoginResponse>() {
            @Override
            public void onNext(LoginResponse loginResponse) {
                System.out.println("loginResponse.getToken() = " + loginResponse.getToken());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("throwable = " + throwable);
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }
}

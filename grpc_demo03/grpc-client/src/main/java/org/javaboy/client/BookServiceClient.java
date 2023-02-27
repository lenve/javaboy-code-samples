package org.javaboy.client;

import com.google.protobuf.StringValue;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import org.javaboy.grpc.demo.*;

import java.util.concurrent.CountDownLatch;

public class BookServiceClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .intercept(new ClientInterceptor() {
                    @Override
                    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
                        System.out.println("!!!!!!!!!!!!!!!!");
                        callOptions = callOptions.withAuthority("javaboy");
                        return next.newCall(method,callOptions);
                    }
                })
                .build();
        BookServiceGrpc.BookServiceStub stub = BookServiceGrpc.newStub(channel);
//        addBook(stub);
//        getBook(stub);
//        searchBook(stub);
//        updateBook(stub);
        processBook(stub);
    }

    private static void processBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        StreamObserver<StringValue> request = stub.processBooks(new StreamObserver<BookSet>() {
            @Override
            public void onNext(BookSet bookSet) {
                System.out.println("bookSet = " + bookSet);
                System.out.println("=============");
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onCompleted() {
                System.out.println("处理完毕！");
                countDownLatch.countDown();
            }
        });
        request.onNext(StringValue.newBuilder().setValue("a").build());
        request.onNext(StringValue.newBuilder().setValue("b").build());
        request.onNext(StringValue.newBuilder().setValue("c").build());
        request.onNext(StringValue.newBuilder().setValue("d").build());
        request.onCompleted();
        countDownLatch.await();
    }

    private static void updateBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        StreamObserver<Book> request = stub.updateBooks(new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue stringValue) {
                System.out.println("stringValue.getValue() = " + stringValue.getValue());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("更新完毕");
                countDownLatch.countDown();
            }
        });
        request.onNext(Book.newBuilder().setId("1").setName("a").setAuthor("b").build());
        request.onNext(Book.newBuilder().setId("2").setName("c").setAuthor("d").build());
        request.onCompleted();
        countDownLatch.await();
    }

    private static void searchBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        stub.searchBooks(StringValue.newBuilder().setValue("明清小说").build(), new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
                System.out.println(book);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
                System.out.println("查询完毕！");
            }
        });
        countDownLatch.await();
    }

    private static void getBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        stub.getBook(StringValue.newBuilder().setValue("2").build(), new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
                System.out.println("book = " + book);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
                System.out.println("查询完毕");
            }
        });
        countDownLatch.await();
    }

    private static void addBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        stub.addBook(Book.newBuilder().setPrice(99).setId("100").setName("java").setAuthor("javaboy").build(), new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue stringValue) {
                System.out.println("stringValue.getValue() = " + stringValue.getValue());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
                System.out.println("添加完毕");
            }
        });
        countDownLatch.await();
    }
}

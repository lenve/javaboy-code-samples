package org.javaboy.grpc.server;

import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import io.jsonwebtoken.Jwts;
import org.javaboy.grpc.api.*;

/**
 * @author baize
 * @date 2023/2/17
 * @site www.qfedu.com
 */
public class LoginServiceImpl extends LoginServiceGrpc.LoginServiceImplBase {
    @Override
    public void login(LoginBody request, StreamObserver<LoginResponse> responseObserver) {
        String username = request.getUsername();
        String password = request.getPassword();
        if ("javaboy".equals(username) && "123".equals(password)) {
            System.out.println("login success");
            //登录成功
            String jwtToken = Jwts.builder().setSubject(username).signWith(AuthConstant.JWT_KEY).compact();
            responseObserver.onNext(LoginResponse.newBuilder().setToken(jwtToken).build());
            responseObserver.onCompleted();
        }else{
            System.out.println("login error");
            //登录失败
            responseObserver.onError(Status.UNAUTHENTICATED.withDescription("login error").asException());
//            StatusException statusException = Status.fromCodeValue(500).withDescription("").asException();
        }
    }
}

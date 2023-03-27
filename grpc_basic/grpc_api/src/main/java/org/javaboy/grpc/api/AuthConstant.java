package org.javaboy.grpc.api;

import io.grpc.Context;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

/**
 * @author baize
 * @date 2023/2/17
 * @site www.qfedu.com
 */
public interface AuthConstant {
    SecretKey JWT_KEY = Keys.hmacShaKeyFor("hello_javaboy_hello_javaboy_hello_javaboy_hello_javaboy_".getBytes());
    Context.Key<String> AUTH_CLIENT_ID = Context.key("clientId");
    String AUTH_HEADER = "Authorization";
    String AUTH_TOKEN_TYPE = "Basic";
}

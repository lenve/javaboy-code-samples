package org.javaboy.grpc.server;

import io.grpc.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.javaboy.grpc.api.AuthConstant;

import java.util.Base64;

/**
 * @author baize
 * @date 2023/2/17
 * @site www.qfedu.com
 */
public class AuthInterceptor implements ServerInterceptor {
    private JwtParser parser = Jwts.parser().setSigningKey(AuthConstant.JWT_KEY);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        String authorization = metadata.get(Metadata.Key.of(AuthConstant.AUTH_HEADER, Metadata.ASCII_STRING_MARSHALLER));
        Status status = Status.OK;
        if (authorization == null) {
            status = Status.UNAUTHENTICATED.withDescription("miss authentication token");
        } else if (!authorization.startsWith(AuthConstant.AUTH_TOKEN_TYPE)) {
            status = Status.UNAUTHENTICATED.withDescription("unknown token type");
        } else {
            try {
                String token = authorization.substring(AuthConstant.AUTH_TOKEN_TYPE.length()).trim();
                String[] split = new String(Base64.getDecoder().decode(token)).split(":");
                String username = split[0];
                String password = split[1];
                if ("javaboy".equals(username) && "123".equals(password)) {
                    Context ctx = Context.current()
                            .withValue(AuthConstant.AUTH_CLIENT_ID, username);
                    return Contexts.interceptCall(ctx, serverCall, metadata, serverCallHandler);
                }
            } catch (JwtException e) {
                status = Status.UNAUTHENTICATED.withDescription(e.getMessage()).withCause(e);
            }
        }
        serverCall.close(status, new Metadata());
        return new ServerCall.Listener<ReqT>() {
        };
    }
}

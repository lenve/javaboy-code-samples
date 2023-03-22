package org.javaboy.grpc.client;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import io.jsonwebtoken.Jwts;
import org.javaboy.grpc.api.AuthConstant;

import java.util.concurrent.Executor;

/**
 * @author baize
 * @date 2023/2/17
 * @site www.qfedu.com
 */
public class JwtCredential extends CallCredentials {
    private String subject;
    private String jwt;

    public JwtCredential(String subject) {
        this.subject = subject;
//        jwt = Jwts.builder().setSubject(subject).signWith(AuthConstant.JWT_KEY).compact();
    }

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
        executor.execute(() -> {
            try {
                Metadata headers = new Metadata();
                headers.put(Metadata.Key.of(AuthConstant.AUTH_HEADER, Metadata.ASCII_STRING_MARSHALLER),
                        String.format("%s %s", AuthConstant.AUTH_TOKEN_TYPE, subject));
                metadataApplier.apply(headers);
            } catch (Throwable e) {
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {

    }
}

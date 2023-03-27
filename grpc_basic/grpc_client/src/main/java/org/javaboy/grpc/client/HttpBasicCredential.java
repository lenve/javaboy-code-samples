package org.javaboy.grpc.client;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64Encoder;
import org.javaboy.grpc.api.AuthConstant;

import java.util.Base64;
import java.util.concurrent.Executor;

/**
 * @author baize
 * @date 2023/2/17
 * @site www.qfedu.com
 */
public class HttpBasicCredential extends CallCredentials {
    private String username;
    private String password;

    public HttpBasicCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
        executor.execute(() -> {
            try {
                String token = new String(Base64.getEncoder().encode((username + ":" + password).getBytes()));
                Metadata headers = new Metadata();
                headers.put(Metadata.Key.of(AuthConstant.AUTH_HEADER, Metadata.ASCII_STRING_MARSHALLER),
                        String.format("%s %s", AuthConstant.AUTH_TOKEN_TYPE, token));
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

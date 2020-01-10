package com.wpp.oauth2.iotdev.feign.config;

import feign.FeignException;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author wangpp
 */
public class CodecConfiguration {
    @Bean
    public Decoder decoder() {
        return new Decoder() {
            @Override
            public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
                return null;
            }
        };
    }

    @Bean
    public Encoder encoder() {
        return new Encoder() {
            @Override
            public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {

            }
        };
    }
}

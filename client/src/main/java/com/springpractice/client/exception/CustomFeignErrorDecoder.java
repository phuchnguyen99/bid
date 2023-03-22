package com.springpractice.client.exception;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        final AuctionException auctionException = extractAuctionException(response);
        switch (response.status()){
            case 400:
                log.error("Error in request  went through feign client {}", auctionException);
                return auctionException;
            case 401:
                log.error("Unauthorized Request Through Feign");
                return new Exception("Unauthorized Request through feign");
            case 404:
                log.error("Unidentified Request through Feign");
                return new Exception("Unidentified Request through feign");
            default:
                log.error("Error in request went through feign client");
                return new Exception("Common Feign Exception");
        }
    }
    private AuctionException extractAuctionException(final Response response){
        Reader reader = null;
        try{
            reader = response.body().asReader(StandardCharsets.UTF_8);
            final String result = IOUtils.toString(reader);
            final ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
            return mapper.readValue(result, AuctionException.class);
        }
        catch (IOException e){
            log.error("IO Exception on reading exception message feign client", e);
            return null;
        }
        finally {
            if(reader != null){
                IOUtils.closeQuietly(reader);
            }
        }
    }
}

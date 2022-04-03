package com.fitnessbuddy.clients.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
/*
 * Error decoder handler
 */
public class CustomErrorDecoder implements ErrorDecoder {
    /*
     * Method which look after the decoding
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        //String requestUrl = response.request().url();
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            return new ResponseStatusException(responseStatus, responseBody.toString());
        } else if (responseStatus.is4xxClientError()) {
            return new ResponseStatusException(responseStatus, responseBody.toString());
        } else {
            return new Exception("Generic exception");
        }
    }
}
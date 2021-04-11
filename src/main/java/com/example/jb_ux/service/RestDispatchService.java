package com.example.jb_ux.service;

import com.example.jb_ux.dto.Message;
import com.example.jb_ux.model.Recipient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
    public class RestDispatchService {

    public Map<String, String> makeRestDispatch(List<Recipient> recipients, String message) {

        HashMap<String, String> result = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) {
                // do nothing
            }
        });

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        //Todo use async
        for (Recipient recipient:  recipients) {

            try {
                ResponseEntity<String> resource = restTemplate.postForEntity(recipient.getValue(), new Message(message), String.class);
                result.put(recipient.getValue(), resource.getStatusCode().toString());
            } catch (IllegalArgumentException exception) {
                result.put(recipient.getValue(), "Uri is not absolute spring");
            }
        }

        return result;
    }
}

package com.example.mvd.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

import com.example.mvd.Model.PolicyModel;

@Slf4j
@RestController
@RequestMapping("/policy")
public class PolicyController {

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/submitPolicy")
    public ResponseEntity<String> submitPolicy(@Valid @RequestBody PolicyModel policyRequest) {
        log.info("Received Policy Request: {}", policyRequest);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<PolicyModel> requestEntity = new HttpEntity<>(policyRequest, headers);

        String url = "http://localhost:9191/api/management/v2/policydefinitions";

        log.info("Sending POST request to URL: {}", url);
        log.info("Request Headers: {}", headers);
        log.info("Request Body: {}", policyRequest);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error Response Body: {}", e.getResponseBodyAsString());
            throw e;
        }

        log.info("Received Response: {}", response.getBody());

        return ResponseEntity.ok(response.getBody());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/fetchPolicies")
    public ResponseEntity<String> fetchPolicies() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        String url = "http://localhost:9191/api/management/v2/policydefinitions/request";

        log.info("Sending GET request to URL: {}", url);
        log.info("Request Headers: {}", headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error Response Body: {}", e.getResponseBodyAsString());
            throw e;
        }

        log.info("Received Response: {}", response.getBody());

        return ResponseEntity.ok(response.getBody());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deletePolicy/{id}")
    public ResponseEntity<Object> deletePolicy(@PathVariable("id") String id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        String url = "http://localhost:9191/api/management/v2/policydefinitions/" + id;

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "Policy deleted successfully");
                return ResponseEntity.ok(responseBody);
            } else {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "Failed to delete policy");
                return ResponseEntity.status(response.getStatusCode()).body(responseBody);
            }
        } catch (Exception e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Error deleting policy: " + e.getMessage());
            return ResponseEntity.status(500).body(responseBody);
        }
    }
}

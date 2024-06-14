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

import com.example.mvd.Model.ContractDefinitionModel;

@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractDefinitionController {

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/submitContract")
    public ResponseEntity<String> submitContract(@Valid @RequestBody ContractDefinitionModel contractRequest) {
        log.info("Received Contract Request: {}", contractRequest);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<ContractDefinitionModel> requestEntity = new HttpEntity<>(contractRequest, headers);

        String url = "http://localhost:9191/api/management/v2/contractdefinitions";

        log.info("Sending POST request to URL: {}", url);
        log.info("Request Headers: {}", headers);
        log.info("Request Body: {}", contractRequest);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error Response Body: {}", e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }

        log.info("Received Response: {}", response.getBody());

        return ResponseEntity.ok(response.getBody());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/fetchContracts")
    public ResponseEntity<String> fetchContracts() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        String url = "http://localhost:9191/api/management/v2/contractdefinitions/request";

        log.info("Sending POST request to URL: {}", url);
        log.info("Request Headers: {}", headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error Response Body: {}", e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }

        log.info("Received Response: {}", response.getBody());

        return ResponseEntity.ok(response.getBody());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteContract/{id}")
    public ResponseEntity<Object> deleteContract(@PathVariable("id") String id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        String url = "http://localhost:9191/api/management/v2/contractdefinitions/" + id;

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "Contract deleted successfully");
                return ResponseEntity.ok(responseBody);
            } else {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "Failed to delete contract");
                return ResponseEntity.status(response.getStatusCode()).body(responseBody);
            }
        } catch (Exception e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Error deleting contract: " + e.getMessage());
            return ResponseEntity.status(500).body(responseBody);
        }
    }
}

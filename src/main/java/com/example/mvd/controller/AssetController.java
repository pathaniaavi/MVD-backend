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

import com.example.mvd.Model.AssetModel;

@Slf4j
@RestController
@RequestMapping("/asset")
public class AssetController {

    private String determinePort(String company) {
        return "company1".equals(company) ? "9191" : "9192";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/submitAsset/{company}")
    public ResponseEntity<String> submitAsset(@PathVariable String company, @Valid @RequestBody AssetModel assetRequest) {
        log.info("Received Asset Request: {}", assetRequest);
        log.info("Company: {}", company);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<AssetModel> requestEntity = new HttpEntity<>(assetRequest, headers);

        String port = determinePort(company);
        String url = "http://localhost:" + port + "/api/management/v3/assets";

        log.info("Sending POST request to URL: {}", url);
        log.info("Request Headers: {}", headers);
        log.info("Request Body: {}", assetRequest);

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
    @PostMapping("/fetchAssets/{company}")
    public ResponseEntity<String> fetchAssets(@PathVariable String company) {
        log.info("Company: {}", company);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        String port = determinePort(company);
        String url = "http://localhost:" + port + "/api/management/v3/assets/request";

        log.info("Sending POST request to URL: {}", url);
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
    @DeleteMapping("/deleteAsset/{company}/{id}")
    public ResponseEntity<Object> deleteAsset(@PathVariable("company") String company, @PathVariable("id") String id) {
        log.info("Company: {}", company);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        String port = determinePort(company);
        String url = "http://localhost:" + port + "/api/management/v3/assets/" + id;

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

            Map<String, String> responseBody = new HashMap<>();
            if (response.getStatusCode().is2xxSuccessful()) {
                responseBody.put("message", "Asset deleted successfully");
                return ResponseEntity.ok(responseBody);
            } else {
                responseBody.put("message", "Failed to delete asset");
                return ResponseEntity.status(response.getStatusCode()).body(responseBody);
            }
        } catch (Exception e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Error deleting asset: " + e.getMessage());
            return ResponseEntity.status(500).body(responseBody);
        }
    }
}

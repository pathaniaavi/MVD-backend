package com.example.mvd.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

import com.example.mvd.Model.CatalogModel;

@Slf4j
@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private String determinePort(String company) {
        return "company1".equals(company) ? "9191" : "9192";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/fetchCatalog/{company}")
    public ResponseEntity<String> fetchCatalog(@PathVariable String company, @Valid @RequestBody CatalogModel catalogRequest) {
        log.info("Received Catalog Request: {}", catalogRequest);
        log.info("Company: {}", company);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "ApiKeyDefaultValue");

        HttpEntity<CatalogModel> requestEntity = new HttpEntity<>(catalogRequest, headers);

        String port = determinePort(company);
        String url = "http://localhost:" + port + "/api/management/v2/catalog/request";

        log.info("Sending POST request to URL: {}", url);
        log.info("Request Headers: {}", headers);
        log.info("Request Body: {}", catalogRequest);

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
}

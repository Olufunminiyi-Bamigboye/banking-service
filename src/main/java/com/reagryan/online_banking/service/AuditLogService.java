package com.reagryan.online_banking.service;

import com.reagryan.online_banking.dto.request.AuditLogRequest;
import com.reagryan.online_banking.dto.request.MetaDataRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuditLogService {

    private final RestTemplate restTemplate;

    public AuditLogService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${tracebit.base.url}")
    private String baseUrl;

    @Value("${tracebit.api.key}")
    private String apiKey;

    public ApiResponse submitAuditLog(AuditLogRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("X-TRACEBIT-KEY", apiKey);

        HttpEntity<AuditLogRequest> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ApiResponse(false, "Audit log created successfully", null);
    }
}

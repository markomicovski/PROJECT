package com.example.demo;

import com.example.renovationtracker.model.Budget;
import com.example.renovationtracker.model.Contractor;
import com.example.renovationtracker.model.Project;
import com.example.renovationtracker.DemoApplication; // Import the main application class
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class) // Specify the main application class
public class ApiIntegrationTestUpdated {

    private static final Logger logger = LoggerFactory.getLogger(ApiIntegrationTestUpdated.class);

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/api"; // Adjust the port if necessary

    @Test
    public void testGetAllBudgets() {
        ResponseEntity<List<Budget>> response = restTemplate.getForEntity(BASE_URL + "/budgets", (Class<List<Budget>>) (Object) List.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Fetched budgets successfully: {}", response.getBody());
        } else {
            logger.error("Failed to fetch budgets: {}", response.getStatusCode());
        }
    }

    @Test
    public void testGetAllContractors() {
        ResponseEntity<List<Contractor>> response = restTemplate.getForEntity(BASE_URL + "/contractors", (Class<List<Contractor>>) (Object) List.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Fetched contractors successfully: {}", response.getBody());
        } else {
            logger.error("Failed to fetch contractors: {}", response.getStatusCode());
        }
    }

    @Test
    public void testGetAllProjects() {
        ResponseEntity<List<Project>> response = restTemplate.getForEntity(BASE_URL + "/projects", (Class<List<Project>>) (Object) List.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Fetched projects successfully: {}", response.getBody());
        } else {
            logger.error("Failed to fetch projects: {}", response.getStatusCode());
        }
    }
}

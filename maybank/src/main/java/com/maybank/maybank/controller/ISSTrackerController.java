package com.maybank.maybank.controller;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.maybank.maybank.beans.IssPosition;
import com.maybank.maybank.beans.IssResponse;

/**
 * 
 * @author Austin Teck
 * Third party API call
 *
 */
@RestController
@RequestMapping("/iss")
public class ISSTrackerController {
	
	private static final Logger logger = Logger.getLogger(ISSTrackerController.class);

    @GetMapping("/location")
    public ResponseEntity<IssPosition> getISSLocation(HttpServletRequest request) {
        String uri = "http://api.open-notify.org/iss-now.json";
        RestTemplate restTemplate = new RestTemplate();
        IssResponse response = restTemplate.getForObject(uri, IssResponse.class);
        IssPosition position = response.getIssPosition();
        
        logger.info("REQUEST: " + request.getRequestURI());
        logger.info("RESPONSE: " + position.toString());
        return ResponseEntity.ok().body(position);
    }
}
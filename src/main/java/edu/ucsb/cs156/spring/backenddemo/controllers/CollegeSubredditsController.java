package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.CollegeSubredditQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Tag(name="College Subreddits from https://github.com/karlding/college-subreddits/")
@RestController
@RequestMapping("/api/collegesubreddits")
public class CollegeSubredditsController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CollegeSubredditQueryService collegeSubredditQueryService;

    @Operation(summary = "Get a list of college subreddits")
    @GetMapping("/get")
    public ResponseEntity<String> getSubreddits() throws JsonProcessingException {
        String result = collegeSubredditQueryService.getJSON();
        return ResponseEntity.ok().body(result);
    }

}
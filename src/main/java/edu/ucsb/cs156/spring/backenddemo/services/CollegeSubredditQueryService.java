package edu.ucsb.cs156.spring.backenddemo.services;

import java.io.StringReader;
import java.util.List;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.web.client.RestTemplate;

import edu.ucsb.cs156.spring.backenddemo.beans.CollegeSubreddit;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class CollegeSubredditQueryService {

    private ObjectMapper mapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    public CollegeSubredditQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "https://raw.githubusercontent.com/karlding/college-subreddits/master/data/colleges.csv";

    public String getJSON() throws HttpClientErrorException, JsonProcessingException {
       
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.TEXT_PLAIN));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class);
        String csvData =  re.getBody();
        List<CollegeSubreddit> subreddits = new CsvToBeanBuilder<CollegeSubreddit>(new StringReader(csvData)).withType(CollegeSubreddit.class).build().parse();
        String jsonData = mapper.writeValueAsString(subreddits);
        return jsonData;
    }

}
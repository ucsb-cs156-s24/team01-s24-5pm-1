package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Public Holiday Info from https://date.nager.at/Api")
@Slf4j
@RestController
@RequestMapping("/api/publicholidays")

public class PublicHolidaysController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService publicholidayQueryService;

    @Operation(summary = "Get public holidays for a given year and country", description = "JSON return format documented here: https://date.nager.at/api/v2/publicholidays/")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHoliday(
        @Parameter(name="year", description="2 letter country code, e.g. US, MX, CN", example="US") @RequestParam String year,
        @Parameter(name="countryCode", description="year, e.g. 2024", example="2024") @RequestParam String countryCode
    ) throws JsonProcessingException {
        log.info("getPublicHoliday: year={} countryCode={}", year, countryCode);
        String result = publicholidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }
}

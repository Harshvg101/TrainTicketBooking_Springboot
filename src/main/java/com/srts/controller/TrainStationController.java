package com.srts.controller;

import com.srts.service.CSVFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/train-stations")
public class TrainStationController {

    @Autowired
    private CSVFileService csvFileService;

    // API to upload CSV file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadTrainStations(@RequestParam("file") MultipartFile file) {
        csvFileService.importTrainStations(file);
        return ResponseEntity.ok("Train stations imported successfully.");
    }
}

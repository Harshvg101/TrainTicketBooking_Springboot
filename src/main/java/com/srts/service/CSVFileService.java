package com.srts.service;

import com.srts.entity.TrainStation;
import com.srts.exception.InvalidFileContentException;
import com.srts.repository.TrainStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

@Service
public class CSVFileService {

    @Autowired
    private TrainStationRepository trainStationRepository;

    public void importTrainStations(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            List<TrainStation> stations = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 2) {
                    throw new InvalidFileContentException("Invalid CSV format: " + line);
                }
                TrainStation station = new TrainStation();
                station.setName(data[0]);
                station.setStopNumber(Integer.parseInt(data[1]));
                stations.add(station);
            }
            trainStationRepository.saveAll(stations);
        } catch (Exception e) {
            throw new InvalidFileContentException("Error processing file: " + e.getMessage());
        }
    }
}

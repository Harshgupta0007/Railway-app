package com.railway.railway.service;



import com.railway.railway.dto.TrainRequest;
import com.railway.railway.model.Train;
import com.railway.railway.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private static final String ADMIN_API_KEY = "AdminSecretKey";

    @Autowired
    private TrainRepository trainRepository;

    public ResponseEntity<?> addTrain(TrainRequest request, String apiKey) {
        if (!ADMIN_API_KEY.equals(apiKey)) {
            return ResponseEntity.status(403).body("Invalid API Key");
        }

        Train train = new Train();
        train.setName(request.getName());
        train.setSource(request.getSource());
        train.setDestination(request.getDestination());
        train.setTotalSeats(request.getTotalSeats());
        train.setAvailableSeats(request.getTotalSeats());

        trainRepository.save(train);

        return ResponseEntity.ok("Train added successfully");
    }

    public ResponseEntity<?> updateTrain(Long trainId, TrainRequest request, String apiKey) {
        if (!ADMIN_API_KEY.equals(apiKey)) {
            return ResponseEntity.status(403).body("Invalid API Key");
        }

        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found"));

        train.setName(request.getName());
        train.setSource(request.getSource());
        train.setDestination(request.getDestination());
        train.setTotalSeats(request.getTotalSeats());
        train.setAvailableSeats(request.getTotalSeats());

        trainRepository.save(train);

        return ResponseEntity.ok("Train updated successfully");
    }
}

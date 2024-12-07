package com.railway.railway.controller;



import com.railway.railway.dto.TrainRequest;
import com.railway.railway.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-train")
    public ResponseEntity<?> addTrain(@RequestBody TrainRequest request, @RequestHeader("API-Key") String apiKey) {
        return adminService.addTrain(request, apiKey);
    }

    @PutMapping("/update-train/{trainId}")
    public ResponseEntity<?> updateTrain(@PathVariable Long trainId, @RequestBody TrainRequest request, @RequestHeader("API-Key") String apiKey) {
        return adminService.updateTrain(trainId, request, apiKey);
    }
}

package com.railway.railway.dto;


import lombok.Data;

@Data
public class TrainRequest {
    private String name;
    private String source;
    private String destination;
    private int totalSeats;
}

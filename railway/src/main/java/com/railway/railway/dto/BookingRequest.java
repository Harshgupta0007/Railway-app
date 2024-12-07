package com.railway.railway.dto;



import lombok.Data;

@Data
public class BookingRequest {
    private Long trainId;
    private String source;
    private String destination;
}

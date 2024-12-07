package com.railway.railway.controller;



import com.railway.railway.dto.BookingRequest;
import com.railway.railway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/trains")
    public ResponseEntity<?> getTrains(@RequestParam String source, @RequestParam String destination) {
        return userService.getTrains(source, destination);
    }

    @PostMapping("/book-seat")
    public ResponseEntity<?> bookSeat(@RequestBody BookingRequest request, @RequestHeader("Authorization") String token) {
        return userService.bookSeat(request, token);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getBookingDetails(@PathVariable Long bookingId, @RequestHeader("Authorization") String token) {
        return userService.getBookingDetails(bookingId, token);
    }
}


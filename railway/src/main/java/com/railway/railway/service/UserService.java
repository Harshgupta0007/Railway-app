package com.railway.railway.service;



import com.railway.railway.dto.BookingRequest;
import com.railway.railway.model.Booking;
import com.railway.railway.model.Train;
import com.railway.railway.model.User;
import com.railway.railway.repository.BookingRepository;
import com.railway.railway.repository.TrainRepository;
import com.railway.railway.repository.UserRepository;
import com.railway.railway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<?> getTrains(String source, String destination) {
        List<Train> trains = trainRepository.findBySourceAndDestination(source, destination);
        if (trains.isEmpty()) {
            return ResponseEntity.ok("No trains available for this route");
        }
        return ResponseEntity.ok(trains);
    }

    @Transactional
    public ResponseEntity<?> bookSeat(BookingRequest request, String token) {
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Train train = trainRepository.findById(request.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        if (train.getAvailableSeats() <= 0) {
            return ResponseEntity.badRequest().body("No seats available on this train");
        }

        synchronized (this) {
            if (train.getAvailableSeats() > 0) {
                train.setAvailableSeats(train.getAvailableSeats() - 1);
                trainRepository.save(train);

                Booking booking = new Booking();
                booking.setUser(user);
                booking.setTrain(train);
                booking.setSource(request.getSource());
                booking.setDestination(request.getDestination());
                booking.setStatus("CONFIRMED");

                bookingRepository.save(booking);

                return ResponseEntity.ok("Booking successful: " + booking.getId());
            } else {
                return ResponseEntity.badRequest().body("No seats available on this train");
            }
        }
    }

    public ResponseEntity<?> getBookingDetails(Long bookingId, String token) {
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).body("Access denied");
        }

        return ResponseEntity.ok(booking);
    }
}


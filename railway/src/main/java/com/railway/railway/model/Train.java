package com.railway.railway.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String source;

    private String destination;

    private int totalSeats;

    private int availableSeats;

}

package com.nagarro.hotel.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "micro_hotels")
public class Hotel {
    @Id
    private String hotelId;
    private String name;
    private String location;
    private String pin;
    private String about;



}

package com.nagarro.userservice.entities;


import lombok.*;

import javax.persistence.Entity;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rate;
    private String feedback;
    private Hotel hotel;
}








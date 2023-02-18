package com.nagarro.rating.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "micro_rating")
public class Rating {

    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rate;
    private String feedback;
}

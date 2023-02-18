package com.nagarro.userservice.entities;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String about;

    @Transient  // It will not store in DB...
    private List<Rating> ratings = new ArrayList<>();
}

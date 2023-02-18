package com.nagarro.hotel.controllers;

import com.nagarro.hotel.entities.Hotel;
import com.nagarro.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // create hotel

    @PostMapping
    private ResponseEntity<Hotel> createNewHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    // find all hotel

    @GetMapping
    private ResponseEntity<List<Hotel>> findAllHotels(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotel());
    }

    //find single hotel

    @GetMapping("/{hotelId}")
    private ResponseEntity<Hotel> findHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
    }



}

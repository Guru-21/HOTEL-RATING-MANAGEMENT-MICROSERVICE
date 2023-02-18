package com.nagarro.hotel.services;

import com.nagarro.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create hotel
    Hotel createHotel(Hotel hotel);

    //get all hotel
    List<Hotel> getAllHotel();

    //get single hotel
    Hotel getHotel(String hotelId);


}

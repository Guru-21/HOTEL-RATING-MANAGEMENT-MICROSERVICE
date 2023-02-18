package com.nagarro.hotel.services.impl;

import com.nagarro.hotel.entities.Hotel;
import com.nagarro.hotel.exceptions.ResourceNotFoundException;
import com.nagarro.hotel.repositories.HotelRepository;
import com.nagarro.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
      String hotelId = UUID.randomUUID().toString();
      hotel.setHotelId(hotelId);
      return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Resource is not found with given id: " + hotelId));
    }
}

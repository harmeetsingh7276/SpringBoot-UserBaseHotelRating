package com.lcwd.hotel.services.impl;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.repositories.HotelRepository;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found!!"));
    }

    @Override
    public void deleteHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    @Override
    public Hotel updateHotel(String hotelId, Hotel hotel) {
        Hotel hotelData=hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException());
        hotelData.setName(hotel.getName());
        hotelData.setLocation(hotel.getLocation());
        hotelData.setAbout(hotel.getAbout());
        hotelRepository.save(hotelData);
        return hotelData;
    }
}

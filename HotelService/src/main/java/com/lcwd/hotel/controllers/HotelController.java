package com.lcwd.hotel.controllers;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }
    //get single
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }
    //get all
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    }

    //delete
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable String hotelId){
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body("Hotel Deleted:"+hotelId);
    }
    //update
    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId,@RequestBody Hotel hotel){
        return ResponseEntity.ok(hotelService.updateHotel(hotelId,hotel));
    }
}

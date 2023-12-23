package com.project.findPg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.findPg.Entities.Room;
import com.project.findPg.services.RoomService;

import java.util.List;


@RestController
@RequestMapping(path ="/api/room")
@CrossOrigin
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	@PostMapping("/add")
	public Room addRoom(@RequestBody Room room) {
		
	    return 	roomService.addRoom(room);
	}

	@GetMapping("/getAll")
	public List<Room> getRooms(){
		return roomService.getRooms();
	}
	
	@GetMapping("/getById/{roomId}")
	public Room getRoom(@PathVariable int roomId) {
		return roomService.getRoom(roomId);
	}
	
	@GetMapping("/getByCapacity/{capacity}")
	public Room findRoomByCapacity(@PathVariable int capacity) {
		return roomService.findRoomByCapacity(capacity);
	}

	@GetMapping("/getByCity/{city}")
	public Room findRoomByCity(@PathVariable String city) {
		return roomService.findRoomByCity(city);
	}
	@GetMapping("/getByPriceRange/{roomPrice}")
	public List<Room> findRoomByRoomPriceLesserThan(@PathVariable("roomPrice") float roomPrice) {
		return roomService.findRoomByRoomPriceLessThan(roomPrice);
	}
	
	@DeleteMapping("/deleteById/{roomId}")
	public void deleteRoom(@PathVariable int roomId) {
	     roomService.deleteRoom(roomId);
    }
	
	@PutMapping("/update/{roomId}")
	public Room updateRoom(@PathVariable int roomId, @RequestBody Room room) {
		room.setRoomId(roomId);
	    return 	roomService.updateRoom(room,roomId);
	}
}

package com.project.findPg.services;

import java.util.List;

import com.project.findPg.Entities.Room;

public interface RoomService {
	Room addRoom(Room room);
	List<Room> getRooms();
	Room getRoom(int roomId);
	void deleteRoom(int roomId);
    Room updateRoom(Room room,int roomId);
    
    //custom method
    Room findRoomByCity(String city);
	Room findRoomByCapacity(int capacity);
	List<Room> findRoomByRoomPriceLessThan(float roomPrice);

}

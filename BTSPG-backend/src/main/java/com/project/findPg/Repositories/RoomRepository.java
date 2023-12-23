package com.project.findPg.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.findPg.Entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

// custom method	
	Room findRoomByCity(String city);
	Room findRoomByCapacity(int capacity);
	List<Room> findRoomByRoomPriceLessThan(float roomPrice);
}

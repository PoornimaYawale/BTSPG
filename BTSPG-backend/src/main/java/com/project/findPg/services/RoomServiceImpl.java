package com.project.findPg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.findPg.Entities.Room;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyInputFieldException;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Repositories.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Override
	public Room addRoom(Room room) {
		if (room.getRoomDescription().isBlank()) {
			throw new EmptyInputFieldException();
		}
		return roomRepository.save(room);
	}
 
	@Override
	public List<Room> getRooms() {
		List<Room> rooms = roomRepository.findAll();
		if (rooms.isEmpty()) {
			throw new EmptyListException("room list is empty , please add room !!!");
		} else {
			return rooms;
		}
	}

	@Override
	public Room getRoom(int roomId) {

		Optional<Room> room = roomRepository.findById(roomId);
		if (room.isPresent()) {
			return room.get();
		}
		throw new DetailNotFoundException("Room detials doesnt exist in the DB for roomid", roomId);

	}

	@Override
	public void deleteRoom(int roomId) {
//		if (roomRepository.findById(roomId).isPresent()) {
//
//			roomRepository.deleteById(roomId);
//		} else {
//			throw new DetailNotFoundException("Room detials doesnt exist in the DB for roomid", roomId);
//		}

		Room room = getRoom(roomId);
		roomRepository.delete(room);
	}

	@Override
	public Room updateRoom(Room room, int roomId) {

		Room updatedRoom = getRoom(roomId);

		updatedRoom.setRoomDescription(room.getRoomDescription());
		updatedRoom.setCapacity(room.getCapacity());
		updatedRoom.setAddress(room.getAddress());
		updatedRoom.setCity(room.getCity());
		updatedRoom.setImage(room.getImage());
		updatedRoom.setRoomPrice(room.getRoomPrice());

		return roomRepository.save(updatedRoom);
	}

	@Override
	public Room findRoomByCity(String city) {

		return roomRepository.findRoomByCity(city);
	}

	@Override
	public Room findRoomByCapacity(int capacity) {
		return roomRepository.findRoomByCapacity(capacity);
	}


	@Override
	public List<Room> findRoomByRoomPriceLessThan(float roomPrice) {
		return roomRepository.findRoomByRoomPriceLessThan(roomPrice);
	}
}

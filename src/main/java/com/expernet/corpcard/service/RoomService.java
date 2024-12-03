package com.expernet.corpcard.service;

import java.util.HashMap;
import java.util.List;

import com.expernet.corpcard.dto.main.RoomDTO;
import com.expernet.corpcard.dto.main.RoomListDTO;
import com.expernet.corpcard.entity.Room;

public interface RoomService {
	List<Room> roomList (RoomListDTO.Request param);
	
	Room insertRoomInfo(RoomDTO.Request params);
	
	Room selectRoom(RoomDTO.selectRequest params);
	
	Room updateRoomInfo(RoomDTO.udRequest params);
	
	void deleteRoomInfo(long seq);
	
	List<Room> myRoomList (String regId);
}

package com.expernet.corpcard.service.Impl;


import com.expernet.corpcard.dto.main.RoomDTO;
import com.expernet.corpcard.dto.main.RoomDTO.selectRequest;
import com.expernet.corpcard.dto.main.RoomDTO.udRequest;
import com.expernet.corpcard.dto.main.RoomListDTO;
import com.expernet.corpcard.entity.Room;
import com.expernet.corpcard.repository.RoomRepository;
import com.expernet.corpcard.repository.UserRepository;
import com.expernet.corpcard.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roomService")
public class RoomServiceImpl implements RoomService {
	 /**
     * 사용자 정보 Repository
     */
    @Autowired
    private UserRepository userRepository;
    
    /**
     * 방 정보 Repository
     */
    @Autowired
    private RoomRepository roomRepository;
    
    @Override
    public List<Room> roomList (RoomListDTO.Request param){
    	List<Room> list = roomRepository.findAllByRoomQuery();
    	if((param.getRoomType() != null && !param.getRoomType().equals(""))
    			&& (param.getRentType() != null && !param.getRentType().equals(""))
    			&& (param.getRentPrice1() >= 0 && param.getRentPrice2() >= 0)) {
    		list = roomRepository.findRoomTypeAndRentTypeAndRentPriceByRoomQuery(param.getRoomType(), param.getRentType(), param.getRentPrice1(), param.getRentPrice2());
    	}
    	else if((param.getRoomType() != null && !param.getRoomType().equals(""))
    			&& (param.getRentType() != null && !param.getRentType().equals(""))) {
    		list = roomRepository.findRoomAndRentTypeByRoomQuery(param.getRoomType(), param.getRentType());
    	}
    	else if((param.getRoomType() != null && !param.getRoomType().equals(""))
    			&& (param.getRentPrice1() >= 0 && param.getRentPrice2() >= 0)) {
    		list = roomRepository.findRoomTypeAndRentPriceByRoomQuery(param.getRoomType(), param.getRentPrice1(), param.getRentPrice2());
    	}
    	else if((param.getRentType() != null && !param.getRentType().equals(""))
    			&& (param.getRentPrice1() >= 0 && param.getRentPrice2() >= 0)) {
    		list = roomRepository.findRentTypeAndRentPriceByRoomQuery(param.getRentType(), param.getRentPrice1(), param.getRentPrice2());
    	}
    	else if((param.getRoomType() != null && !param.getRoomType().equals(""))
    			&& (param.getRentType() == null || param.getRentType().equals(""))
    			&& (param.getRentPrice1() < 0 || param.getRentPrice2() < 0)) {
    		list = roomRepository.findRoomTypeByRoomQuery(param.getRoomType());
    	}
    	else if((param.getRentType() != null && !param.getRentType().equals(""))
    			&& (param.getRoomType() == null || param.getRoomType().equals(""))
    			&& (param.getRentPrice1() < 0 || param.getRentPrice2() < 0)) {
    		list = roomRepository.findRentTypeByRoomQuery(param.getRentType());
    	}
    	else if((param.getRentType() == null || param.getRentType().equals(""))
    			&& (param.getRoomType() == null || param.getRoomType().equals(""))
    			&& (param.getRentPrice1() >= 0 && param.getRentPrice2() >= 0)) {
    		list = roomRepository.findRentPriceByRoomQuery(param.getRentPrice1(), param.getRentPrice2());
    	}
    	else {
    		list = roomRepository.findAllByRoomQuery();
    	}
    	return list;
    }

    @Override
	@Transactional
    public Room insertRoomInfo(RoomDTO.Request params) {
    	Room result = null;
    	
    	//방 정보 저장 or 수정
    	Room room = Room.builder()
                .rentType(params.getRentType())
                .roomType(params.getRoomType())
                .deposit(params.getDeposit())
                .rentPrice(params.getRentPrice())
                .roomNm(params.getRoomNm())
                .build();
        result = roomRepository.save(room);
        return result;
    }

	@Override
	public Room selectRoom(selectRequest params) {
		// TODO Auto-generated method stub
		return roomRepository.findRoomTypeSeqByRoomQuery(params.getSeq());
	}

	@Override
	@Transactional
	public Room updateRoomInfo(udRequest params) {
		Room result = null;
		
    	//방 정보 저장 or 수정
    	Room room = Room.builder()
    			.seq(params.getSeq())
                .rentType(params.getRentType())
                .roomType(params.getRoomType())
                .deposit(params.getDeposit())
                .rentPrice(params.getRentPrice())
                .roomNm(params.getRoomNm())
                .udtId(params.getUdtId())
                .build();
        result = roomRepository.save(room);
        return result;
	}

	@Override
	@Transactional
	public void deleteRoomInfo(long seq) {
		roomRepository.deleteById(seq);
	}

	@Override
	public List<Room> myRoomList(String regId) {
		// TODO Auto-generated method stub
		return roomRepository.findRegIdByRoomQuery(regId);
	}
}

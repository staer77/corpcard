package com.expernet.corpcard.repository;

import com.expernet.corpcard.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    
    @Query(value = "SELECT tr.SEQ, tr.ROOM_NM, tr.DEPOSIT, tr.RENT_PRICE, tr.REG_DT, tr.UDT_DT, tr.REG_ID, tr.UDT_ID" + 
    				", CASE WHEN tr.ROOM_TYPE = '1' THEN '원룸'" + 
    				"		WHEN tr.ROOM_TYPE = '2' THEN '투룸'" + 
    				"		WHEN tr.ROOM_TYPE = '3' THEN '쓰리룸'" + 
    				"		ELSE '' END AS ROOM_TYPE " + 
    				", CASE WHEN tr.RENT_TYPE = '1' THEN '월세'" + 
    				"		WHEN tr.RENT_TYPE = '2' THEN '전세'" + 
    				"		ELSE '' END AS RENT_TYPE " + 
    				"FROM tb_room tr ", nativeQuery = true)
    List<Room> findAllByRoomQuery();
    
    @Query(value = "SELECT * FROM tb_room tr " + 
    				"WHERE ROOM_TYPE = :roomType", nativeQuery = true)
    List<Room> findRoomTypeByRoomQuery(@Param("roomType")String roomType);
    
    @Query(value = "SELECT * FROM tb_room tr " + 
    		"WHERE RENT_TYPE = :rentType", nativeQuery = true)
    List<Room> findRentTypeByRoomQuery(@Param("rentType")String rentType);
    
    @Query(value = "SELECT * FROM tb_room tr " +
    		"WHERE RENT_PRICE >= :rentPrice1 AND RENT_PRICE <= :rentPrice2", nativeQuery = true)
    List<Room> findRentPriceByRoomQuery(@Param("rentPrice1")long rentPrice1, @Param("rentPrice2")long rentPrice2);
    
    @Query(value = "SELECT * FROM tb_room tr " +
    		"WHERE ROOM_TYPE = :roomType " +
    		"AND RENT_TYPE = :rentType", nativeQuery = true)
    List<Room> findRoomAndRentTypeByRoomQuery(@Param("roomType")String roomType, @Param("rentType")String rentType);
    
    @Query(value = "SELECT * FROM tb_room tr " +
    		"WHERE ROOM_TYPE = :roomType " +
    		"AND (RENT_PRICE >= :rentPrice1 AND RENT_PRICE <= :rentPrice2)", nativeQuery = true)
    List<Room> findRoomTypeAndRentPriceByRoomQuery(@Param("roomType")String roomType, @Param("rentPrice1")long rentPrice1, @Param("rentPrice2")long rentPrice2);
    
    @Query(value = "SELECT * FROM tb_room tr " +
    		"WHERE RENT_TYPE = :rentType " +
    		"AND (RENT_PRICE >= :rentPrice1 AND RENT_PRICE <= :rentPrice2)", nativeQuery = true)
    List<Room> findRentTypeAndRentPriceByRoomQuery(@Param("rentType")String rentType, @Param("rentPrice1")long rentPrice1, @Param("rentPrice2")long rentPrice2);
    
    @Query(value = "SELECT * FROM tb_room tr " +
    		"WHERE ROOM_TYPE = :roomType " +
    		"AND RENT_TYPE = :rentType " +
    		"AND (RENT_PRICE >= :rentPrice1 AND RENT_PRICE <= :rentPrice2)", nativeQuery = true)
    List<Room> findRoomTypeAndRentTypeAndRentPriceByRoomQuery(@Param("roomType")String roomType, @Param("rentType")String rentType, @Param("rentPrice1")long rentPrice1, @Param("rentPrice2")long rentPrice2);
    
    @Query(value = "SELECT * FROM tb_room tr " +
    		"WHERE SEQ = :seq ", nativeQuery = true)
    Room findRoomTypeSeqByRoomQuery(@Param("seq")long seq);
    
    @Query(value = "SELECT * FROM tb_room tr " +
    		"WHERE REG_ID = :regId ", nativeQuery = true)
    List<Room> findRegIdByRoomQuery(@Param("regId")String regId);
}

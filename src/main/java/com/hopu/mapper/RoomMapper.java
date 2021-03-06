package com.hopu.mapper;

import com.hopu.domain.History;
import com.hopu.domain.Room;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMapper {
    List<Room> findAll();

    void save(Room room);

    @Select("SELECT * FROM t_room WHERE id =#{id}")
    Room findById(Integer id);

    void update(Room room);

    List<Room> findAllByIds(List<Integer> roomIdList);

    @Delete("DELETE FROM t_room WHERE id =#{id}")
    void deleteById(Integer id);

    @Update("UPDATE t_room SET rent_status =#{rentStatus} WHERE id =#{id}")
    void updateRoom(@Param("id") Integer id, @Param("rentStatus") int rentStatus);

    List<Room> findAllFront(@Param("rentStatus") Integer rentStatus,@Param("regionId") Integer regionId,@Param("beginRent") int beginRent,@Param("endRent") int endRent);

    @Select("select * from t_history h join t_room r on h.room_id = r.id  where h.h_user_id = #{userId}")
    List<Room> findRoomByHistoryUser(@Param("userId")Integer userId);

    @Select("select * from t_history where h_user_id = #{userId} order by hh_date desc, hh_id desc")
    List<History> findHistoryByUserId(Integer userId);

    @Select("select * from t_history where room_id = #{roomId} and h_user_id = #{userId}")
    History findHistoryByUserIdAndRoomId(@Param("roomId")Integer roomId ,@Param("userId")Integer userId);

    @Update("update t_history set hh_date = NOW() where hh_id = #{hhId}")
    void updateHistoryByHhid(Long hhId);

    @Delete("delete from t_history where hh_id = #{hhId}")
    void deleteHistory(Long hhId);
}

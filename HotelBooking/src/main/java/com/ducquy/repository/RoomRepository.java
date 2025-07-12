package com.ducquy.repository;

import com.ducquy.entitys.Room;
import com.ducquy.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> { // Repository cho thực thể Room

    /**
     * Tìm các phòng còn trống trong khoảng thời gian đặt phòng.
     *
     * @param checkInDate Ngày nhận phòng
     * @param checkOutDate Ngày trả phòng
     * @param roomType Loại phòng (nếu có, nếu không thì tìm tất cả các loại phòng)
     * @return Danh sách các phòng còn trống
     */
    @Query("""
        SELECT r FROM Room r
        WHERE
                r.id NOT IN (
                SELECT b.room.id
                FROM Booking b
                WHERE :checkInDate <= b.checkOutDate
                AND :checkOutDate >= b.checkInDate
                AND b.bookingStatus IN ('BOOKED', 'CHECKED_IN')
            )
            AND (:roomType IS NULL OR r.type = :roomType)
        """)
    // Truy vấn để tìm các phòng còn trống trong khoảng thời gian đặt phòng
    List<Room> findAvailableRooms( // Tìm các phòng còn trống trong khoảng thời gian đặt phòng
            @Param("checkInDate") LocalDate checkInDate, // Ngày nhận phòng
            @Param("checkOutDate") LocalDate checkOutDate, // Ngày trả phòng
            @Param("roomType") RoomType roomType // Loại phòng (nếu có, nếu không thì tìm tất cả các loại phòng)w
    );

    /**
     * Tìm các phòng theo tham số tìm kiếm.
     *
     * @param searchParam Tham số tìm kiếm (có thể là số phòng, loại phòng, giá mỗi đêm, sức chứa hoặc mô tả)
     * @return Danh sách các phòng phù hợp với tham số tìm kiếm
     */
    @Query ("""
        SELECT r FROM Room r
        WHERE CAST (r.roomNumber AS string) LIKE %:searchParam%
        OR LOWER (r.type) LIKE LOWER(:searchParam)
        OR CAST (r.pricePerNight AS string) LIKE %:searchParam%
        OR CAST (r.capacity AS string) LIKE %:searchParam%
        OR LOWER (r.description) LIKE LOWER(CONCAT('%', :searchParam, '%'))
        """)
    // Truy vấn để tìm các phòng theo tham số tìm kiếm
    List<Room> searchRooms(@Param("searchParam") String searchParam); // Tìm các phòng theo loại phòng

}

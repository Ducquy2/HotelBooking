package com.ducquy.repository;

import com.ducquy.entitys.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long > { // Repository cho thực thể Booking

    List<Booking> findByUserId(Long userId); // Tìm các đặt phòng theo ID người dùng

    Optional<Booking> findByBookingReference(String bookingReference); // Tìm đặt phòng theo mã tham chiếu

    @Query("""
                SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
                FROM Booking b
                WHERE b.room.id = :roomId
                AND b.checkInDate <= :checkOutDate
                AND b.checkOutDate >= :checkInDate
                AND b.bookingStatus IN ('BOOKED', 'CHECKED_IN')
        """)
    Boolean isRoomAvailable(@Param("roomId") Long roomId,
                            @Param("checkInDate") LocalDate checkInDate,
                            @Param("checkOutDate") LocalDate checkOutDate
    ); // Kiểm tra xem phòng có còn trống trong khoảng thời gian đặt phòng hay không

}

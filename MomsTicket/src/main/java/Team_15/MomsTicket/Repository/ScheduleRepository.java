package Team_15.MomsTicket.Repository;

import Team_15.MomsTicket.Entity.Schedule;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    // 특정 월에 스케줄이 있는 날짜 리스트 반환
    @Query(value =
            "SELECT * " +
                    "FROM Schedule " +
                    "WHERE idolID = :idolID " +
                    "AND scheduleDate BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Schedule> findDatesWithSchedules(
            @Param("idolID") Integer idolID,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate);

    // 특정 날짜에 해당하는 스케줄 ID 리스트 반환
    @Query(value = "SELECT s.scheduleID " +
            "FROM Schedule s " +
            "WHERE s.idolID = :idolID " +
            "AND DATE(s.scheduleDate) = :selectedDate",
            nativeQuery = true)
    List<Integer> findScheduleIdsByDate(
            @Param("idolID") Integer idolID,
            @Param("selectedDate") String selectedDate);

    // 스케줄 ID 리스트로 해당 스케줄의 전체 정보 조회
    @Query(value = "SELECT * FROM Schedule s WHERE s.scheduleID IN :ids", nativeQuery = true)
    List<Schedule> findSchedulesByIds(@Param("ids") List<Integer> ids);

    // 전날, 당일, 다음날의 스케줄을 조회
    @Query(value = "SELECT * FROM Schedule s " +
            "WHERE s.idolID = :idolID " +
            "AND s.scheduleDate BETWEEN :startOfRange AND :endOfRange",
            nativeQuery = true)
    List<Schedule> findSchedulesByDateRange(
            @Param("idolID") Integer idolID,
            @Param("startOfRange") Timestamp startOfRange,
            @Param("endOfRange") Timestamp endOfRange);
}

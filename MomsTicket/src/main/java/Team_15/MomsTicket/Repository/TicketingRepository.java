package Team_15.MomsTicket.Repository;

import Team_15.MomsTicket.Entity.Ticketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TicketingRepository extends JpaRepository<Ticketing, Integer> {
    @Query(value = "insert into Ticketing(applicantID, scheduleID, ticketNum, seatingType, requestMessage) " +
            "values(:user_id, :schedule_id, :ticket_num, :seating_type, :request_message)", nativeQuery = true)
    @Modifying
    @Transactional
    void submit(@Param(value = "user_id") Long user_id, @Param(value = "schedule_id") int schedule_id,
                @Param(value = "ticket_num") Integer ticket_num, @Param(value = "seating_type") String seating_type,
                @Param(value = "request_message") String request_message);
}

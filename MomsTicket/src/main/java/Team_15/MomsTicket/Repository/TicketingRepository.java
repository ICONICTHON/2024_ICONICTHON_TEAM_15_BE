package Team_15.MomsTicket.Repository;

import Team_15.MomsTicket.Entity.Ticketing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketingRepository extends JpaRepository<Ticketing, Integer> {
}

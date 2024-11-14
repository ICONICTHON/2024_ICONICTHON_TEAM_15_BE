package Team_15.MomsTicket.Repository;

import Team_15.MomsTicket.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

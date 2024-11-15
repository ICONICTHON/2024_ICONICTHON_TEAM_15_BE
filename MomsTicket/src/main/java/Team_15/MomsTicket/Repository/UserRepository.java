package Team_15.MomsTicket.Repository;

import Team_15.MomsTicket.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "update User set idolID = :idol_id where userID = :user_id", nativeQuery = true)
    @Modifying
    @Transactional
    void setIdol(@Param(value = "user_id") Long user_id, @Param(value = "idol_id") int idol_id);
}

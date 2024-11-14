package Team_15.MomsTicket.Repository;
import Team_15.MomsTicket.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRoomId(Long roomId);
}
package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.DTO.TicketingFormDTO;
import Team_15.MomsTicket.Entity.Matching;
import Team_15.MomsTicket.Entity.Ticketing;
import Team_15.MomsTicket.Entity.User;
import Team_15.MomsTicket.Repository.MatchingRepository;
import Team_15.MomsTicket.Repository.TicketingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TicketingService {
    private final TicketingRepository ticketingRepository;
    private final MatchingRepository matchingRepository;

    public List<Ticketing> getTicketingList() {
        return ticketingRepository.findAll();
    }

    public void submitTicketing(User currentUser, int schedule_id, TicketingFormDTO formDTO) {
        ticketingRepository.submit(currentUser.getId(), schedule_id, formDTO.getTicketNum(), formDTO.getSeatingType(), formDTO.getRequestMessage());
    }

    @Transactional
    public Matching matchTicketing(User agentUser, int ticketing_id) {
        Matching newMatch = new Matching();
        Ticketing ticketing_to_match = ticketingRepository.findById(ticketing_id)
                .orElseThrow(() -> new NullPointerException("Ticketing with id " + ticketing_id + " not found"));
        ticketing_to_match.setTicketingStatus(1); // 매칭 전 -> 매칭 후, 예매 전 상태 변경
        newMatch.setApplicantID(ticketing_to_match.getApplicantID());
        newMatch.setAgentID(agentUser);
        newMatch.setTicketingID(ticketing_to_match);
        ticketingRepository.save(ticketing_to_match);
        matchingRepository.save(newMatch);
        return newMatch;
    }
}

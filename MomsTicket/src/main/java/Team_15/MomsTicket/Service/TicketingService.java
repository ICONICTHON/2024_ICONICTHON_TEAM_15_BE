package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.DTO.TicketingFormDTO;
import Team_15.MomsTicket.Entity.Matching;
import Team_15.MomsTicket.Entity.Ticketing;
import Team_15.MomsTicket.Entity.User;
import Team_15.MomsTicket.Repository.MatchingRepository;
import Team_15.MomsTicket.Repository.ScheduleRepository;
import Team_15.MomsTicket.Repository.TicketingRepository;
import Team_15.MomsTicket.Repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public List<Ticketing> getTicketingList() {
        return ticketingRepository.findAll();
    }

    public void submitTicketing(User currentUser, int schedule_id, TicketingFormDTO formDTO) {
        Ticketing ticketing = new Ticketing();
        ticketing.setApplicantID(userRepository.findById(currentUser.getId()).get());
        ticketing.setScheduleID(scheduleRepository.findById(schedule_id).get());
        ticketing.setTicketNum(formDTO.getTicketNum());
        ticketing.setSeatingType(formDTO.getSeatingType());
        ticketing.setRequestMessage(formDTO.getRequestMessage());
        ticketingRepository.save(ticketing);
    }

    @Transactional
    public Matching matchTicketing(User agentUser, int ticketing_id) {
        userRepository.save(agentUser); // for persistence
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

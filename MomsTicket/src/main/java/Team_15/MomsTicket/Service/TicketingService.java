package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.DTO.TicketingFormDTO;
import Team_15.MomsTicket.Entity.Ticketing;
import Team_15.MomsTicket.Entity.User;
import Team_15.MomsTicket.Repository.TicketingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TicketingService {
    private final TicketingRepository ticketingRepository;

    public List<Ticketing> getTicketingList() {
        return ticketingRepository.findAll();
    }

    public void submitTicketing(User currentUser, int schedule_id, TicketingFormDTO formDTO) {
        ticketingRepository.submit(currentUser.getId(), schedule_id, formDTO.getTicketNum(), formDTO.getSeatingType(), formDTO.getRequestMessage());
    }
}

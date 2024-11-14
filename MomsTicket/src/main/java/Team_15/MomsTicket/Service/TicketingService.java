package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.Entity.Ticketing;
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
}

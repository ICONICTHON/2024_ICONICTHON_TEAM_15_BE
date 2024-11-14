package Team_15.MomsTicket.Controller;

import Team_15.MomsTicket.Entity.Ticketing;
import Team_15.MomsTicket.Service.TicketingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TicketingController {
    private final TicketingService ticketingService;

    @GetMapping("/ticketing/list")
    public ResponseEntity<Map<String, Object>> getTicketingList() {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            List<Ticketing> ticketingList = ticketingService.getTicketingList();
            if (ticketingList.isEmpty()) {
                throw new NullPointerException("Ticketing list is empty");
            }
            response.put("code", "SU");
            response.put("message", "Success.");
            response.put("ticketing_list", ticketingList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

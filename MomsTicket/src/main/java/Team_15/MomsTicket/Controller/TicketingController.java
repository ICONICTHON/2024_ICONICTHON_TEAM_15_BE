package Team_15.MomsTicket.Controller;

import Team_15.MomsTicket.DTO.TicketingFormDTO;
import Team_15.MomsTicket.Entity.Matching;
import Team_15.MomsTicket.Entity.Ticketing;
import Team_15.MomsTicket.Entity.User;
import Team_15.MomsTicket.Service.TicketingService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/ticketing/submit/{schedule_id}")
    public ResponseEntity<Map<String, Object>> submitTicketing(HttpSession session, @PathVariable("schedule_id") int schedule_id, @RequestBody TicketingFormDTO formDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        System.out.println(formDTO);
        // for test
        User loginUser = new User();
        loginUser.setId(67890L);
        loginUser.setUserName("testUser2");
        session.setAttribute("userInfo", loginUser);

        Object currentUser = session.getAttribute("userInfo");
        try {
            ticketingService.submitTicketing((User)currentUser, schedule_id, formDTO);
            response.put("code", "SU");
            response.put("message", "Success.");
            response.put("submitted_user", currentUser);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception e) {
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/ticketing/match/{ticketing_id}")
    public ResponseEntity<?> matchTicketing(HttpSession session, @PathVariable("ticketing_id") int ticketing_id) {
        Map<String, Object> response = new LinkedHashMap<>();

        // for test
        User loginUser = new User();
        loginUser.setId(33344L);
        loginUser.setUserName("안성현");
        session.setAttribute("userInfo", loginUser);

        Object agentUser = session.getAttribute("userInfo");

        try {
            Matching match = ticketingService.matchTicketing((User) agentUser, ticketing_id);
            response.put("code", "SU");
            response.put("message", "Success.");
            response.put("matched", match);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

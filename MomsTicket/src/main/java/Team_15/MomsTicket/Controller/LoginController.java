package Team_15.MomsTicket.Controller;

import Team_15.MomsTicket.DTO.GetTokenDTO;
import Team_15.MomsTicket.DTO.KakaoUserDTO;
import Team_15.MomsTicket.Entity.Ticketing;
import Team_15.MomsTicket.Entity.User;
import Team_15.MomsTicket.Service.LoginService;
import Team_15.MomsTicket.Service.TicketingService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @Autowired
    private HttpSession session;
    @Autowired
    private TicketingService ticketingService;

    // api with front (get code)
    @GetMapping("/login")
    public ResponseEntity<?> RegisterLogin(@RequestBody String code) throws IOException {
        Map<String, Object> response = new LinkedHashMap<>();
        User loginUser = new User();

        try {
            GetTokenDTO accessToken = loginService.getAccessTokenFromKakao(code);
            KakaoUserDTO userInfo = loginService.getKakaoInfo(accessToken.getAccessToken());
            log.info(userInfo.toString());
            loginService.register(userInfo);

            loginUser.setId(userInfo.getId());
            loginUser.setUserName(userInfo.getNickname());
            loginUser.setProfileImage(userInfo.getProfile_image());

            session.setAttribute("userInfo", loginUser);
            session.setMaxInactiveInterval(60 * 60 * 24);

            response.put("code", "SU");
            response.put("message", "Success.");
            response.put("loginUser", userInfo);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @GetMapping("/{idol_id}")
    public ResponseEntity<?> setIdol(@PathVariable("idol_id") int idol_id) {
        Map<String, Object> response = new LinkedHashMap<>();
        Object currentUser = session.getAttribute("userInfo");

        try {
            loginService.setIdol((User)currentUser, idol_id);
            response.put("code", "SU");
            response.put("message", "idol set successfully.");
            response.put("loginUser", ((User) currentUser).getId());
            response.put("userName", ((User) currentUser).getUserName());
            response.put("idol_id", idol_id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyPage() {
        Map<String, Object> response = new LinkedHashMap<>();

        // for test
        User user = new User();
        user.setId(12345L);
        session.setAttribute("userInfo", user);
        Object currentUser = session.getAttribute("userInfo");
        Long user_id = ((User) currentUser).getId();

        try {
            User user_from_table = loginService.getMyInfo(user_id);
            if (user_from_table == null) {
                throw new NullPointerException("no user found!");
            }
            List<Ticketing> ticketingList = ticketingService.getMyTicketingList(user_from_table)
                    .stream()
                    .sorted(Comparator.comparingInt(Ticketing::getTicketingStatus))
                    .collect(Collectors.toList());
            response.put("code", "SU");
            response.put("message", "Success.");
            response.put("userInfo", user_from_table);
            response.put("ticketingList", ticketingList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}


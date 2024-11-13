package Team_15.MomsTicket.Controller;

import Team_15.MomsTicket.DTO.GetTokenDTO;
import Team_15.MomsTicket.DTO.KakaoUserDTO;
import Team_15.MomsTicket.Entity.User;
import Team_15.MomsTicket.Service.LoginService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @Autowired
    private HttpSession session;

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

}


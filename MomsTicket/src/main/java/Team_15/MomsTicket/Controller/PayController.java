package Team_15.MomsTicket.Controller;

import Team_15.MomsTicket.DTO.KakaoApproveResponseDTO;
import Team_15.MomsTicket.DTO.KakaoPayDTO;
import Team_15.MomsTicket.Service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    @PostMapping("/payment/create/{matching_id}")
    public KakaoPayDTO readyKakaoPay(@PathVariable("matching_id") Integer matching_id) {
        return payService.kakaoPayReady(matching_id);
    }

    @PostMapping("/payment/success")
    public ResponseEntity<?> afterPayRequest(@RequestParam("pg_token") String pgToken) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            KakaoApproveResponseDTO approveResponse = payService.approveResponse(pgToken);
            response.put("code", "SU");
            response.put("message", "Success.");
            response.put("data", approveResponse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @PostMapping("/payment/cancel")
    public ResponseEntity<?> cancel() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code", "CANCELED");
        response.put("message", "Payment cancelled.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/payment/fail")
    public ResponseEntity<?> fail() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code", "FAILED");
        response.put("message", "Payment failed.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

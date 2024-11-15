package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.DTO.KakaoApproveResponseDTO;
import Team_15.MomsTicket.DTO.KakaoPayDTO;
import Team_15.MomsTicket.Entity.Matching;
import Team_15.MomsTicket.Repository.MatchingRepository;
import Team_15.MomsTicket.Repository.TicketingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PayService {
    private final TicketingRepository ticketingRepository;
    @Value("${kakaopay.secretKey}")
    private String secretKey;

    @Value("${kakaopay.cid}")
    private String cid;

    private KakaoPayDTO kakaoPayDTO;
    private final MatchingRepository matchingRepository;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String auth = "SECRET_KEY " + secretKey;
        headers.set("Authorization", auth);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public KakaoPayDTO kakaoPayReady(Integer matching_id) {
        Map<String, Object> params = new HashMap<>();
        Matching matching = matchingRepository.findById(matching_id)
                .orElseThrow(() -> new RuntimeException("Matching not found"));
        partner_order_id = String.valueOf(matching.getId());
        partner_user_id = String.valueOf(matching.getApplicantID().getId());
        item_name = matching.getTicketingID().getScheduleID().getScheduleName();

        params.put("cid", cid);
        params.put("partner_order_id", partner_order_id); // 실제주문번호 -> matching_id와 같게 함.
        params.put("partner_user_id", partner_user_id); // 결제할 사람 (티켓팅 신청인)의 kakao id
        params.put("item_name", item_name); // 실제상품명 -> 대리 티켓팅한 스케줄 이름과 같게 함.
        params.put("quantity", 1); // 수량 1로 고정
        params.put("total_amount", "50000"); // 총 금액 문자열 -> 일단 고정.
        params.put("tax_free_amount", "5000"); // 비과세 금액 문자열 -> ?
        params.put("approval_url", "https://localhost:8080/success");
        params.put("fail_url", "https://localhost:8080/fail");
        params.put("cancel_url", "https://localhost:8080/cancel");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        kakaoPayDTO = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                requestEntity,
                KakaoPayDTO.class);
        log.info(kakaoPayDTO.toString());
        return kakaoPayDTO;
    }

    public KakaoApproveResponseDTO approveResponse(String pgToken) {
        // 카카오 요청
        Map<String, String> params = new HashMap<>();
        params.put("cid", cid);
        params.put("tid", kakaoPayDTO.getTid());
        params.put("partner_order_id", partner_order_id);
        params.put("partner_user_id", partner_user_id);
        params.put("pg_token", pgToken);

        // ticketingStatus 3으로 변경
        ticketingRepository.updateTicketingStatusByApplicantID(Long.parseLong(partner_user_id));
        // parameter, header
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(params, this.getHeaders());
        log.info(requestEntity.toString());

        // 외부에 보낸 url
        RestTemplate restTemplate = new RestTemplate();
        KakaoApproveResponseDTO approveResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                requestEntity,
                KakaoApproveResponseDTO.class);
        log.info(approveResponse.toString());
        return approveResponse;
    }
}

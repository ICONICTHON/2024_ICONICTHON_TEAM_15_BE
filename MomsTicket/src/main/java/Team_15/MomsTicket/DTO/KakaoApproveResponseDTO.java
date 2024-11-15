package Team_15.MomsTicket.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class KakaoApproveResponseDTO {
    private String aid; // 요청 고유 번호
    private String tid; // 결제 고유 번호
    private String cid; // 가맹점 코드
    private String partner_order_id; // 123
    private String partner_user_id; // 3784974864
    private String payment_method_type; // 결제수단
    private AmountDTO amount; // 결제 금액 정보
    private String item_name; // 상품명
    private int quantity; // 상품 수량
    private String created_at; // 결제 요청 시간
    private String approved_at; // 결제 승인 시간
}

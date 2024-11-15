package Team_15.MomsTicket.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AmountDTO {
    private int total; // 총 결제 금액
    private int tax_free; // 비과세 금액
    private int tax; // 부가세 금액
    private int point; // 사용한 포인트
    private int discount; // 할인 금액
    private int green_deposit; // 컵 보증금
}

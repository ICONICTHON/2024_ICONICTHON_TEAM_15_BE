package Team_15.MomsTicket.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class KakaoPayDTO {
    private String tid;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private String created_at;
}

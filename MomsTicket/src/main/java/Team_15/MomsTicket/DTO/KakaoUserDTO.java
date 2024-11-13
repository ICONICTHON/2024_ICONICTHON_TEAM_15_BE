package Team_15.MomsTicket.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class KakaoUserDTO {
    public Long id; // 고유 id
    public String nickname; // 카카오톡 상의 이름
    public String profile_image; // 카카오톡 프로필 사진
}

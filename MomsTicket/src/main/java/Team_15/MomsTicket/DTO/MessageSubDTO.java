package Team_15.MomsTicket.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class MessageSubDTO {
    private Long applicantID;
    private Long agentID;
    private ChatMessageDTO chatMessageDTO;
    private List<ChatRoomListGetResponse> applicantList;
    private List<ChatRoomListGetResponse> agentList;
}

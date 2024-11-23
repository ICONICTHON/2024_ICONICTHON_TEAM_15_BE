package Team_15.MomsTicket.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageSubDTO {
    private String applicantID;
    private String agentID;
    private ChatMessageDTO chatMessageDTO;
    private List<ChatRoomListGetResponse> applicantList;
    private List<ChatRoomListGetResponse> agentList;
}

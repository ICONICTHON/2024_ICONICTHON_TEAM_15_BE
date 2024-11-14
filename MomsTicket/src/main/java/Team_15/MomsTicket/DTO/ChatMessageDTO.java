package Team_15.MomsTicket.DTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Builder
@Getter
@Setter

public class ChatMessageDTO {
    private String messageId;
    private String content;
    private String chatRoomID;
    private String senderID;
    private Timestamp sendDate;

}

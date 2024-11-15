package Team_15.MomsTicket.DTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Builder
@Getter
@Setter

public class ChatMessageDTO {
    private String content;
    private Integer chatRoomID;
    private Long senderID;
    private Timestamp sendDate;

    public ChatMessageDTO(String content, Integer chatRoomID, Long senderID, Timestamp sendDate) {
        this.content = content;
        this.chatRoomID = chatRoomID;
        this.senderID = senderID;
        this.sendDate = sendDate;
    }
}

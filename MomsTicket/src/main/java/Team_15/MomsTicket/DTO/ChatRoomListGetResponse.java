package Team_15.MomsTicket.DTO;

import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
    public class ChatRoomListGetResponse {
        private String ChatRoomID;         // 채팅방 ID
        private String title;     // 채팅방 이름 (예: [콘서트] OOO 서울 콘서트)
        private String lastMessage;  // 채팅방의 마지막 메시지 내용
        private String lastMessageTime; // 마지막 메시지 시간
        private int unreadCount;     // 안 읽은 메시지 개수
}

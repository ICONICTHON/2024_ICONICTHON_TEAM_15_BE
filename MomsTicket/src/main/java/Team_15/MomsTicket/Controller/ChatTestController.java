//package Team_15.MomsTicket.Controller;
//
//import Team_15.MomsTicket.DTO.ChatMessageDTO;
//import Team_15.MomsTicket.Entity.ChatRoom;
//import Team_15.MomsTicket.Entity.Message;
//import Team_15.MomsTicket.Entity.User;
//import Team_15.MomsTicket.Service.ChatMessageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
//@Controller
//public class ChatTestController {
//
//    @Autowired
//    private ChatMessageService chatMessageService;
//
//    // 클라이언트가 /pub/send로 메시지를 전송하면 이 메서드가 호출됩니다.
//    @MessageMapping("/send")
//    @SendTo("/sub/chatRoom_{chatRoomID}")
//    public ChatMessageDTO sendMessage(ChatMessageDTO message, SimpMessageHeaderAccessor headerAccessor) {
//        // 메시지를 Message 엔티티로 변환하여 저장
//        Message newMessage = new Message();
//        User user = new User();
//        user.setId(message.getSenderID());
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.setId(message.getChatRoomID());
//        newMessage.setContent(message.getContent());
//        newMessage.setSenderID(user);
//        newMessage.setChatRoomID(chatRoom); // ChatRoom 엔티티가 아닌 ID를 사용하는 경우 수정 필요
//
//        // 메시지 저장
//        chatMessageService.saveMessage(newMessage);
//
//        // 저장된 메시지를 그대로 반환하여 구독 경로에 전달
//        return message;
//    }
//
//    // 특정 채팅방의 메시지 내역 조회
//    @GetMapping("/chat/history/{roomId}")
//    public List<Message> getChatHistory(@PathVariable("roomId") Integer roomId) {
//        // 채팅방 ID에 맞는 메시지 내역 조회
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.setId(roomId); // ChatRoom 객체 생성 및 ID 설정
//        return chatMessageService.getChatHistory(chatRoom);
//    }
//}

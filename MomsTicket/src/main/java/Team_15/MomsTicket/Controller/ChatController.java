package Team_15.MomsTicket.Controller;

import Team_15.MomsTicket.DTO.ChatMessageDTO;
import Team_15.MomsTicket.Entity.ChatRoom;
import Team_15.MomsTicket.Service.ChatMessageService;
import Team_15.MomsTicket.Config.MessagePublisher;
import Team_15.MomsTicket.Entity.Message;
import Team_15.MomsTicket.Service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private MessagePublisher messagePublisher;

    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/send") // 클라이언트에서 /pub/send로 메시지를 보내면
    @SendTo("/sub/chat_{chatRoomID}")  // /sub/chat로 메시지를 보낸다
    public ChatMessageDTO sendMessage(ChatMessageDTO message) {
        String channelName = "chat_" + message.getChatRoomID();
        messagePublisher.publish(channelName, message.getContent());
        return message;
    }

    @GetMapping("/history/{roomId}")
    public List<Message> getChatHistory(@PathVariable("roomId") ChatRoom roomId) {
        return chatMessageService.getChatHistory(roomId);
    }

    @PostMapping("/save")
    public void saveMessage(@RequestBody ChatMessageDTO messageDTO) {
        chatMessageService.saveMessage(messageDTO);
    }
}

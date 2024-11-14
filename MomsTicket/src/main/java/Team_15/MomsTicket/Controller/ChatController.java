package Team_15.MomsTicket.Controller;

import Team_15.MomsTicket.DTO.ChatMessageDTO;
import Team_15.MomsTicket.Entity.ChatRoom;//추가
import Team_15.MomsTicket.Service.ChatMessageService;
import Team_15.MomsTicket.Config.MessagePublisher;
import Team_15.MomsTicket.Entity.Message;
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

    @MessageMapping("/send")
    @SendTo("/topic/chat")
    public ChatMessageDTO sendMessage(ChatMessageDTO message) {
        messagePublisher.publish("chat", message.getContent());
        return message;
    }

    @GetMapping("/history/{roomId}")
    public List<Message> getChatHistory(@PathVariable /*Long*/ChatRoom roomId) {
        return chatMessageService.getChatHistory(roomId);
    }
}

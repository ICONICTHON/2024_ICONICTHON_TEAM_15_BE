package Team_15.MomsTicket.Controller;

import Team_15.MomsTicket.Repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import Team_15.MomsTicket.DTO.ChatMessageDTO;
import Team_15.MomsTicket.DTO.ChatRoomListGetResponse;
import Team_15.MomsTicket.Entity.ChatRoom;
import Team_15.MomsTicket.Entity.User;
import Team_15.MomsTicket.Service.ChatMessageService;
import Team_15.MomsTicket.Config.MessagePublisher;
import Team_15.MomsTicket.Entity.Message;
import Team_15.MomsTicket.Service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    //추가
    @Autowired
    private UserRepository userRepository;

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

    //추가
    @PostMapping("/feedback")
    public ResponseEntity<String> submitFeedback(@RequestParam Long sellerId, @RequestParam int feedback) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seller not found"));

        // feedback 값이 1이면 trustScore +1, 0이면 trustScore -1
        if (feedback == 1) {
            seller.setTrustScore(seller.getTrustScore() + 1);
        } else if (feedback == 0) {
            seller.setTrustScore(seller.getTrustScore() - 1);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid feedback value");
        }

        userRepository.save(seller);  // 업데이트된 trustScore를 저장
        return ResponseEntity.ok("Feedback submitted successfully");
    }

    @PostMapping("/updateApplicantChatRoomList")
    public void updateApplicantChatRoomList(@RequestParam Long userId, @RequestBody List<ChatRoomListGetResponse> applicantList) {
        chatRoomService.updateApplicantChatRoomList(userId, applicantList);
    }

    @PostMapping("/updateAgentChatRoomList")
    public void updateAgentChatRoomList(@RequestParam Long agentId, @RequestBody List<ChatRoomListGetResponse> agentList) {
        chatRoomService.updateAgentChatRoomList(agentId, agentList);
    }

    @PostMapping("/createTemporaryChatRoom")
    public ChatRoom createTemporaryChatRoom(@RequestParam Long userId, @RequestParam Long agentId) {
        return chatRoomService.createTemporayChatRoom(userId, agentId);
    }
}


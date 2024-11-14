package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.DTO.ChatMessageDTO;
import Team_15.MomsTicket.Entity.ChatRoom;
import Team_15.MomsTicket.Entity.Message;
import Team_15.MomsTicket.Repository.ChatMessageRepository;
import Team_15.MomsTicket.Repository.ChatRoomRepository;
import Team_15.MomsTicket.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

// 메시지 송수신, 저장
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CHATMESSAGE_KEY = "CHAT_MESSAGE";
    //1. 메시지 저장 로직
    public void saveMessage(ChatMessageDTO messageDTO) {
        // redis 에 메시지 저장 (실시간 접근용)
        redisTemplate.opsForList().rightPush(CHATMESSAGE_KEY + ":" + messageDTO.getContent(), messageDTO);

        // 데이터베이스에 메시지 영구 저장
        Message newMessage = new Message();
        newMessage.setContent(messageDTO.getContent());
        newMessage.setChatRoomID(chatRoomRepository.findById(messageDTO.getChatRoomID())
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다.")));
        newMessage.setSenderID(userRepository.findById(messageDTO.getSenderID())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")));
        chatMessageRepository.save(newMessage);
    }

    //2. 채팅방 내 메시지 조회
    public List<Message> getChatHistory(/*Long roomId*/ChatRoom chatRoomID) {
        String redisKey = CHATMESSAGE_KEY + ":" + Integer.toString(chatRoomID.getId());
        List<Object> cachedMessages = redisTemplate.opsForList().range(redisKey, 0, -1);
        if(cachedMessages == null|| cachedMessages.isEmpty()) {
            List<Message> messages = chatMessageRepository.findByChatRoomID(chatRoomID);
            for(Message message : messages) {
                redisTemplate.opsForList().rightPush(redisKey, message);
            }
            return messages;
        }
        return chatMessageRepository.findByChatRoomID(chatRoomID);

    }


}

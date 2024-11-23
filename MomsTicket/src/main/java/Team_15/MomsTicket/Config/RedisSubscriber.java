package Team_15.MomsTicket.Config;
import Team_15.MomsTicket.DTO.ChatRoomListGetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import Team_15.MomsTicket.DTO.ChatMessageDTO;
import Team_15.MomsTicket.DTO.MessageSubDto;
import org.springframework.data.redis.connection.Message;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    // redis에서 메시지 받았을 때 호출되는 메서드
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = new String(message.getBody());
        sendMessage(publishMessage);
        sendRoomList(publishMessage);
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }

    // redis에서 받은 메시지를 특정 채팅방에 발송
    public void sendMessage(String publishMessage) {
        try {
            // MessageSubDto에서 ChatMessageDTO를 추출하여 채팅방 구독자에게 발송
            ChatMessageDTO chatMessage =
                    objectMapper.readValue(publishMessage, MessageSubDto.class).getChatMessageDTO();

            // 채팅방을 구독한 클라이언트에게 메시지 발송
            messagingTemplate.convertAndSend(
                    "/sub/chat/room/" + chatMessage.getChatRoomID(), chatMessage
            );

        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }

    // 채팅방 리스트를 최신화하여 발송
    public void sendRoomList(String publishMessage) {
        try {
            MessageSubDto dto = objectMapper.readValue(publishMessage, MessageSubDto.class);

            List<ChatRoomListGetResponse> chatRoomListGetResponseList = dto.getApplicantList();
            List<ChatRoomListGetResponse> chatRoomListGetResponseListPartner = dto.getAgentList();

            String userId = dto.getApplicantID();
            String partnerId = dto.getAgentID();

            // 신청인 유저 채팅방 리스트 최신화 -> 내 계정에 보냄
            messagingTemplate.convertAndSend(
                    "/sub/chat/roomlist/" + userId, chatRoomListGetResponseList
            );

            // 대리인 계정에도 리스트 최신화 보냄.
            messagingTemplate.convertAndSend(
                    "/sub/chat/roomlist/" + partnerId, chatRoomListGetResponseListPartner
            );

        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }
}

package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.Config.RedisConfig;
import Team_15.MomsTicket.Config.RedisSubscriber;
import Team_15.MomsTicket.DTO.ChatRoomListGetResponse;
import Team_15.MomsTicket.DTO.MessageSubDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {
    private final RedisConfig redisConfig;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisSubscriber redisSubscriber;
    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public ChatRoomService( 
            RedisConfig redisConfig,
            RedisMessageListenerContainer redisMessageListenerContainer,
            RedisSubscriber redisSubscriber,
            SimpMessageSendingOperations messagingTemplate) {
        this.redisConfig = redisConfig;
        this.redisMessageListenerContainer = redisMessageListenerContainer;
        this.redisSubscriber = redisSubscriber;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 새로운 채팅방을 생성하고 Redis 구독 채널을 추가합니다.
     * @param chatRoomId 생성할 채팅방 ID
     */
    public void createChatRoom(String chatRoomId) {
        redisConfig.addChannel(redisMessageListenerContainer, redisSubscriber, chatRoomId);
    }

    /**
     * 신청인에게 채팅방 목록을 갱신합니다.
     * @param userId 신청인 ID
     * @param applicantList 신청인의 채팅방 목록
     */
    public void updateApplicantChatRoomList(Long userId, List<ChatRoomListGetResponse> applicantList) {
        MessageSubDto messageSubDto = new MessageSubDto();
        messageSubDto.setApplicantList(applicantList);
        messagingTemplate.convertAndSend("/sub/chat/roomlist/" + userId, applicantList);
    }

    /**
     * 대리인에게 채팅방 목록을 갱신합니다.
     * @param agentId 대리인 ID
     * @param agentList 대리인의 채팅방 목록
     */
    public void updateAgentChatRoomList(Long agentId, List<ChatRoomListGetResponse> agentList) {
        MessageSubDto messageSubDto = new MessageSubDto();
        messageSubDto.setAgentList(agentList);
        messagingTemplate.convertAndSend("/sub/chat/roomlist/" + agentId, agentList);
    }
}

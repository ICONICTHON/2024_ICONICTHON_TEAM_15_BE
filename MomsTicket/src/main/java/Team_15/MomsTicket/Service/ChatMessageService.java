package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.DTO.ChatMessageDTO;
import Team_15.MomsTicket.Entity.ChatRoom;
import Team_15.MomsTicket.Entity.Message;
import Team_15.MomsTicket.Repository.ChatMessageRepository;
import Team_15.MomsTicket.Repository.ChatRoomRepository;
import Team_15.MomsTicket.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 메시지 송수신, 저장
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public void saveMessage(ChatMessageDTO message) {
        Message newMessage = new Message();
        newMessage.setContent(message.getContent());
        newMessage.setChatRoomID(chatRoomRepository.findById(message.getChatRoomID()).get());
        newMessage.setSenderID(userRepository.findById(message.getSenderID()).get());
        chatMessageRepository.save(newMessage);
    }

    public List<Message> getChatHistory(/*Long roomId*/ChatRoom chatRoomID) {
//        return chatMessageRepository.findByRoomId(roomId);
        return chatMessageRepository.findByChatRoomID(chatRoomID);

    }


}

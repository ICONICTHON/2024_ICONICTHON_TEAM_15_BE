package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.Entity.ChatRoom;//추가
import Team_15.MomsTicket.Entity.Message;
import Team_15.MomsTicket.Repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void saveMessage(Message message) {
        chatMessageRepository.save(message);
    }

    public List<Message> getChatHistory(/*Long roomId*/ChatRoom chatRoomID) {
//        return chatMessageRepository.findByRoomId(roomId);
        return chatMessageRepository.findByChatRoomID(chatRoomID);

    }
}

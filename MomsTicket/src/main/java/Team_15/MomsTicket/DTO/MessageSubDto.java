package Team_15.MomsTicket.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageSubDto {
    private String applicantID;
    private String agentID;
    private ChatMessageDTO chatMessageDTO;
    private List<ChatRoomListGetResponse> applicantList;
    private List<ChatRoomListGetResponse> agentList;

    public ChatMessageDTO getChatMessageDto() {
        return chatMessageDTO;
    }

    public List<ChatRoomListGetResponse> getapplicantList() {
        return applicantList;
    }
    public List<ChatRoomListGetResponse> getagentList() {
        return agentList;
    }

    public String getapplicantId() {
        return applicantID;
    }
    public String getagentId() {
        return agentID;
    }

    public void setApplicantList(List<ChatRoomListGetResponse> applicantList) {
        this.applicantList = applicantList;
    }

    public void setAgentList(List<ChatRoomListGetResponse> agentList) {
        this.agentList = agentList;
    }

    public void setApplicantId(String string) {
    }

    public void setAgentId(String string) {
    }
}

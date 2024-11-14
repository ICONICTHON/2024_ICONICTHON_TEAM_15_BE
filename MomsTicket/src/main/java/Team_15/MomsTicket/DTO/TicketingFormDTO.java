package Team_15.MomsTicket.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TicketingFormDTO {
    private Integer ticketNum;
    private String seatingType;
    private String requestMessage;
}

package Team_15.MomsTicket.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TicketingFormDTO {
    private Integer ticketNum;
    private String seatingType;
    private String requestMessage;
}

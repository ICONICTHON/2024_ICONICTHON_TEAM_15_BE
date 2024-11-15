package Team_15.MomsTicket.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ParticipantList {
    @EmbeddedId
    private ParticipantListId id;

    @MapsId("userID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private User userID;

    @MapsId("recruitmentID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "recruitmentID", nullable = false)
    private Recruitment recruitmentID;

}
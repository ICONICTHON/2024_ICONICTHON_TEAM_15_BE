package Team_15.MomsTicket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchingID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicantID", nullable = false)
    private User applicantID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agentID", nullable = false)
    private User agentID;

    @NotNull
    @Column(name = "matchingStatus", nullable = false, columnDefinition = "int default 0")
    private Integer matchingStatus;

    @Column(name = "matchingDate")
    @CreationTimestamp
    private Instant matchingDate;

    @NotNull
    @Column(name = "isRematch", nullable = false, columnDefinition = "int default 0")
    private Integer isRematch;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticketingID", nullable = false)
    private Ticketing ticketingID;

}
package Team_15.MomsTicket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    @Column(name = "paymentID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "accountNum", nullable = false)
    private Integer accountNum;

    @NotNull
    @Column(name = "payAmount", nullable = false)
    private Integer payAmount;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ticketingID", nullable = false)
    private Ticketing ticketingID;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "applicantID", nullable = false)
    private User applicantID;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "agentID", nullable = false)
    private User agentID;

    @NotNull
    @Column(name = "payStatus", nullable = false)
    private Byte payStatus;

    @Column(name = "paymentDate")
    private Instant paymentDate;

}
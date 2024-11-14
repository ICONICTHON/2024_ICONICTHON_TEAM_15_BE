package Team_15.MomsTicket.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Ticketing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketingID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "applicantID", nullable = false)
    private User applicantID;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "scheduleID", nullable = false)
    private Schedule scheduleID;

    @Column(name = "ticketNum")
    private Integer ticketNum;

    @Lob
    @Column(name = "seatingType")
    private String seatingType;

    @Size(max = 100)
    @Column(name = "requestMessage", length = 100)
    private String requestMessage;

    @NotNull
    @Column(name = "ticketingStatus", nullable = false, columnDefinition = "int default 0")
    private int ticketingStatus = 0;

    @NotNull
    @Column(name = "isRematch", nullable = false, columnDefinition = "int default 0")
    private Integer isRematch = 0;
}
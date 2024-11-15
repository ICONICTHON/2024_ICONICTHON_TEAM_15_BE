package Team_15.MomsTicket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Report {
    @Id
    @Column(name = "reportID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "reportType", nullable = false)
    private Byte reportType;

    @Lob
    @Column(name = "reportReason")
    private String reportReason;

    @NotNull
    @Column(name = "reportDate", nullable = false)
    private Instant reportDate;

    @NotNull
    @Column(name = "reportStatus", nullable = false)
    private Byte reportStatus;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "reporterID", nullable = false)
    private User reporterID;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "reportedUserID", nullable = false)
    private User reportedUserID;

}
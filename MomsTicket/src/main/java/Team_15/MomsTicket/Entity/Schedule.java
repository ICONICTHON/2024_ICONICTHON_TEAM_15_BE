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
public class Schedule {
    @Id
    @Column(name = "scheduleID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idolID", nullable = false)
    private Idol idolID;

    @Size(max = 255)
    @Column(name = "scheduleName")
    private String scheduleName;

    @NotNull
    @Column(name = "scheduleDate", nullable = false)
    private Instant scheduleDate;

    @Size(max = 255)
    @Column(name = "originUrl")
    private String originUrl;

    @Lob
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "location")
    private String location;

    @Column(name = "isTicketing")
    private Byte isTicketing;

}
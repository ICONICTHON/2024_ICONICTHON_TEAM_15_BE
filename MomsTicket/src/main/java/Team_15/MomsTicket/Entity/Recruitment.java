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
public class Recruitment {
    @Id
    @Column(name = "recruitmentID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "startDate", nullable = false)
    private Instant startDate;

    @NotNull
    @Column(name = "expiredDate", nullable = false)
    private Instant expiredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creatorID")
    private User creatorID;

    @NotNull
    @Column(name = "maxParticipants", nullable = false)
    private Integer maxParticipants;

    @NotNull
    @Column(name = "currentParticipants", nullable = false)
    private Integer currentParticipants;

    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    @NotNull
    @Lob
    @Column(name = "genderPreference", nullable = false)
    private String genderPreference;

    @NotNull
    @Lob
    @Column(name = "agePreference", nullable = false)
    private String agePreference;

    @Size(max = 20)
    @NotNull
    @Column(name = "locationPreference", nullable = false, length = 20)
    private String locationPreference;

    @Size(max = 255)
    @Column(name = "additionalNote")
    private String additionalNote;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scheduleID", nullable = false)
    private Schedule scheduleID;

}
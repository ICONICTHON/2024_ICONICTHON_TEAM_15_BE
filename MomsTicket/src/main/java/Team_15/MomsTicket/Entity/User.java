package Team_15.MomsTicket.Entity;

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
public class User {
    @Id
    @Column(name = "userID", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "userName", nullable = false, length = 10)
    private String userName;

    @Size(max = 255)
    @Column(name = "profileImage")
    private String profileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idolID")
    private Idol idolID;

    @NotNull
    @Column(name = "trustScore", columnDefinition = "int default 50")
    private Integer trustScore;

    @Size(max = 20)
    @Column(name = "accountNum", length = 20)
    private String accountNum;

    @Size(max = 100)
    @Column(name = "bio", length = 100)
    private String bio;

}
package Team_15.MomsTicket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Message {
    @Id
    @Column(name = "messageID", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "sendDate", nullable = false)
    private Instant sendDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chatRoomID", nullable = false)
    private ChatRoom chatRoomID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "senderID", nullable = false)
    private User senderID;

}
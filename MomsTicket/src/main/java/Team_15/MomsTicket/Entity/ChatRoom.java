package Team_15.MomsTicket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ChatRoom {
    @Id
    @Column(name = "chatRoomID", nullable = false)
    private Integer id;

    // 채팅방 유형 (안쓸 것)
//    @NotNull
//    @Column(name = "chatRoomType", nullable = false)
//    private Byte chatRoomType;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 255)
    @Column(name = "chatRoomImage")
    private String chatRoomImage;

    @NotNull
    @CreatedDate
    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "recruitmentID", nullable = false)
    private Recruitment recruitmentID;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "scheduleID", nullable = false)
    private Schedule scheduleID;

}
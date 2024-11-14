package Team_15.MomsTicket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Schedule.java 컬럼 json 화
public class ScheduleDTO {
        private Integer id;
        private Integer idolID;
        private String scheduleName;
        private Instant scheduleDate;
        private String originUrl;
        private String description;
        private String location;
        private Boolean isTicketing; // DTO에서 Byte에서 Boolean 타입으로 변환

    }



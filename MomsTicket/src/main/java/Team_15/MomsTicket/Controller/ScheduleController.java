package Team_15.MomsTicket.Controller;

import Team_15.MomsTicket.DTO.ScheduleDTO;
import Team_15.MomsTicket.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 특정 아이돌의 스케줄이 존재하는 날짜 리스트 반환 (year-month)
    @GetMapping("/{idol_id}/schedule/exists")
    public List<ScheduleDTO> getDatesWithSchedules(
            @PathVariable("idol_id") Integer idolID,
            @RequestParam int year,
            @RequestParam int month) {
        return scheduleService.getDatesWithSchedules(year, month, idolID);
    }

    // 특정 날짜에 해당하는 스케줄 전체 정보 리스트 반환
    @GetMapping("/{idol_id}/schedule/details")
    public List<ScheduleDTO> getSchedulesByDate(
            @PathVariable("idol_id") Integer idolID,
            @RequestParam("selectedDate") String selectedDate) {
        return scheduleService.getSchedulesByDate(idolID, selectedDate);
    }

    // 오늘 날짜 기준으로 전날, 당일, 다음날의 스케줄 전체 정보 반환
    @GetMapping("/{idol_id}/schedule/around")
    public List<ScheduleDTO> getSchedulesAroundDate(
            @PathVariable("idol_id") Integer idolID,
            @RequestParam("selectedDate") String selectedDate) {
        return scheduleService.getSchedulesAroundDate(idolID, selectedDate);
    }
}


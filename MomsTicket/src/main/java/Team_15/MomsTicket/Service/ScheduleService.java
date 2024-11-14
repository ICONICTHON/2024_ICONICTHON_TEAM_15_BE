package Team_15.MomsTicket.Service;

import Team_15.MomsTicket.DTO.ScheduleDTO;
import Team_15.MomsTicket.Entity.Schedule;
import Team_15.MomsTicket.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 특정 월에 스케줄이 존재하는 날짜 리스트 반환
    public List<ScheduleDTO> getDatesWithSchedules(int year, int month, Integer idolID) {
        if (idolID == null || idolID < 0) {
            throw new IllegalArgumentException("유효하지 않은 idolID입니다.");
        }

        // 해당 연도와 월의 시작과 끝을 Instant로 계산
        LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.toLocalDate().lengthOfMonth())
                .withHour(23).withMinute(59).withSecond(59);

        // Instant를 Timestamp로 변환하여 전달
        Timestamp startDate = Timestamp.valueOf(startOfMonth);
        Timestamp endDate = Timestamp.valueOf(endOfMonth);

        List<Schedule> schedules = scheduleRepository.findDatesWithSchedules(idolID, startDate, endDate);

        return schedules.stream()
                .map(schedule -> {
                    ScheduleDTO dto = new ScheduleDTO();
                    dto.setId(schedule.getId());
                    dto.setIdolID(schedule.getIdolID().getIdol_id());
                    dto.setScheduleName(schedule.getScheduleName());
                    dto.setScheduleDate(schedule.getScheduleDate());
                    dto.setOriginUrl(schedule.getOriginUrl());
                    dto.setDescription(schedule.getDescription());
                    dto.setLocation(schedule.getLocation());
                    dto.setIsTicketing(schedule.getIsTicketing() != null && schedule.getIsTicketing() == 1);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // 특정 날짜에 해당하는 스케줄 전체 정보 리스트 반환
    public List<ScheduleDTO> getSchedulesByDate(Integer idolID, String selectedDate) {
        if (idolID == null || idolID < 0) {
            throw new IllegalArgumentException("유효하지 않은 idolID입니다.");
        }

        // 특정 날짜에 해당하는 스케줄 ID 리스트 조회
        List<Integer> scheduleIds = scheduleRepository.findScheduleIdsByDate(idolID, selectedDate);

        // ID 리스트에 해당하는 스케줄 전체 정보 조회
        List<Schedule> schedules = scheduleRepository.findSchedulesByIds(scheduleIds);

        return schedules.stream()
                .map(schedule -> {
                    ScheduleDTO dto = new ScheduleDTO();
                    dto.setId(schedule.getId());
                    dto.setIdolID(schedule.getIdolID().getIdol_id());
                    dto.setScheduleName(schedule.getScheduleName());
                    dto.setScheduleDate(schedule.getScheduleDate());
                    dto.setOriginUrl(schedule.getOriginUrl());
                    dto.setDescription(schedule.getDescription());
                    dto.setLocation(schedule.getLocation());
                    dto.setIsTicketing(schedule.getIsTicketing() != null && schedule.getIsTicketing() == 1);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // 특정 날짜 기준으로 전날, 당일, 다음날의 스케줄 전체 정보를 반환하는 메서드
    public List<ScheduleDTO> getSchedulesAroundDate(Integer idolID, String selectedDate) {
        if (idolID == null || idolID < 0) {
            throw new IllegalArgumentException("유효하지 않은 idolID입니다.");
        }

        // 선택된 날짜를 기준으로 전날, 당일, 다음날 범위 설정
        LocalDate selectedLocalDate = LocalDate.parse(selectedDate);
        LocalDateTime startOfRange = selectedLocalDate.minusDays(1).atStartOfDay();
        LocalDateTime endOfRange = selectedLocalDate.plusDays(1).atTime(23, 59, 59);

        Timestamp startOfRangeTimestamp = Timestamp.valueOf(startOfRange);
        Timestamp endOfRangeTimestamp = Timestamp.valueOf(endOfRange);

        // Repository에서 해당 범위에 포함된 스케줄 목록 조회
        List<Schedule> schedules = scheduleRepository.findSchedulesByDateRange(idolID, startOfRangeTimestamp, endOfRangeTimestamp);

        return schedules.stream()
                .map(schedule -> {
                    ScheduleDTO dto = new ScheduleDTO();
                    dto.setId(schedule.getId());
                    dto.setIdolID(schedule.getIdolID().getIdol_id());
                    dto.setScheduleName(schedule.getScheduleName());
                    dto.setScheduleDate(schedule.getScheduleDate());
                    dto.setOriginUrl(schedule.getOriginUrl());
                    dto.setDescription(schedule.getDescription());
                    dto.setLocation(schedule.getLocation());
                    dto.setIsTicketing(schedule.getIsTicketing() != null && schedule.getIsTicketing() == 1);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

package com.tenniscourts.tenniscourts;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.schedules.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TennisCourtService {

    private final TennisCourtRepository tennisCourtRepository;

    private final ScheduleService scheduleService;

    private final TennisCourtMapper tennisCourtMapper;

    public TennisCourtDTO addTennisCourt(TennisCourtDTO tennisCourt) {
        return tennisCourtMapper.map(tennisCourtRepository.saveAndFlush(tennisCourtMapper.map(tennisCourt)));
    }

    public TennisCourtDTO findTennisCourtById(Long id) {
        return tennisCourtRepository.findById(id).map(tennisCourtMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Tennis Court not found.");
        });
    }

    public TennisCourtDTO findTennisCourtWithSchedulesById(Long tennisCourtId) {
        TennisCourtDTO tennisCourtDTO = findTennisCourtById(tennisCourtId);
        tennisCourtDTO.setTennisCourtSchedules(scheduleService.findSchedulesByTennisCourtId(tennisCourtId));
        return tennisCourtDTO;
    }

    public TennisCourtDTO updateTennisCourt(Long tennisCourtId, TennisCourtDTO tennisCourtDTO) {
        TennisCourt existingTennisCourt = tennisCourtRepository.findById(tennisCourtId)
                .orElseThrow(() -> new EntityNotFoundException("Tennis Court not found with ID: " + tennisCourtId));

        existingTennisCourt.setName(tennisCourtDTO.getName());
        TennisCourt updatedTennisCourt = tennisCourtRepository.save(existingTennisCourt);

        return tennisCourtMapper.map(updatedTennisCourt);
    }

    public void deleteTennisCourt(Long tennisCourtId) {
        tennisCourtRepository.findById(tennisCourtId)
                .ifPresentOrElse(
                        tennisCourtRepository::delete,
                        () -> {
                            throw new EntityNotFoundException("Tennis Court not found with ID: " + tennisCourtId);
                        }
                );
    }

    public TennisCourtDTO findTennisCourtByName(String tennisCourtName) {
        return tennisCourtRepository.findByName(tennisCourtName).map(tennisCourtMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Tennis Court not found.");
        });
    }

//    public List<TennisCourtDTO> getAvailableTimeSlots(Long tennisCourtId) {
//        List<TennisCourtDTO> availableTimeSlots = tennisCourtRepository.findAvailableTimeSlots(tennisCourtId);
//        return availableTimeSlots;
//    }
}

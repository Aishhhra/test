package com.tenniscourts.tenniscourts;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tennis-courts")
@AllArgsConstructor
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;


    @PostMapping("/add")
    public ResponseEntity<Void> addTennisCourt(@RequestBody TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }


    @GetMapping("/{tennisCourtId}")
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(@PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }


    @GetMapping("/withSchedules/{tennisCourtId}")
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(@PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }

    @PutMapping("/{tennisCourtId}")
    public ResponseEntity<TennisCourtDTO> updateTennisCourt(@PathVariable Long tennisCourtId,
                                                            @RequestBody TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.ok(tennisCourtService.updateTennisCourt(tennisCourtId, tennisCourtDTO));
    }

    @DeleteMapping("/{tennisCourtId}")

    public ResponseEntity<Void> deleteTennisCourt(@PathVariable Long tennisCourtId) {
        tennisCourtService.deleteTennisCourt(tennisCourtId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{tennisCourtName}")
    public ResponseEntity<TennisCourtDTO> findTennisCourtByName(@PathVariable String tennisCourtName) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtByName(tennisCourtName));
    }

//    @GetMapping("/available-time-slots")
//    public ResponseEntity<List<TennisCourtDTO>> getAvailableTimeSlots(@RequestParam Long tennisCourtId) {
//        return ResponseEntity.ok(tennisCourtService.getAvailableTimeSlots(tennisCourtId));
//    }
}

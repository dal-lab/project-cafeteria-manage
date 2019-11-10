package com.poppo.dallab.cafeteria.interfaces;

import com.poppo.dallab.cafeteria.applications.WorkDayService;
import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.dto.WorkDayRequestDto;
import com.poppo.dallab.cafeteria.dto.WorkMonthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class WorkDayController {

    private final WorkDayService workDayService;

    @GetMapping("/workMonth")
    public WorkMonthResponseDto getWorkMonthList() {

        List<Integer> workMonthList = workDayService.getWorkMonths();
        WorkMonthResponseDto workMonthResponseDto = WorkMonthResponseDto.builder()
                .existedMonthList(workMonthList)
                .build();

        return workMonthResponseDto;
    }

    @PostMapping("/workDay")
    public ResponseEntity startMonth(
            @RequestBody WorkDayRequestDto requestDto
    ) throws URISyntaxException {

        List<WorkDay> workDays = workDayService.bulkCreate(requestDto.getMenuPlanMonth());
        String url = "/workDay";

        return ResponseEntity.created(new URI(url)).body("Created " + workDays.size() + " days");
    }

}

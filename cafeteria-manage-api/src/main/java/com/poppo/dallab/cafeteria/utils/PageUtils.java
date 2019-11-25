package com.poppo.dallab.cafeteria.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageUtils {

    private final DateTimeUtils dateTimeUtils;


    public Pageable getPageable(Integer year, Integer month, Integer page) {

        Integer firstWeekLength = dateTimeUtils.getFirstWeekLength(year, month);

        if (page == 1) {
            return PageRequest.of(0, firstWeekLength);
        }

        return PageRequest.of(page - 1, 5);
    }
}

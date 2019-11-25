package com.poppo.dallab.cafeteria.utils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

public class PageUtilsTests {

    private PageUtils pageUtils;
    private DateTimeUtils dateTimeUtils;

    @Before
    public void setup() {

        dateTimeUtils = new DateTimeUtils();
        pageUtils = new PageUtils(dateTimeUtils);
    }

    @Test
    public void 주어진_월의_첫주_페이지객체_반환() {

        Pageable pageable = pageUtils.getPageable(2019, 11, 1);

        assertThat(pageable.getPageSize()).isEqualTo(1);
    }

    @Test
    public void 주어진_월의_첫째주_외의_주_페이지객체_반환() {

        Pageable pageable = pageUtils.getPageable(2019, 11, 4);

        assertThat(pageable.getPageSize()).isEqualTo(5);
    }
}
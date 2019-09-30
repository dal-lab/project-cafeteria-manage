package com.poppo.dallab.cafeteria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String day;

    LocalDate date;

}

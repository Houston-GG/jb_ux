package com.example.jb_ux.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ScheduleDto {

    private Long id;

    private Long periodicity;

    private LocalTime triggerTime;

    private List<SavedValueDto> savedValues;
}

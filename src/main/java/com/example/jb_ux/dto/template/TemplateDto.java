package com.example.jb_ux.dto.template;

import com.example.jb_ux.dto.ScheduleDto;
import com.example.jb_ux.dto.recipient.RecipientDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TemplateDto {

    private String id;

    private String templateString;

    private List<RecipientDto> recipients;

    private List<ScheduleDto> schedules;
}

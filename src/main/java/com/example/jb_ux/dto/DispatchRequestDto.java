package com.example.jb_ux.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class DispatchRequestDto {

    @NotEmpty
    private String templateId;

    @NotEmpty
    private @Valid HashMap<String, String> variables;

    private List<String> dispatchMethods;
}

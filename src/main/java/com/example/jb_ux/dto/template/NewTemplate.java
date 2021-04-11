package com.example.jb_ux.dto.template;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Set;

@Getter
@Setter
public class NewTemplate {

    @NotEmpty
    private String templateId;

    @NotEmpty
    private String templateString;

    @NotNull
    @NotEmpty
    private @Valid HashMap<String, Set<@NotEmpty String>> recipientsWithMethod;
}

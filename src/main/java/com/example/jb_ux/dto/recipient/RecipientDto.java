package com.example.jb_ux.dto.recipient;

import com.example.jb_ux.dto.DispatchMethodDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecipientDto {

    private Long id;

    private DispatchMethodDto dispatchMethod;

    private String value;
}

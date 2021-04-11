package com.example.jb_ux.controller;

import com.example.jb_ux.dto.DispatchRequestDto;
import com.example.jb_ux.exception.TemplateNotFoundException;
import com.example.jb_ux.exception.TemplateValuesException;
import com.example.jb_ux.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/dispatch")
public class DispatchController {

    private final DispatchService dispatchService;

    @Autowired
    public DispatchController(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @PostMapping
    public Map<String, String> dispatch(@RequestBody @Valid DispatchRequestDto dispatchRequest){
        try {
            return dispatchService.dispatch(dispatchRequest);
        } catch (TemplateValuesException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage());
        } catch (TemplateNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}

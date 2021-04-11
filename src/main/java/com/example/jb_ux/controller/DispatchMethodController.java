package com.example.jb_ux.controller;

import com.example.jb_ux.dto.DispatchMethodDto;
import com.example.jb_ux.service.DispatchMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/methods")
public class DispatchMethodController {

    private final DispatchMethodService dispatchMethodService;

    @Autowired
    public DispatchMethodController(DispatchMethodService dispatchMethodService) {
        this.dispatchMethodService = dispatchMethodService;
    }

    @GetMapping
    public List<DispatchMethodDto> getAll(){
        return dispatchMethodService.getAll();
    }
}

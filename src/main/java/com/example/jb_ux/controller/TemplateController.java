package com.example.jb_ux.controller;

import com.example.jb_ux.dto.template.NewTemplate;
import com.example.jb_ux.dto.template.TemplateDto;
import com.example.jb_ux.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateService templateService;

    @Autowired
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public List<TemplateDto> getAll(){
        return templateService.getAll();
    }

    @PostMapping("/template")
    public void saveNewTemplate(@RequestBody @Valid NewTemplate newTemplate) {
        templateService.saveNewTemplate(newTemplate);

    }
}

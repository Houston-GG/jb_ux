package com.example.jb_ux.controller;

import com.example.jb_ux.dto.template.NewTemplate;
import com.example.jb_ux.dto.template.TemplateDto;
import com.example.jb_ux.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            templateService.saveNewTemplate(newTemplate);
        } catch (InvalidDataAccessApiUsageException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Selected unknown dispatch method.");
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Template with ID [" + newTemplate.getTemplateId() + "] already exists.");
        }

    }
}

package com.example.jb_ux.service;

import com.example.jb_ux.dto.DispatchRequestDto;
import com.example.jb_ux.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DispatchService {

    private final TemplateService templateService;
    private final RestDispatchService restDispatchService;

    @Autowired
    public DispatchService(TemplateService templateService, RestDispatchService restDispatchService) {
        this.templateService = templateService;
        this.restDispatchService = restDispatchService;
    }

    public Map<String, String> dispatch(DispatchRequestDto dispatchRequest) {
        Template template = templateService.findTemplateById(dispatchRequest.getTemplateId());
        Map<String, String> statusList = new HashMap<>();
        String resultString = "";
        resultString = templateService.processTemplate(template.getTemplateString(), dispatchRequest.getVariables());

        if (dispatchRequest.getDispatchMethods() == null || (dispatchRequest.getDispatchMethods() != null && dispatchRequest.getDispatchMethods().isEmpty())) {
            statusList = restDispatchService.makeRestDispatch(template.getRecipients(), resultString);
        }

        //Todo new dispatch method

        return statusList;
    }
}

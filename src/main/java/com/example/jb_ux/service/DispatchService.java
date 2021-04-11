package com.example.jb_ux.service;

import com.example.jb_ux.configuration.AvailableMethod;
import com.example.jb_ux.dto.DispatchRequestDto;
import com.example.jb_ux.model.Recipient;
import com.example.jb_ux.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DispatchService {

    private final TemplateService templateService;
    private final RestDispatchService restDispatchService;
    private final MailDispatchService mailDispatchService;

    @Autowired
    public DispatchService(TemplateService templateService, RestDispatchService restDispatchService, MailDispatchService mailDispatchService) {
        this.templateService = templateService;
        this.restDispatchService = restDispatchService;
        this.mailDispatchService = mailDispatchService;
    }

    public Map<String, String> dispatch(DispatchRequestDto dispatchRequest) {
        Template template = templateService.findTemplateById(dispatchRequest.getTemplateId());
        Map<String, String> statusList = new HashMap<>();
        String resultString = "";
        resultString = templateService.processTemplate(template.getTemplateString(), dispatchRequest.getVariables());

        if (dispatchRequest.getDispatchMethods() == null || (dispatchRequest.getDispatchMethods() != null && dispatchRequest.getDispatchMethods().isEmpty())) {
            statusList = restDispatchService.makeRestDispatch(template.getRecipients(), resultString);
        } else {
            //Todo need to refactor
            for (String method : dispatchRequest.getDispatchMethods()) {

                AvailableMethod availableMethod = AvailableMethod.valueOf(method);
                switch (availableMethod) {
                    case REST_REQUEST:
                        statusList.putAll(
                                restDispatchService.makeRestDispatch(template.getRecipients()
                                .stream()
                                .filter(r -> r.getDispatchMethod().getName().equals(method))
                                .collect(Collectors.toList()), resultString));
                        break;

                    case EMAIL:
                        String[] stringArray = template.getRecipients()
                                .stream()
                                .filter(r -> r.getDispatchMethod().getName().equals(method))
                                .map(Recipient::getValue).toArray(String[]::new);
                        if (stringArray.length!=0) {
                            mailDispatchService.makeMailDispatch(stringArray, "Email dispatch", resultString);
                            statusList.putAll(Arrays.stream(stringArray)
                                    .map(e -> new AbstractMap.SimpleEntry<>(e, "DISPATCHED"))
                                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
                        }
                        break;

                    default:
                        break;
                }
            }
        }
        return statusList;
    }
}

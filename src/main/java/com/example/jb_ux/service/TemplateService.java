package com.example.jb_ux.service;

import com.example.jb_ux.dao.RecipientDao;
import com.example.jb_ux.dao.TemplateDao;
import com.example.jb_ux.dto.template.NewTemplate;
import com.example.jb_ux.dto.template.TemplateDto;
import com.example.jb_ux.model.DispatchMethod;
import com.example.jb_ux.model.Recipient;
import com.example.jb_ux.model.Template;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemplateService {

    private final TemplateDao templateDao;
    private final ModelMapper modelMapper;
    private final RecipientDao recipientDao;

    @Autowired
    public TemplateService(TemplateDao templateDao, ModelMapper modelMapper, RecipientDao recipientDao) {
        this.templateDao = templateDao;
        this.modelMapper = modelMapper;
        this.recipientDao = recipientDao;
    }

    public List<TemplateDto> getAll() {
        return IterableUtils.toList(templateDao.findAll())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void saveNewTemplate(NewTemplate newTemplate) {
        recipientDao.saveAll(convertToEntity(newTemplate).getRecipients());
    }

    private TemplateDto convertToDto(Template template) {
        return modelMapper.map(template, TemplateDto.class);
    }

    private Template convertToEntity(NewTemplate newTemplate) {
        List<Recipient> recipients = new ArrayList<>();
        Template template = new Template(newTemplate.getTemplateId(), newTemplate.getTemplateString(), recipients);

        newTemplate.getRecipientsWithMethod().forEach((r, values) -> {
            values.forEach(v-> {
                Recipient recipient = new Recipient(template, new DispatchMethod(r), v);
                recipients.add(recipient);
            });
        });

        return template;
    }
}

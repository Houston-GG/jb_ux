package com.example.jb_ux.service;

import com.example.jb_ux.dao.DispatchMethodDao;
import com.example.jb_ux.dto.DispatchMethodDto;
import com.example.jb_ux.model.DispatchMethod;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DispatchMethodService {

    private final ModelMapper modelMapper;
    private final DispatchMethodDao dispatchMethodDao;

    @Autowired
    public DispatchMethodService(ModelMapper modelMapper, DispatchMethodDao dispatchMethodDao) {
        this.modelMapper = modelMapper;
        this.dispatchMethodDao = dispatchMethodDao;
    }

    public List<DispatchMethodDto> getAll() {
        return IterableUtils.toList(dispatchMethodDao.findAll())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DispatchMethodDto convertToDto(DispatchMethod dispatchMethod) {
        return modelMapper.map(dispatchMethod, DispatchMethodDto.class);
    }
}

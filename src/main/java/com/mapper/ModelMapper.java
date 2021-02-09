package com.mapper;

import com.domain.Model;
import com.dto.ModelDto;
import com.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    @Autowired
    private ProducerRepository producerRepository;

    public Model mapToModel(final ModelDto dto) {
        Model modelBean = new Model();
        modelBean.setId(dto.getId());
        modelBean.setName(dto.getName());
        modelBean.setProducer(producerRepository.findProducerById(dto.getId()));
        modelBean.setToolList(dto.getToolList());
        return modelBean;
    }

    public ModelDto mapToDto(final Model model) {
        ModelDto dtoBean = new ModelDto();
        dtoBean.setId(model.getId());
        dtoBean.setName(model.getName());
        dtoBean.setProducerId(model.getProducer().getId());
        dtoBean.setToolList(model.getToolList());
        return dtoBean;
    }

    public List<ModelDto> mapToModetDtoList(final List<Model> modelList) {
        return modelList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}

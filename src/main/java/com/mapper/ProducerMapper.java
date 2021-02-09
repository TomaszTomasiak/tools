package com.mapper;

import com.domain.Producer;
import com.dto.ProducerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProducerMapper {

   private Producer mapToProducer(final ProducerDto dto) {
       Producer producerBean = new Producer();
       producerBean.setId(dto.getId());
       producerBean.setName(dto.getName());
       producerBean.setModelList(dto.getModelList());
       producerBean.setToolList(dto.getToolList());
       return producerBean;
   }

   private ProducerDto mapToDto(final Producer producer) {
       ProducerDto dtoBean = new ProducerDto();
       dtoBean.setId(producer.getId());
       dtoBean.setName(producer.getName());
       dtoBean.setModelList(producer.getModelList());
       dtoBean.setToolList(producer.getToolList());
       return dtoBean;
   }

   private List<ProducerDto> mapToListDtos(final List<Producer> producers) {
       return producers.stream()
               .map(this::mapToDto)
               .collect(Collectors.toList());
   }
}

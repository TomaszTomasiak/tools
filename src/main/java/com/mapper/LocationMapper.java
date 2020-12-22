package com.mapper;

import com.domain.Location;
import com.dto.LocationDto;
import com.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper {

    @Autowired
    private LocationRepository repository;

    public Location mapToLocation(LocationDto dto) {
        Location bean = new Location();
        bean.setId(dto.getId());
        bean.setCountry(dto.getCountry());
        bean.setCity(dto.getCity());
        bean.setZipCode(dto.getZipCode());
        bean.setAddres(dto.getAddres());
        bean.setEmail(dto.getEmail());
        bean.setPhone(dto.getPhone());
        bean.setToolList(dto.getToolList());
        return bean;
    }

    public LocationDto mapToLocationDto(Location location){
        LocationDto dtoBean = new LocationDto();
        dtoBean.setId(location.getId());
        dtoBean.setCountry(location.getCountry());
        dtoBean.setCity(location.getCity());
        dtoBean.setZipCode(location.getZipCode());
        dtoBean.setAddres(location.getAddres());
        dtoBean.setEmail(location.getEmail());
        dtoBean.setPhone(location.getPhone());
        dtoBean.setToolList(location.getToolList());
        return dtoBean;
    }

    public List<LocationDto> mapToLocationDtoList(List<Location> list) {
        return list.stream()
                .map(this::mapToLocationDto)
                .collect(Collectors.toList());
    }

}

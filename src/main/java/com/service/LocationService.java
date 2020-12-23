package com.service;

import com.domain.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LocationService {
    List<Location> getAllLocations();

    Optional<Location> getLocation(long id);

    Location saveLocation(Location location);

    Location getLocationById(long id);

    void deleteLocation(long id);
}

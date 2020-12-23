package com.service;

import com.domain.Location;
import com.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<Location> getAllLocations() {
        log.debug("Request to get all locations");
        return repository.findAll();
    }

    @Override
    public Optional<Location> getLocation(long id) {
        log.debug("Request to get location with id: {}", id);
        return repository.findById(id);
    }

    @Override
    public Location saveLocation(Location location) {
        log.debug("Request to save location : {}", location);
        return repository.save(location);
    }

    @Override
    public Location getLocationById(long id) {
        log.debug("Request to find location with Id : {}", id);
        return repository.findLocationById(id);
    }

    @Override
    public void deleteLocation(long id) {
        log.debug("Request to delete location with Id : {}", id);
        repository.deleteById(id);
    }
}

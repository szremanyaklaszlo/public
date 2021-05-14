package hu.zerotohero.verseny.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.zerotohero.verseny.crud.dao.LocationRepository;
import hu.zerotohero.verseny.crud.domain.Location;
import hu.zerotohero.verseny.crud.service.exception.LocationNotFoundException;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll() {
        return (List<Location>) locationRepository.findAll();
    }
    
    public void createLocation(Location location) {
        if(!isAlreadyExist(location)) {
            locationRepository.save(location);
        }
    }

    private boolean isAlreadyExist(Location location) {
        return findAll().stream().anyMatch(loc -> loc.equals(location));
    }
    
    public void updateLocation (long id, Location updatedLocation) {
        if(!isAlreadyExist(updatedLocation)) {
            Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException("Location not found with id " + id));
            location.setName(updatedLocation.getName());
            location.setAddress(updatedLocation.getAddress());
            locationRepository.save(location);
        }
    }
    
    public void deleteLocation(long id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException("Location not found with id " + id));
        locationRepository.delete(location);
    }

}

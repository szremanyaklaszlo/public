package hu.zerotohero.verseny.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import hu.zerotohero.verseny.crud.domain.Location;
import hu.zerotohero.verseny.crud.service.exception.LocationNotFoundException;

@Service
public class LocationResolver {

    @Autowired
    private LocationService locationService;

    public Location getLocation(Location location) {
        Optional<Location> result = locationService.findAll().stream().filter(l -> l.equals(location)).findAny();
        if(!result.isPresent()) {
            throw new LocationNotFoundException(location.getName() + " " + location.getAddress() + " not found.");
        }
        return result.get();
    }
    
}

package hu.zerotohero.verseny.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.zerotohero.verseny.crud.domain.Location;
import hu.zerotohero.verseny.crud.service.LocationService;

@RestController
@RequestMapping("/crud")
public class LocationController {
    
    @Autowired
    private LocationService locationService;
    
    @GetMapping(value = "api/location/get")
    public List<Location> getLocation() {
        return locationService.findAll();
    }
    
    @PostMapping(value = "api/location", consumes = "application/json")
    public HttpStatus createLocation(@RequestBody Location location) {
        locationService.createLocation(location);
        return HttpStatus.OK;
    }
    
    @PutMapping(value = "api/location/{id}", consumes = "application/json")
    public HttpStatus updateLocation(@PathVariable Long id, @RequestBody Location location) {
        locationService.updateLocation(id, location);
        return HttpStatus.OK;
    }
    
    @DeleteMapping (value = "api/location/delete/{id}")
    public HttpStatus deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return HttpStatus.OK;
    }
}

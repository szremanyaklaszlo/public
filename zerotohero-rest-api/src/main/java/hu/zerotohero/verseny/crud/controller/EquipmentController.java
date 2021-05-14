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

import hu.zerotohero.verseny.crud.domain.Equipment;
import hu.zerotohero.verseny.crud.service.EquipmentService;

@RestController
@RequestMapping("/crud")
public class EquipmentController {
    
    @Autowired
    private EquipmentService equipmentService;
    
    @GetMapping(value = "api/equipment/get")
    public List<Equipment> getEquipments (){
        return equipmentService.findAll();
    }
    @PostMapping(value = "api/equipment", consumes = "application/json")
    public HttpStatus createEquipment(@RequestBody Equipment equipment) {
        equipmentService.createEquipment(equipment);
        return HttpStatus.OK;
    }
    
    @PutMapping(value = "api/equipment/{id}", consumes = "application/json")
    public HttpStatus updateEquipment(@PathVariable Long id, @RequestBody Equipment equipment) {
        equipmentService.updateEquipment(id, equipment);
        return HttpStatus.OK;
    }
    
    @DeleteMapping(value = "api/equipment/delete/{id}")
    public HttpStatus deleteEquipment(@PathVariable Long id, @RequestBody Equipment equipment) {
        equipmentService.deleteEquipment(id);;
        return HttpStatus.OK;
    }
}

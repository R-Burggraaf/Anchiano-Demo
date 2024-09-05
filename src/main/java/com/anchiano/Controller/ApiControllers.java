package com.anchiano.Controller;

import com.anchiano.Models.Bin;
import com.anchiano.Repo.BinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private BinRepo binRepo;

    @GetMapping(value ="/")
    public String getPage(){
        return "Hi there";
    }

    @GetMapping(value = "/allbins")
    public List<Bin> getBins(){
        return binRepo.findAll();
    }

    @PostMapping(value = "/addbin")
    public String addBin(@RequestBody Bin bin){
        binRepo.save(bin);
        return "Successfully added";
    }

    @PutMapping(value = "/update/{id}")
    public String updateBin(@PathVariable long id, @RequestBody Bin bin){
        Bin updatedBin = binRepo.findById(id).get();
        updatedBin.setGeoLocation(bin.getGeoLocation());
        updatedBin.setCurrentCapacity(bin.getCurrentCapacity());
        updatedBin.setMaxCapacity(bin.getMaxCapacity());
        updatedBin.setTrashType(bin.getTrashType());

        binRepo.save(updatedBin);
        return "Successfully updated bin with id: " + id;
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteBin(@PathVariable long id){
        Bin deleteBin = binRepo.findById(id).get();
        binRepo.delete(deleteBin);
        return "Bin with id: " + id + " has been successfully deleted.";
    }

}

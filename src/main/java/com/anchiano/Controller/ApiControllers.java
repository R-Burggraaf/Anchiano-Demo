package com.anchiano.Controller;

import com.anchiano.Models.Bin;
import com.anchiano.Repo.BinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class ApiControllers {

    @Autowired
    private BinRepo binRepo;

    //Landing page
    @GetMapping(value ="/")
    public String getPage(){
        return "Hi there";
    }

    // Shows list of all the bins
    @GetMapping(value = "/allbins")
    public List<Bin> getBins(){
        return binRepo.findAll();
    }

    // takes jSon file and adds it to the database
    @PostMapping(value = "/addbin")
    public String addBin(@RequestBody Bin bin){
        binRepo.save(bin);
        return "Successfully added";
    }

    // takes jSon file and updates the values to the database of specific id given in url
    // should be checked if there's not already a bin on this specific geolocation
    // function should only be used by admin
    @PutMapping(value = "/update/{id}")
    public String updateBin(@PathVariable long id, @RequestBody Bin bin){
        Bin updatedBin = binRepo.findById(id).get();
        updatedBin.setGeoLocation(bin.getGeoLocation());
        updatedBin.setCurrentCapacity(bin.getCurrentCapacity());
        updatedBin.setMaxCapacity(bin.getMaxCapacity());
        updatedBin.setTrashType(bin.getTrashType());

    // checked if the maxcapacity of this bin falls within the expected values.
        if(updatedBin.getMaxCapacity()>updatedBin.getAllowedCapacity()){
            return "Max Capacity not right";
        }
        binRepo.save(updatedBin);
        return "Successfully updated bin with id: " + id;
    }

    @PutMapping(value = "/sensorUpdate/{id}&{trash}")
    public String sensorUpdateBin(@PathVariable long id, @PathVariable int trash , @RequestBody Bin bin){
        Bin updatedBin = binRepo.findById(id).get();
        // trash is added to current capacity
        updatedBin.setCurrentCapacity(updatedBin.getCurrentCapacity()+trash);
        // checked if the maxcapacity of this bin falls within the expected values.
        if(updatedBin.getCurrentCapacity()>updatedBin.getMaxCapacity()){
            return "Bin is full and should be emptied";
        }
        binRepo.save(updatedBin);
        return "Successfully added " + trash + " to bin: " + id;
    }

    // deletes specific bin from the database of id given in url
    // should be protected by role in session
    @DeleteMapping(value = "/delete/{id}")
    public String deleteBin(@PathVariable long id){
        Bin deleteBin = binRepo.findById(id).get();
        binRepo.delete(deleteBin);
        return "Bin with id: " + id + " has been successfully deleted.";
    }

}

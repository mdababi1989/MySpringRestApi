package com.mdababi.controllers.v1;

import com.mdababi.api.v1.model.VendorDTO;
import com.mdababi.api.v1.model.VendorListDTO;
import com.mdababi.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors/";

    VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    VendorListDTO getAllVendors(){
        return new VendorListDTO(vendorService.getAllVendors());
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    VendorDTO updateVendorById(@PathVariable  Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.updateVendorById(id, vendorDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteVendorById(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }
}

package com.mdababi.service;

import com.mdababi.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    VendorDTO updateVendorById(Long id, VendorDTO vendorDTO);
    void deleteVendorById(Long id);

}

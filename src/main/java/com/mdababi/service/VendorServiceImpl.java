package com.mdababi.service;

import com.mdababi.api.v1.mapper.VendorMapper;
import com.mdababi.api.v1.model.VendorDTO;
import com.mdababi.controllers.v1.VendorController;
import com.mdababi.domain.Vendor;
import com.mdababi.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    VendorRepository vendorRepository;
    VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = VendorMapper.INSTANCE;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream().map(vendor -> {
            VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);
            vendorDTO.setVendor_url(VendorController.BASE_URL);
            return vendorDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        Optional<VendorDTO> vendorDTO =  vendorRepository.findById(id).map(vendorMapper::vendorToVendorDto);
        if(vendorDTO.isPresent()) vendorDTO.get().setVendor_url(VendorController.BASE_URL+id);
        return vendorDTO.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor savedVendor = vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO));
        VendorDTO returnDTO = VendorDTO.builder().name(savedVendor.getName()).vendor_url(VendorController.BASE_URL + savedVendor.getId()).build();
        return returnDTO;
    }

    @Override
    public VendorDTO updateVendorById(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);
        Vendor updatedVendor = vendorRepository.save(vendor);
        VendorDTO returnDTO = vendorMapper.vendorToVendorDto(updatedVendor);
        returnDTO.setVendor_url(VendorController.BASE_URL + id);
        return returnDTO;
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);

    }
}

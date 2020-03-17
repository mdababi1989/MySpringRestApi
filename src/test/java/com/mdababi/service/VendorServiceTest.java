package com.mdababi.service;

import com.mdababi.api.v1.mapper.VendorMapper;
import com.mdababi.api.v1.model.VendorDTO;
import com.mdababi.controllers.v1.VendorController;
import com.mdababi.domain.Vendor;
import com.mdababi.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VendorServiceTest {
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository);
    }

    @Test
    void getAllVendors() {
        Vendor vendor1 = Vendor.builder().name("vendor1").build();
        Vendor vendor2 = Vendor.builder().name("vendor2").build();
        Vendor vendor3 = Vendor.builder().name("vendor3").build();
        List<Vendor> vendors = Arrays.asList(vendor1, vendor2, vendor3);
        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();
        assertEquals(3, vendorDTOS.size());
    }

    @Test
    void getVendorById() {

        long id = 1L;
        String name = "test";
        Vendor vendor = Vendor.builder().name(name).id(id).build();
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));
        VendorDTO vendorDTO = vendorService.getVendorById(id);
        assertEquals(name, vendorDTO.getName());
        assertEquals(VendorController.BASE_URL+id, vendorDTO.getVendor_url());
        
    }

    @Test
    void createNewVendor() {
        long id = 1L;
        String name = "test";
        Vendor vendor = Vendor.builder().name(name).id(id).build();
        VendorDTO vendorDTO = VendorDTO.builder().name(name).build();
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);
        VendorDTO savedVendor= vendorService.createNewVendor(vendorDTO);
        assertEquals(name, savedVendor.getName());
        assertEquals(VendorController.BASE_URL+id, savedVendor.getVendor_url());
    }

    @Test
    void updateVendorById() {
        long id = 1L;
        String name = "test";
        String updatedName = "updated";
        Vendor vendor = Vendor.builder().name(updatedName).id(id).build();
        VendorDTO vendorDTO = VendorDTO.builder().name(name).build();
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);
        VendorDTO savedVendor= vendorService.createNewVendor(vendorDTO);
        assertEquals(updatedName, savedVendor.getName());
        assertEquals(VendorController.BASE_URL+id, savedVendor.getVendor_url());


    }

    @Test
    void deleteVendorById() {
        long id = 1L;
        vendorService.deleteVendorById(id);
        verify(vendorRepository, times(1)).deleteById(id);
    }
}
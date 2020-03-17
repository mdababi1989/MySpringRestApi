package com.mdababi.api.v1.mapper;

import com.mdababi.api.v1.model.VendorDTO;
import com.mdababi.domain.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {
    public static final String TEST = "Test";
    private VendorMapper vendorMapper;

    @BeforeEach
    void setUp() {
        vendorMapper = VendorMapper.INSTANCE;
    }

    @Test
    void vendorToVendorDto() {
        Vendor vendor = Vendor.builder().name(TEST).build();
        VendorDTO vendorDTO= vendorMapper.vendorToVendorDto(vendor);
        assertEquals(TEST, vendorDTO.getName());
    }
}
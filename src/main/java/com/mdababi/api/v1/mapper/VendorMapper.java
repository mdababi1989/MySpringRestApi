package com.mdababi.api.v1.mapper;

import com.mdababi.api.v1.model.VendorDTO;
import com.mdababi.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
    VendorDTO vendorToVendorDto(Vendor vendor);
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}

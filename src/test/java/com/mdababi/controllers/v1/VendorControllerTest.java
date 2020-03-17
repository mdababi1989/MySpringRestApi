package com.mdababi.controllers.v1;

import com.jayway.jsonpath.JsonPath;
import com.mdababi.api.v1.model.VendorDTO;
import com.mdababi.domain.Vendor;
import com.mdababi.repositories.VendorRepository;
import com.mdababi.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.mdababi.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest {
    @Mock
    VendorService vendorService;
    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    void getAllVendors() throws Exception {
        VendorDTO vendorDTO1 = VendorDTO.builder().name("v1").build();
        VendorDTO vendorDTO2 = VendorDTO.builder().name("v2").build();
        List<VendorDTO> vendorDTOS = Arrays.asList(vendorDTO1, vendorDTO2);
        when(vendorService.getAllVendors()).thenReturn(vendorDTOS);
        mockMvc.perform(get(VendorController.BASE_URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()
        ).andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void getVendorById() throws Exception {
        long id = 1L;
        VendorDTO vendorDTO1 = VendorDTO.builder().name("v1").build();
        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO1);
        mockMvc.perform(get(VendorController.BASE_URL+id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", equalTo("v1")));
    }

    @Test
    void createNewVendor() throws Exception {
        long id = 1L;
        VendorDTO vendorDTO1 = VendorDTO.builder().name("v1").build();
        VendorDTO returnVendorDTO = VendorDTO.builder().name("v1").vendor_url(VendorController.BASE_URL+id).build();
        when(vendorService.createNewVendor(ArgumentMatchers.any(VendorDTO.class))).thenReturn(returnVendorDTO);
        mockMvc.perform(post(VendorController.BASE_URL+"create").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(vendorDTO1))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",equalTo("v1")))
        .andExpect(jsonPath("vendor_url", equalTo(VendorController.BASE_URL+id)));
    }

    @Test
    void updateVendorById() throws Exception {
        long id = 1L;
        VendorDTO vendorDTO1 = VendorDTO.builder().name("v1").build();
        VendorDTO returnVendorDTO = VendorDTO.builder().name("updated").vendor_url(VendorController.BASE_URL+id).build();
        when(vendorService.updateVendorById(anyLong(), ArgumentMatchers.any(VendorDTO.class))).thenReturn(returnVendorDTO);
        mockMvc.perform(put(VendorController.BASE_URL+id).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1))).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("updated")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL+id)));


    }

    @Test
    void deleteVendorById() throws Exception {
        long id = 1L;
        mockMvc.perform(delete(VendorController.BASE_URL+id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(vendorService, times(1)).deleteVendorById(anyLong());

    }
}
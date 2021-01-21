package dev.alvarocar.msscbrawery.business.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alvarocar.msscbrawery.business.service.customer.CustomerService;
import dev.alvarocar.msscbrawery.model.dto.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDto validCustomer;

    final String apiV1 = "/api/v1/customer/";

    @Before
    public void setUp() {
        validCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Alvaro")
                .build();
    }

    @Test
    public void testGetCustomer() throws Exception {
        given(customerService.getCustomerById(any(UUID.class))).willReturn(validCustomer);
        mockMvc.perform(get(apiV1 + validCustomer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testHandlepost() throws Exception {
        setUp();
        CustomerDto customer = validCustomer;
        customer.setId(null);
        CustomerDto savedDto = CustomerDto.builder().id(UUID.randomUUID()).name("Alvaro").build();
        String customerDtoJson = objectMapper.writeValueAsString(customer);

        given(customerService.createCustomer(any())).willReturn(savedDto);

        mockMvc.perform(post(apiV1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testHandleupdate() throws Exception {
        //given
        CustomerDto customerDto = validCustomer;
        customerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(customerDto);

        //when
        mockMvc.perform(put(apiV1 + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(customerService).should().updateCustomer(any(), any());
    }

}
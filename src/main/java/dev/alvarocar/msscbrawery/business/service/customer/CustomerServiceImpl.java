package dev.alvarocar.msscbrawery.business.service.customer;

import dev.alvarocar.msscbrawery.model.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .customerID(UUID.randomUUID())
                .nombre("Alvaro")
                .build();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .customerID(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        //Update
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        //Delete
    }
}

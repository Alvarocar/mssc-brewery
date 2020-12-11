package dev.alvarocar.msscbrawery.business.service.customer;

import dev.alvarocar.msscbrawery.model.dto.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
}

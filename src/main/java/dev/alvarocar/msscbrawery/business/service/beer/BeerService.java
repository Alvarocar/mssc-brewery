package dev.alvarocar.msscbrawery.business.service.beer;

import dev.alvarocar.msscbrawery.model.dto.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
    BeerDto saveNewBeer(BeerDto beerDto);
}

package com.juanba.sunnybank.infrastructure.persistance.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressEntity {

    @Column(name = "address_street_us", nullable = false, length = 250)
    private String street;

    @Column(name = "address_city_us", nullable = false, length = 100)
    private String city;

    @Column(name = "address_state_us", nullable = false, length = 100)
    private String state;

    @Column(name = "address_zip_code_us", nullable = false, length = 10)
    private String zipCode;

}

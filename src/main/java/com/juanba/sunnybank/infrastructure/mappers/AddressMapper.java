package com.juanba.sunnybank.infrastructure.mappers;

import com.juanba.sunnybank.domain.model.address.Address;
import com.juanba.sunnybank.infrastructure.persistance.address.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    Address toDomain(AddressEntity entity);
    AddressEntity roEntity(Address domain);
}

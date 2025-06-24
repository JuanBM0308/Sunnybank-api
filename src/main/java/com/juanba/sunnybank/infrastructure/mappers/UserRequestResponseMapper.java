package com.juanba.sunnybank.infrastructure.mappers;

import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.domain.request.user.RegisterRequest;
import com.juanba.sunnybank.domain.response.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {AddressMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestResponseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "firstname", target = "name")
    @Mapping(source = "lastname", target = "surname")
    @Mapping(target = "registerDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "lastLoginDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "bankAccounts", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(source = "password", target = "password")
    User toUser(RegisterRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "identificationNumber", target = "identificationNumber")
    @Mapping(source = "name", target = "firstname")
    @Mapping(source = "surname", target = "lastname")
    @Mapping(source = "email", target = "email")
    RegisterResponse toRegisterResponse(User user);
}

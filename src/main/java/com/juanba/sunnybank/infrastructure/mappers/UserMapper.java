package com.juanba.sunnybank.infrastructure.mappers;

import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, BankAccountMapper.class, NotificationMapper.class, PasswordEncoderMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "identificationType", target = "identificationType")
    @Mapping(source = "identificationNumber", target = "identificationNumber")
    @Mapping(source = "registerDate", target = "registerDate")
    @Mapping(source = "lastLoginDate", target = "lastLoginDate")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "active", target = "isActive")
    @Mapping(source = "bankAccounts", target = "bankAccounts")
    @Mapping(source = "notifications", target = "notifications")
    User toDomain(UserEntity entity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "identificationType", target = "identificationType")
    @Mapping(source = "identificationNumber", target = "identificationNumber")
    @Mapping(source = "registerDate", target = "registerDate")
    @Mapping(source = "lastLoginDate", target = "lastLoginDate")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "password", target = "password", qualifiedByName = "encodePassword")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "active", target = "isActive")
    @Mapping(source = "bankAccounts", target = "bankAccounts")
    @Mapping(source = "notifications", target = "notifications")
    UserEntity toEntity(User domain);
}

package com.juanba.sunnybank.infrastructure.mappers;

import com.juanba.sunnybank.domain.model.notification.Notification;
import com.juanba.sunnybank.infrastructure.persistance.notification.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {
    Notification toDomain(NotificationEntity entity);
    NotificationEntity toEntity(Notification domain);

    List<Notification> toDomainList(List<NotificationEntity> entities);
    List<NotificationEntity> toEntityList(List<Notification> domains);
}

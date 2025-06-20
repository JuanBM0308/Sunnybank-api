package com.juanba.sunnybank.infrastructure.persistance.notification;

import com.juanba.sunnybank.domain.model.user.User;
import com.juanba.sunnybank.infrastructure.persistance.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Message: message is required for customer notifications")
    @Column(name = "message_no")
    private String message;

    @NotNull
    @Column(name = "generation_date_no")
    private LocalDateTime generationDate;

    @NotBlank(message = "Email: necessary to be able to direct the message appropriately")
    @Column(name = "user_email_no")
    private String userEmail;

    //* Relacion de NotificationEntity a UserEntity
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "id_customer_no")
    private User customer;
}

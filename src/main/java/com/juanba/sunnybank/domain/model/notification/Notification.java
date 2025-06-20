package com.juanba.sunnybank.domain.model.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private Long id;
    private String message;
    private LocalDateTime generationDate;
    private Long userId;
    private String userEmail;
}

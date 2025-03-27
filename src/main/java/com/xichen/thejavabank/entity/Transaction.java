package com.xichen.thejavabank.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    private String transactionType;
    private BigDecimal amount;
    private String accountNumber;
    private String status;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}

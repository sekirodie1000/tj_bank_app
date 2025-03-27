package com.xichen.thejavabank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryRequest {
    @Schema(name = "Account Number")
    private String accountNumber;

    @Schema(name = "Password")
    private String password;
}

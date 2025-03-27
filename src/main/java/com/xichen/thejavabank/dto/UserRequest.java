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
public class UserRequest {
    @Schema(name = "First Name")
    private String firstName;

    @Schema(name = "Last Name")
    private String lastName;

    @Schema(name = "Other Name")
    private String otherName;

    @Schema(name = "Gender")
    private String gender;

    @Schema(name = "Address")
    private String address;

    @Schema(name = "Status Of Origin")
    private String statusOfOrigin;

    @Schema(name = "Email")
    private String email;

    @Schema(name = "Phone Number")
    private String phoneNumber;

    @Schema(name = "Alternative Phone Number")
    private String alternativePhoneNumber;

    @Schema(name = "User Status")
    private String status;
}

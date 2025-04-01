package com.xichen.thejavabank.controller;

import com.xichen.thejavabank.dto.*;
import com.xichen.thejavabank.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Management APIs", description = "APIs for creating accounts, balance enquiry, credit, debit and fund transfers")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Create New User Account",
            description = "Creates a new user account and assigns an account number."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User account successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "409", description = "User with given email already exists")
    })
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }

    @PostMapping("/login")
    public BankResponse login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @Operation(
            summary = "Balance Enquiry",
            description = "Retrieves the account balance for the given account number."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account balance retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request) {
        return userService.balanceEnquiry(request);
    }

    @Operation(
            summary = "Name Enquiry",
            description = "Retrieves the account holder's name for the given account number."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account holder name retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request) {
        return userService.nameEnquiry(request);
    }

    @Operation(
            summary = "Credit Account",
            description = "Credits a specified amount to the account."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account credited successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest request) {
        return userService.creditAccount(request);
    }

    @Operation(
            summary = "Debit Account",
            description = "Debits a specified amount from the account if sufficient funds exist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account debited successfully"),
            @ApiResponse(responseCode = "400", description = "Insufficient funds"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest request) {
        return userService.debitAccount(request);
    }

    @Operation(
            summary = "Transfer Funds",
            description = "Transfers a specified amount from one account to another."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funds transferred successfully"),
            @ApiResponse(responseCode = "400", description = "Insufficient funds or invalid accounts"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PostMapping("transfer")
    public BankResponse transfer(@RequestBody TransferRequest request) {
        return userService.transfer(request);
    }
}

package com.xichen.thejavabank.service.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.xichen.thejavabank.controller.UserController;
import com.xichen.thejavabank.dto.EmailDetails;
import com.xichen.thejavabank.entity.Transaction;
import com.xichen.thejavabank.entity.User;
import com.xichen.thejavabank.repository.TransactionRepository;
import com.xichen.thejavabank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class BankStatement {
    private final TransactionRepository transactionRepository;
    private static final String FILE = System.getProperty("user.home") + "/Documents/Mystatement.pdf";
    private EmailService emailService;
    private UserRepository userRepository;

    /*
     * Retrieve list of transactions within a data range given an account number
     * Generate a pdf file of transactions
     * Send the file via email
     */

    public List<Transaction> generateStatement(String accountNumber, String startDate, String endDate){
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        return transactionRepository.findAll().stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .filter(t -> {
                    LocalDate transactionDate = t.getCreatedAt().toLocalDate();
                    return (transactionDate.isEqual(start) || transactionDate.isAfter(start)) &&
                            (transactionDate.isEqual(end) || transactionDate.isBefore(end));
                })
                .collect(Collectors.toList());
    }

    private void designStatement(List<Transaction> transactions, String accountNumber, String startDate, String endDate) throws Exception {
        User user = userRepository.findByAccountNumber(accountNumber);

        Document document = new Document(PageSize.A4);
        OutputStream outputStream = new FileOutputStream(FILE);
        PdfWriter.getInstance(document, outputStream);
        document.open();


        PdfPTable bankNameTable = new PdfPTable(1);
        bankNameTable.setWidthPercentage(100);
        PdfPCell bankNameCell = new PdfPCell(new Phrase("The Java Bank"));
        bankNameCell.setBorder(0);
        bankNameCell.setBackgroundColor(BaseColor.BLUE);
        bankNameCell.setPadding(20f);
        bankNameCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        bankNameTable.addCell(bankNameCell);
        document.add(bankNameTable);

        PdfPTable headerDetails = new PdfPTable(2);
        headerDetails.setWidthPercentage(100);
        headerDetails.setSpacingBefore(20f);
        headerDetails.setSpacingAfter(20f);
        headerDetails.setWidths(new float[]{1, 1});


        PdfPCell leftCell = new PdfPCell();
        leftCell.setBorder(0);
        leftCell.addElement(new Phrase("Bank Address: No.1, Some Road, Some City"));
        leftCell.addElement(new Phrase("Start Date: " + startDate));
        leftCell.addElement(new Phrase("End Date: " + endDate));

        PdfPCell rightCell = new PdfPCell();
        rightCell.setBorder(0);
        rightCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        rightCell.addElement(new Phrase("Statement of Account"));

        if(user != null) {
            rightCell.addElement(new Phrase("Customer Name: " + user.getFirstName() + " " + user.getLastName() + ", " + user.getOtherName()));
            rightCell.addElement(new Phrase("Customer Address: " + user.getAddress()));
        } else {
            rightCell.addElement(new Phrase("Customer information not found"));
        }


        headerDetails.addCell(leftCell);
        headerDetails.addCell(rightCell);
        document.add(headerDetails);

        PdfPTable transactionTable = new PdfPTable(4);
        transactionTable.setWidthPercentage(100);
        transactionTable.setSpacingBefore(10f);
        transactionTable.setSpacingAfter(10f);

        PdfPCell cellDate = new PdfPCell(new Phrase("Date"));
        PdfPCell cellType = new PdfPCell(new Phrase("Type"));
        PdfPCell cellAmount = new PdfPCell(new Phrase("Amount"));
        PdfPCell cellStatus = new PdfPCell(new Phrase("Status"));
        cellDate.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellType.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellAmount.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellStatus.setBackgroundColor(BaseColor.LIGHT_GRAY);

        transactionTable.addCell(cellDate);
        transactionTable.addCell(cellType);
        transactionTable.addCell(cellAmount);
        transactionTable.addCell(cellStatus);

        for (Transaction t : transactions) {
            String date = t.getCreatedAt() != null ? t.getCreatedAt().toLocalDate().toString() : "N/A";
            transactionTable.addCell(new PdfPCell(new Phrase(date)));
            transactionTable.addCell(new PdfPCell(new Phrase(t.getTransactionType())));
            transactionTable.addCell(new PdfPCell(new Phrase(t.getAmount().toString())));
            transactionTable.addCell(new PdfPCell(new Phrase(t.getStatus())));
        }
        document.add(transactionTable);

        document.close();
        outputStream.close();
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("STATEMENT OF ACCOUNT")
                .messageBody("Kindly find your request account statement attached!")
                .attachment(FILE)
                .build();

        emailService.sendEmailWIthAttachment(emailDetails);
        log.info("Bank statement generated and saved at: " + FILE);
    }


    public void generateBankStatementPDF(String accountNumber, String startDate, String endDate) {
        List<Transaction> transactions = generateStatement(accountNumber, startDate, endDate);
        try {
            designStatement(transactions, accountNumber, startDate, endDate);
        } catch (Exception e) {
            log.error("Error generating bank statement PDF", e);
        }
    }

}

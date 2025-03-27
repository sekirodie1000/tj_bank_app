package com.xichen.thejavabank.service.impl;

import com.xichen.thejavabank.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
    void sendEmailWIthAttachment(EmailDetails emailDetails);
}

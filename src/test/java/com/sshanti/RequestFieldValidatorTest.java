package com.sshanti;

import com.sshanti.datawarehouse.boundary.request.FXDealRequest;
import com.sshanti.datawarehouse.util.RequestFieldValidator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


import static com.sshanti.datawarehouse.util.RequestFieldValidator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestFieldValidatorTest {

    @Test
    void testValidateRequestFields_ValidRequest() {

        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 00, 00, 00);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        // Create a valid FXDealRequest for testing
        FXDealRequest fxDealRequest = new FXDealRequest();
        fxDealRequest.setTimestamp(date);
        fxDealRequest.setFromCurrency("JOD");
        fxDealRequest.setToCurrency("USD");
        fxDealRequest.setAmount(BigDecimal.valueOf(100));

        List<String> validationMessages = RequestFieldValidator.validateRequestFields(fxDealRequest);

        assertEquals(0, validationMessages.size());
    }

    @Test
    void testValidateRequestFields_NullTimestamp() {
        // Create an invalid FXDealRequest with a null timestamp
        FXDealRequest fxDealRequest = new FXDealRequest();
        fxDealRequest.setFromCurrency("USD");
        fxDealRequest.setToCurrency("AUD");
        fxDealRequest.setAmount(BigDecimal.valueOf(100));

        List<String> validationMessages = RequestFieldValidator.validateRequestFields(fxDealRequest);

        assertEquals(1, validationMessages.size());
        assertEquals(NULL_TIME_STAMP_ERROR_MESSAGE, validationMessages.get(0));
    }
    @Test
    void testValidateRequestFields_PastTimestamp() {
        // Create an invalid FXDealRequest with a past timestamp
        FXDealRequest fxDealRequest = new FXDealRequest();
        fxDealRequest.setTimestamp(Date.from(LocalDateTime.of(2020, 1, 1, 12, 0, 0)
                .atZone(ZoneId.systemDefault()).toInstant()));
        fxDealRequest.setFromCurrency("USD");
        fxDealRequest.setToCurrency("EUR");
        fxDealRequest.setAmount(BigDecimal.valueOf(100));

        List<String> validationMessages = RequestFieldValidator.validateRequestFields(fxDealRequest);

        assertEquals(1, validationMessages.size());
        assertEquals(TIME_STAMP_ERROR_MESSAGE, validationMessages.get(0));
    }

    @Test
    void testValidateRequestFields_NullCurrencies() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 00, 00, 00);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        FXDealRequest fxDealRequest = new FXDealRequest();
        fxDealRequest.setTimestamp(date);
        fxDealRequest.setFromCurrency(null);
        fxDealRequest.setToCurrency(null);
        fxDealRequest.setAmount(BigDecimal.valueOf(100));

        List<String> validationMessages = RequestFieldValidator.validateRequestFields(fxDealRequest);

        assertEquals(1, validationMessages.size());
        assertEquals(NULL_CURRENCY_ERROR_MESSAGE, validationMessages.get(0));
    }

    @Test
    void testValidateRequestFields_MatchCurrencies() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 00, 00, 00);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        FXDealRequest fxDealRequest = new FXDealRequest();
        fxDealRequest.setTimestamp(date);
        fxDealRequest.setFromCurrency("JOD");
        fxDealRequest.setToCurrency("JOD");
        fxDealRequest.setAmount(BigDecimal.valueOf(100));

        List<String> validationMessages = RequestFieldValidator.validateRequestFields(fxDealRequest);

        assertEquals(1, validationMessages.size());
        assertEquals(MATCH_CURRENCY_ERROR_MESSAGE, validationMessages.get(0));
    }

    @Test
    void testValidateRequestFields_InvalidFromCurrency() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 00, 00, 00);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        FXDealRequest fxDealRequest = new FXDealRequest();
        fxDealRequest.setTimestamp(date);
        fxDealRequest.setFromCurrency("XYZ"); // Invalid currency code
        fxDealRequest.setToCurrency("JOD");
        fxDealRequest.setAmount(BigDecimal.valueOf(100));

        List<String> validationMessages = RequestFieldValidator.validateRequestFields(fxDealRequest);

        assertEquals(1, validationMessages.size());
        assertEquals(FROM_CURRENCY_ERROR_MESSAGE, validationMessages.get(0));
    }

    @Test
    void testValidateRequestFields_InvalidToCurrency() {

        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 00, 00, 00);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        // Create an invalid FXDealRequest with an invalid To currency code
        FXDealRequest fxDealRequest = new FXDealRequest();
        fxDealRequest.setTimestamp(date);
        fxDealRequest.setFromCurrency("USD");
        fxDealRequest.setToCurrency("XYZ"); // Invalid currency code
        fxDealRequest.setAmount(BigDecimal.valueOf(100));


        List<String> validationMessages = RequestFieldValidator.validateRequestFields(fxDealRequest);

        assertEquals(1, validationMessages.size());
        assertEquals(TO_CURRENCY_ERROR_MESSAGE, validationMessages.get(0));
    }

    @Test
    void testValidateRequestFields_ZeroAmount() {
        // Create an invalid FXDealRequest with zero amount
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 00, 00, 00);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        FXDealRequest fxDealRequest = new FXDealRequest();
        fxDealRequest.setTimestamp(date);
        fxDealRequest.setFromCurrency("USD");
        fxDealRequest.setToCurrency("EUR");
        fxDealRequest.setAmount(BigDecimal.ZERO); // Zero amount

        List<String> validationMessages = RequestFieldValidator.validateRequestFields(fxDealRequest);

        assertEquals(1, validationMessages.size());
        assertEquals(AMOUNT_ERROR_MESSAGE, validationMessages.get(0));
    }
}

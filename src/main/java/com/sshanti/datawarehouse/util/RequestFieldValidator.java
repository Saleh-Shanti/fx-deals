package com.sshanti.datawarehouse.util;

import com.sshanti.datawarehouse.boundary.request.FXDealRequest;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RequestFieldValidator {
    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(RequestFieldValidator.class);
    public static final String TIME_STAMP_ERROR_MESSAGE = "Time Stamp Cannot be in the past.";
    public static final String NULL_TIME_STAMP_ERROR_MESSAGE = "Time Stamp Cannot be null.";
    public static final String AMOUNT_ERROR_MESSAGE = "Amount cannot be less than or equal zero.";
    public static final String FROM_CURRENCY_ERROR_MESSAGE = "From Currency is not valid.";
    public static final String TO_CURRENCY_ERROR_MESSAGE = "To Currency is not valid.";
    public static final String NULL_CURRENCY_ERROR_MESSAGE = "From-To currency code cannot be null.";
    public static final String MATCH_CURRENCY_ERROR_MESSAGE = "From-To currency code cannot be the same.";

    public static List<String> validateRequestFields(FXDealRequest request) {
        logger.info("Validating Request Fields....");

        List<String> validationMessages = new ArrayList<>();
        try {

            validateDate(validationMessages, request.getTimestamp());
            validateCurrencyCode(validationMessages, request);
            validateAmount(validationMessages, request.getAmount());

        } catch (Exception e) {
            logger.warn("Error while validating request fields : " + e.getMessage(), e);
            validationMessages.add(e.getMessage());
        }
        return validationMessages;
    }

    private static void validateDate(List<String> validationMessages, Date timestamp) {
        if (timestamp == null ) validationMessages.add(NULL_TIME_STAMP_ERROR_MESSAGE);
        else if(timestamp.before(new Date())) validationMessages.add(TIME_STAMP_ERROR_MESSAGE);
    }

    private static void validateAmount(List<String> validationMessages, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            validationMessages.add(AMOUNT_ERROR_MESSAGE);
    }

    private static void validateCurrencyCode(List<String> validationMessages, FXDealRequest request) {
        if (isNullOrEmpty(request.getToCurrency()) || isNullOrEmpty(request.getFromCurrency())) {
            logger.warn(NULL_CURRENCY_ERROR_MESSAGE);
            validationMessages.add(NULL_CURRENCY_ERROR_MESSAGE);
            return;
        }

        if (request.getFromCurrency().equalsIgnoreCase(request.getToCurrency())) {
            logger.warn(MATCH_CURRENCY_ERROR_MESSAGE);
            validationMessages.add(MATCH_CURRENCY_ERROR_MESSAGE);
            return;
        }

        boolean isValidFromCurrencyCode = Arrays.stream(CurrencyISOCode.values())
                .anyMatch(code -> code.name().equals(request.getFromCurrency().toUpperCase()));

        boolean isValidToCurrencyCode = Arrays.stream(CurrencyISOCode.values())
                .anyMatch(code -> code.name().equals(request.getToCurrency().toUpperCase()));

        if (!isValidFromCurrencyCode) validationMessages.add(FROM_CURRENCY_ERROR_MESSAGE);
        if (!isValidToCurrencyCode) validationMessages.add(TO_CURRENCY_ERROR_MESSAGE);
    }

    private static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}

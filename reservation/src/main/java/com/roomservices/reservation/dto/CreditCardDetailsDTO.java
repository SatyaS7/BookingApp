package com.roomservices.reservation.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * A basic wrapper around credit card details
 */
public class CreditCardDetailsDTO {

    @NotNull
    private String cardOwner;

    @Pattern(regexp = "\\b(?:4[0-9]{12}(?:[0-9]{3})?|" + "5[12345][0-9]{14}|3[47][0-9]{13}|" + "3(?:0[012345]|[68][0-9])[0-9]{11}|" + "6(?:011|5[0-9]{2})[0-9]{12}|"
                    + "(?:2131|1800|35[0-9]{3})[0-9]{11})\\b")

    private String cardNumber;
    @Pattern(regexp = "[0-9]{2}/[0-9]{2}")
    private String expiration;
    @Pattern(regexp = "[0-9]{3,4}")
    private String cvv;

    public CreditCardDetailsDTO() {
    }

    public CreditCardDetailsDTO(String cardOwner, String cardNumber, String expiration, String ccv) {
        this.cardOwner = cardOwner;
        if (cardNumber != null) {
            cardNumber = cardNumber.replaceAll("[\\s\\-]", "");
        }
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.cvv = ccv;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getCvv() {
        return cvv;
    }

    @Override
    public String toString() {
        String ts = ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        // we don't want to log the full card number
        ts = ts.replaceAll("cardNumber=\\d{12}", "cardNumber=XXXX XXXX XXXX ");
        return ts;
    }
}
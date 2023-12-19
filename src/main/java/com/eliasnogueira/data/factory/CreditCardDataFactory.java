/*
 * MIT License
 *
 * Copyright (c) 2021 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.eliasnogueira.data.factory;

import com.eliasnogueira.model.CreditCard;
import com.eliasnogueira.model.CreditCardBuilder;
import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.eliasnogueira.data.changeless.GeneralData.YEAR_DATE_PATTERN;
import static java.lang.String.format;
import static java.lang.String.valueOf;

public class CreditCardDataFactory {

    private final Faker faker;
    private static final Logger log = LogManager.getLogger(CreditCardDataFactory.class);

    public CreditCardDataFactory() {
        faker = new Faker();
    }

    /**
     * Generate a valid credit card object with all correct information
     *
     * @return valid credit card object
     */
    public CreditCard validCreditCard() {
        var valid = validBuilder().createCreditCard();
        log.info(valid);

        return valid;
    }

    /**
     * Generate an invalid credit card object with an invalid credit card number
     *
     * @return invalid credit card object with an invalid credit card number
     */
    public CreditCard invalidCreditCardNumber() {
        var invalid = validBuilder().number(faker.number().digits(10)).createCreditCard();
        log.info(invalid);

        return invalid;
    }

    /**
     * Generate an invalid credit card object with an invalid month expiration date
     *
     * @return month expiration date between 13 and 20
     */
    public CreditCard invalidMonth() {
        var invalid = validBuilder().expirationDateMonth(
                valueOf(faker.number().numberBetween(13, 20))).createCreditCard();
        log.info(invalid);

        return invalid;
    }

    /**
     * Generate an invalid credit card object with an invalid year expiration date
     *
     * @return four (4) to ten (10) years ahead
     */
    public CreditCard invalidYear() {
        LocalDate yearIncremented = LocalDate.now().plusYears(faker.number().numberBetween(4, 10));
        var year = yearIncremented.format(DateTimeFormatter.ofPattern(YEAR_DATE_PATTERN));

        var invalid = validBuilder().expirationDateYear(year).createCreditCard();
        log.info(invalid);

        return invalid;
    }

    /**
     * Generate an invalid credit card object with an invalid CCV
     *
     * @return CCV with only characteres (no numbers)
     */
    public CreditCard invalidCcv() {
        var invalid = validBuilder().ccv(RandomStringUtils.randomAlphabetic(3)).createCreditCard();
        log.info(invalid);

        return invalid;
    }

    /**
     * Generates a valid builder object to be used by all the data generation cases
     *
     * @return valid credit card information as the builder object
     */
    private CreditCardBuilder validBuilder() {
        var creditCardNumber = faker.business().creditCardNumber().replace("-", "");

        return new CreditCardBuilder()
                .owner(faker.name().name())
                .number(creditCardNumber)
                .ccv(faker.number().digits(3))
                .expirationDateMonth(validRandomExpirationDateMonth())
                .expirationDateYear(validRandomExpirationDateYear());
    }

    /**
     * Generate the two digits month
     * <p>
     * The String.format() is adding a zero on the left side of the String.
     * When the number has two digits (10, 11, 12) no zero is added.
     *
     * @return a valid two digits month
     */
    private String validRandomExpirationDateMonth() {
        int month = faker.number().numberBetween(1, 12);
        return format("%02d", month);
    }

    /**
     * Generates a valid year from today's date in the maximum 3 years ahead
     *
     * @return valid two digits year
     */
    private String validRandomExpirationDateYear() {
        LocalDate yearIncremented = LocalDate.now().plusYears(faker.number().numberBetween(1, 3));
        return yearIncremented.format(DateTimeFormatter.ofPattern(YEAR_DATE_PATTERN));
    }
}

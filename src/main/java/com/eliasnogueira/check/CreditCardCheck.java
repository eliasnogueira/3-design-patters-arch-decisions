/*
 * Copyright (c) 2021 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.eliasnogueira.check;

import com.eliasnogueira.model.CreditCard;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.eliasnogueira.data.changeless.GeneralData.INVALID_DATA;

public class CreditCardCheck {

    private CreditCard creditCard;

    public Boolean isValid(CreditCard creditCard) throws CreditCardException {
        this.creditCard = creditCard;

        if (!isValidNumber()) throw new CreditCardException(INVALID_DATA);
        if (!isValidCcv()) throw new CreditCardException(INVALID_DATA);
        if (!isValidMonth()) throw new CreditCardException(INVALID_DATA);
        if (!isValidYear()) throw new CreditCardException(INVALID_DATA);

        return true;
    }

    private boolean isValidNumber() {
        var number = creditCard.getNumber();

        return NumberUtils.isCreatable(number) && number.length() == 16;
    }

    private boolean isValidCcv() {
        return NumberUtils.isCreatable(creditCard.getCcv());
    }

    private boolean isValidMonth() {
        var validMonths = List.of("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

        return ArrayUtils.contains(validMonths.toArray(), creditCard.getExpirationDateMonth());
    }

    private boolean isValidYear() {
        var expirationDate = Integer.parseInt(creditCard.getExpirationDateYear());
        var threeYears = Integer.parseInt(LocalDate.now().plusYears(3).format(DateTimeFormatter.ofPattern("yy")));

        int diff = expirationDate - threeYears;

        return diff <= 0;
    }
}

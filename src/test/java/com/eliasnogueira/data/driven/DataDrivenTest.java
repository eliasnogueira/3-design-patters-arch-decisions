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
package com.eliasnogueira.data.driven;

import com.eliasnogueira.check.CreditCardCheck;
import com.eliasnogueira.model.CreditCard;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.eliasnogueira.data.changeless.GeneralData.INVALID_DATA;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DataDrivenTest {

    private final CreditCardCheck creditCardCheck = new CreditCardCheck();

    @ParameterizedTest(name = "''{0}''")
    @ArgumentsSource(CreditCardDataDriven.class)
    void invalidCreditCard(CreditCard creditCard) {
        assertThatThrownBy(() -> creditCardCheck.isValid(creditCard)).hasMessage(INVALID_DATA);
    }
}

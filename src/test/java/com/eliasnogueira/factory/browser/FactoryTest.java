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
package com.eliasnogueira.factory.browser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.eliasnogueira.data.changeless.BrowserData.CHROME;
import static com.eliasnogueira.data.changeless.BrowserData.FIREFOX;
import static org.assertj.core.api.Assertions.assertThat;

class FactoryTest {

    private WebDriver driver;

    @Test
    void chromeTest() {
        driver = BrowserFactory.valueOf(CHROME).createDriver();
        String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();

        assertThat(browserName).isEqualToIgnoringCase(CHROME);
    }

    @Test
    void firefoxTest() {
        driver = BrowserFactory.valueOf(FIREFOX).createDriver();
        String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
        assertThat(browserName).isEqualToIgnoringCase(FIREFOX);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}

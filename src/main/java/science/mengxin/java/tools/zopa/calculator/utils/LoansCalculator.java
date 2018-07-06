/*
 * Copyright 2018 Xin Meng (https://mengxin.science)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package science.mengxin.java.tools.zopa.calculator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import science.mengxin.java.tools.zopa.calculator.model.LoansRepayment;

/**
 * Class for the loan related calculator method.
 */
public class LoansCalculator {

    private static Logger log = LoggerFactory.getLogger(LoansCalculator.class);

    /**
     * Convert the year Rate to month rate by using monthly compounding interest
     *
     * @param yearRateInDecimal Year Rate
     * @return Month Rate
     */
    private static Double yearRateToMonthRate(Double yearRateInDecimal) {
        yearRateInDecimal = 1 + yearRateInDecimal;
        return Math.pow(yearRateInDecimal, 1d / 12d) - 1d;
    }


    /**
     * Calculate the payment information based on the parameters.
     *
     * @param loanAmount        the total amount of loan
     * @param termInYears       the term in years, such as 3 years: 3
     * @param yearRateInDecimal the year interest rate without percent symbol. such as 7% should input 0.07.
     * @return LoansRepayment with monthly payment and total payment information.
     */
    public static LoansRepayment calculateRepayment(Double loanAmount, Integer termInYears, Double yearRateInDecimal) {
        Double monthlyRate = yearRateToMonthRate(yearRateInDecimal);
        // The length of the term in months
        Integer termInMonths = termInYears * 12;
        // Calculate the monthly payment
        Double monthlyPayment =
                (loanAmount * monthlyRate) /
                        (1d - Math.pow(1d + monthlyRate, -termInMonths));
        return new LoansRepayment(monthlyPayment, monthlyPayment * termInMonths);
    }

    public static LoansRepayment calculateRepaymentMonthNotCompounding(Double loanAmount, Integer termInYears, Double yearRateInDecimal) {
        Double monthlyRate = yearRateInDecimal / 12d;
        // The length of the term in months
        Integer termInMonths = termInYears * 12;
        // Calculate the monthly payment
        Double monthlyPayment =
                (loanAmount * monthlyRate) /
                        (1d - Math.pow(1d + monthlyRate, -termInMonths));
        return new LoansRepayment(monthlyPayment, monthlyPayment * termInMonths);
    }
}

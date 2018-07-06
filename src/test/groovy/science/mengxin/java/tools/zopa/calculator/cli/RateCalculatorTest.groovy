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

package science.mengxin.java.tools.zopa.calculator.cli

import spock.lang.Specification
import spock.lang.Unroll


/**
 * This test is used for testing the requirement.
 */
class RateCalculatorTest extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def months = 36

    @Unroll
    def "test double multiple 100"() {
        when:
        Double d1 = d
        Double x = d1 * 100
        then:
        println("Normal : " + x)

        when:
        BigDecimal a1 = new BigDecimal(Double.toString(d));
        BigDecimal b1 = new BigDecimal(Double.toString(100));
        BigDecimal result = a1 * b1;
        println("BigDecimal: " + result);
        BigDecimal one = new BigDecimal("1");
        double a = result.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue()
        System.out.println(a);
        then:
        System.out.println(a);
        where:
        d        || resoult
        538.865d || ""
        0.075    || ""
        0.069    || ""
        0.071    || ""
        0.104    || ""
        0.081    || ""
        0.074    || ""
        0.071    || ""
        0.03     || ""
        0.118    || ""
        0.138    || ""
        0.148    || ""
        0.188    || ""
        0.198    || ""
    }

    /**
     * This case
     */
    def "quick learning how to calculate the repayment"() {
        given: "basic information"
        double monthReplay = 0.0D
        int loanAmount = 1000
        double rate = 7.0
        when: "calculate monthly repayment"
        monthReplay = calculateMonthlyPayment(loanAmount, 3, rate)
        then:
        monthReplay
        println(monthReplay)
        println(monthReplay * months)

    }

    static double calculateMonthlyPayment(
            int loanAmount, int termInYears, double interestRate) {

        // Convert interest rate into a decimal
        // eg. 6.5% = 0.065

        //interestRate /= 100.0;
        double monthlyRate = monthRateCal(interestRate)

        // The length of the term in months
        // is the number of years times 12

        int termInMonths = termInYears * 12

        // Calculate the monthly payment
        // Typically this formula is provided so
        // we won't go into the details

        // The Math.pow() method is used calculate values raised to a power

        double monthlyPayment =
                (loanAmount * monthlyRate) /
                        (1 - Math.pow(1 + monthlyRate, -termInMonths))
//        double monthlyPayment =
//                (loanAmount*monthlyRate * Math.pow(1+monthlyRate, termInMonths)) /
//                        (Math.pow(1+monthlyRate, termInMonths) - 1);
        return monthlyPayment
    }

    // interestRate == 7  %
    static double monthRateCal(double interestRate) {
        interestRate = 1 + interestRate / 100
        return Math.pow(interestRate, 1 / 12) - 1
    }
}

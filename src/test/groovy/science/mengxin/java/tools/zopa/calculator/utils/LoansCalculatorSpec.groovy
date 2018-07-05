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

package science.mengxin.java.tools.zopa.calculator.utils

import spock.lang.Specification
import spock.lang.Unroll

class LoansCalculatorSpec extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    def "test calculateRepayment #loanAmount #termInYears and #yearRateInDecimal"() {
        when:
        LoansRepayment loansRepayment = LoansCalculator.calculateRepayment(loanAmount, termInYears, yearRateInDecimal)
        then:
        println(loansRepayment.getMonthlyRepayment().toString())
        println(loansRepayment.getTotalRepayment().toString())

        loansRepayment.getMonthlyRepayment().round(2) == expectedMonthlyRepayment
        loansRepayment.getTotalRepayment().round(2) == expectedTotalRepayment

        //the expected result is calculated by trusted 3rd party website
        //   https://www.moneysupermarket.com/loans/calculator/
        where:
        loanAmount | termInYears | yearRateInDecimal || expectedMonthlyRepayment || expectedTotalRepayment
        1000d      | 3           | 0.07d             || 30.78d                   || 1108.04d
        2000d      | 6           | 0.208d            || 46.81d                   || 3370.06d
        4500d      | 4           | 0.09d             || 111.23d                  || 5339.22d
        6000d      | 5           | 0.13d             || 134.33d                  || 8059.80d
        15000d     | 2           | 0.09d             || 682.86d                  || 16388.56d
    }
}

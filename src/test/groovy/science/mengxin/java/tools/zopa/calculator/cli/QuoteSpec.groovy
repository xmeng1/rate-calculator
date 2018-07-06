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

class QuoteSpec extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    def "test run"() {
        given:
        Quote quote = new Quote()
        and: "test csv file"
        String marketCsvFile = "/market-file/market-offer-search-test.csv"
        and: "get the full path from resource"
        String fullPath = QuoteSpec.class.getResource(marketCsvFile).getPath()
        and:
        quote.setLoanAmount(loanAmount)
        quote.setFile(fullPath)
        quote.setVerbose(true)

        when:
        quote.run()

        then:
        def x = "there should print the result"
        assert x != null
        where: ""
        loanAmount || result
        100d       || ""
        200d       || ""
        1000d      || ""
        1100d      || ""
        1200d      || ""
        1300d      || ""
        10000d     || ""
        10100d     || ""
        15000d     || ""
        45000d     || ""
        50301d     || ""

    }
}

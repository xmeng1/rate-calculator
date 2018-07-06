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

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import science.mengxin.java.tools.zopa.calculator.model.LenderOffer
import spock.lang.Specification
import spock.lang.Unroll

class OfferSearchUtilsSpec extends Specification {

    private static Logger log = LoggerFactory.getLogger(OfferSearchUtilsSpec.class)

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    def "test getBestSingleLenderOffer #loanAmount wth offer #name"() {
        given: "test csv file"
        String marketCsvFile = "/market-file/market-offer-search-test.csv"
        and: "get the full path from resource"
        String fullPath = CsvUtilsSpec.class.getResource(marketCsvFile).getPath()

        and: "load the data from csv"
        List<LenderOffer> lenderOfferList = CsvUtils.loadOffersFromCsv(fullPath)

//        and: "define loanAmount"
//        Double loanAmount = 100d

        when:
        LenderOffer bestOffer = OfferSearchUtils.getBestSingleLenderOffer(lenderOfferList, loanAmount)

        then:
        if (existing) {
            assert bestOffer != null
            assert bestOffer.getName() == name
            log.info("loanAmount {} and offer: {}", loanAmount, bestOffer.toString())
        } else {
            log.info("loanAmount {} cannot find", loanAmount)
        }
        where:
        loanAmount || existing || name
        100d       || true     || "Alice"
        200d       || true     || "Jane"
        1000d      || true     || "Alex"
        1100d      || true     || "Alex"
        1200d      || true     || "Alex"
        1300d      || true     || "May"
        10000d     || true     || "Sunny"
        10100d     || true     || "Sunny"
        15000d     || true     || "Sunny"
        45000d     || false    || "null"

    }
}

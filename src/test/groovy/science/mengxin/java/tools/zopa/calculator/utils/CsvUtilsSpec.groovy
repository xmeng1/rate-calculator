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
import science.mengxin.java.tools.zopa.calculator.exception.CsvParseException
import science.mengxin.java.tools.zopa.calculator.model.LenderOffer
import spock.lang.Specification
import spock.lang.Unroll

class CsvUtilsSpec extends Specification {

    private static Logger log = LoggerFactory.getLogger(CsvUtilsSpec.class)

    def setup() {
    }

    def cleanup() {
    }


    @Unroll
    def "test loadOffersFromCsv #fileName"() {
        given: "test csv file"
        String marketCsvFile = fileName
        and: "get the full path from resource"
        String fullPath = CsvUtilsSpec.class.getResource(marketCsvFile).getPath()

        when: "load the data from csv"
        List<LenderOffer> lenderOfferList = CsvUtils.loadOffersFromCsv(fullPath)
        then: "the lender offer list should not be null"
        if (!testError) {
            assert lenderOfferList
            assert lenderOfferList.size() == size
        }
        where: """define different csv as input, if test error is true, the csv file should have some error 
                        and throw exception"""
        fileName                        | testError || success || size
        "/market-file/market-test1.csv" | false     || true    || 7
    }


    @Unroll
    def "test loadOffersFromCsv with exception #fileName"() {
        given: "test csv file"
        String marketCsvFile = fileName
        and: "get the full path from resoure"
        String fullPath = CsvUtilsSpec.class.getResource(marketCsvFile).getPath()

        when: "load the data from csv"
        List<LenderOffer> lenderOfferList = CsvUtils.loadOffersFromCsv(fullPath)
        then: "should throw error"
        // throw exception to match the errorMsg
        def error = thrown(expectedException)
        log.info(error.getMessage())
        assert error.getMessage() == exceptionMsg

        where: """define different csv as input, if test error is true, the csv file should have some error 
                        and throw exception"""
        fileName                                    | testError || expectedException | exceptionMsg
        "/market-file/market-test-double-error.csv" | true      || CsvParseException | "For input string: \"WrongDouble\""
        "/market-file/market-test-header-error.csv" | true      || CsvParseException | "Mapping for Lender not found, expected one of [LenderX, Rate1, Available]"
    }
}

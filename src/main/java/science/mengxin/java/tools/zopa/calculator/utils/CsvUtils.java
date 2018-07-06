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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import science.mengxin.java.tools.zopa.calculator.exception.CsvParseException;
import science.mengxin.java.tools.zopa.calculator.model.LenderOffer;
import science.mengxin.java.tools.zopa.calculator.model.LenderOfferCsvColumn;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is utilities of CSV file handler
 */
public class CsvUtils {

    public static List<LenderOffer> loadOffersFromCsv(String fileFullPath) throws IOException, CsvParseException {
        Reader in = new FileReader(fileFullPath);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        List<LenderOffer> lenderOfferList = new ArrayList<>();
        try {
            for (CSVRecord record : records) {
                lenderOfferList.add(new LenderOffer(
                        record.get(LenderOfferCsvColumn.LENDER.getName()),
                        Double.valueOf(record.get(LenderOfferCsvColumn.RATE.getName())),
                        Double.valueOf(record.get(LenderOfferCsvColumn.AVAILABLE.getName()))));
            }
        } catch (Exception e) {
            throw new CsvParseException(e.getMessage(), e.getCause());
        }
        return lenderOfferList;
    }
}

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

package science.mengxin.java.tools.zopa.calculator.cli;

import io.airlift.airline.Command;
import io.airlift.airline.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import science.mengxin.java.tools.zopa.calculator.exception.CsvParseException;
import science.mengxin.java.tools.zopa.calculator.model.LenderOffer;
import science.mengxin.java.tools.zopa.calculator.model.LoansRepayment;
import science.mengxin.java.tools.zopa.calculator.utils.CsvUtils;
import science.mengxin.java.tools.zopa.calculator.utils.LoansCalculator;
import science.mengxin.java.tools.zopa.calculator.utils.OfferSearchUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.List;

@Command(name = "quote", description = "Quote the best offer")
public class Quote extends RateCommand {

    private static Logger log = LoggerFactory.getLogger(Quote.class);

    //    @Arguments(required = true, title="file", description = "The full path of market file")
    //    public String file;

    @Option(required = true, name = "-f", description = "The full path of market file")
    private String file;

    @Option(required = true, name = "-l", description = "The amount of loan which support Double")
    private Double loanAmount;

    @Override
    public void run() {
        if (verbose) {
            System.out.println("Args [file:" + file + ", loan amount:"
                    + String.valueOf(loanAmount) + "]");
        }
        // read data from the file
        List<LenderOffer> lenderOfferList;
        try {
            lenderOfferList = CsvUtils.loadOffersFromCsv(file);
        } catch (IOException | CsvParseException e) {
            System.out.println("Try to get the market data from the file" + file +
                    ", but failed. " + e.getMessage());
            log.error("Get file {} but get error {}", file, e.getMessage());
            return;
        }
        // get the best offer
        LenderOffer bestOffer = OfferSearchUtils.getBestSingleLenderOffer(lenderOfferList, loanAmount);
        if (bestOffer == null) {
            System.out.println("Cannot get best offer for amount " + loanAmount +
                    " in data " + file);
            log.warn("Cannot get best offer for amount {} in data {}", loanAmount, file);
        } else {
            // compute the repayment
            System.out.println("The base offer for " + loanAmount + " in data " + file + " is as follows:");
            LoansRepayment loansRepayment = LoansCalculator.calculateRepayment(loanAmount, 3, bestOffer.getRate());

            DecimalFormat df = new DecimalFormat("#.##");

            StringBuilder sb = new StringBuilder();
            String pound = "£";
            sb.append("Requested amount: ").append(new String(pound.getBytes(), StandardCharsets.UTF_8))
                    .append(loanAmount).append("\n");
            sb.append("Rate: ").append(bestOffer.getRate()).append("%\n");
            sb.append("Monthly repayment: ").append(new String(pound.getBytes(), StandardCharsets.UTF_8))
                    .append(Double.valueOf(df.format(loansRepayment.getMonthlyRepayment()))).append("\n");
            sb.append("  Total repayment: ").append(new String(pound.getBytes(), StandardCharsets.UTF_8))
                    .append(Double.valueOf(df.format(loansRepayment.getTotalRepayment()))).append("\n");
            if (verbose) {
                sb.append("Best offer is:").append(bestOffer.toString()).append("\n");
            }

            System.out.println(sb.toString());

        }
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }
}

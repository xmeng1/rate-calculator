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

import science.mengxin.java.tools.zopa.calculator.model.LenderOffer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OfferSearchUtils {

    /**
     * Get the best single lender offer to meet the requirement of loan amount.
     * First check the available whose equal to or bigger than the loan amount
     * Then find the lowest rate in all the offers whose available can cover the loan amount.
     *
     * @param lenderOfferList List of lend offer
     * @param loanAmount      the double loan amount
     * @return the best lender offer
     */
    public static LenderOffer getBestSingleLenderOffer(List<LenderOffer> lenderOfferList, Double loanAmount) {
        LenderOffer[] lenderOffers =  lenderOfferList.toArray(new LenderOffer[0]);
        // sort the offers by the available ascending order
        Arrays.sort(lenderOffers, Comparator.comparingDouble(LenderOffer::getAvailable));
        // find the margin of available to match loanAmount
        LenderOffer temp = new LenderOffer();
        temp.setAvailable(loanAmount);
        // get the margin which whose available is bigger or equal to loanAmount (because the order is ascending
        //  so the offers after the margin and offer of margin cloud be candidates for borrower)
        //  We make use of the Arrays.binarySearch to find the margin
        //  if search result >= 0, which mean it find the correct amount of available which equal to the loanAmount
        //  if search result < 0, which mean cannot find, but find the insertion point, -(insertion_point)-1=r,
        //  for example, return -2, which mean the loanAmount should be inserted at -(-2+1)=1, so the available
        //  from index=1 is bigger than loanAmount.
        int margin = Arrays.binarySearch(lenderOffers, temp, Comparator.comparingDouble(LenderOffer::getAvailable));
        if (margin < 0) {
            margin = -(margin + 1);
        }
        if (margin >= lenderOffers.length) {
            return null;
        }
        Arrays.sort(lenderOffers, margin, lenderOffers.length, Comparator.comparingDouble(LenderOffer::getRate));
        return lenderOffers[margin];
    }

}

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

package science.mengxin.java.tools.zopa.calculator.model;

public enum LenderOfferCsvColumn {
    LENDER("Lender"), RATE("Rate"), AVAILABLE("Available");

    private String name;

    LenderOfferCsvColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    /**
     * return the string with only first alphabet is capital
     * @return String Capitalize first alphabet
     */
    @Override
    public String toString() {
        String origin =  super.toString();
        origin = origin.toLowerCase();
        return  origin.substring(0, 1).toUpperCase() + origin.substring(1);
    }
}

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

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import io.airlift.airline.Option;

import java.util.List;

@Command(name = "quote", description = "Quote the best offer")
public class Quote extends RateCommand {
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
        System.out.println("Quote Run");
    }
}

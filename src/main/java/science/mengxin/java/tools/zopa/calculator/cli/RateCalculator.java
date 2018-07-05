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

import io.airlift.airline.Cli;
import io.airlift.airline.Help;

/**
 * The main class for science.mengxin.java.tools.zopa.calculator.cli.RateCalculator
 */
public class RateCalculator {

    public static void main(String[] args)
    {
        Cli.CliBuilder<Runnable> builder = Cli.<Runnable>builder("rater")
                .withDescription("quote the information of the offer")
                .withDefaultCommand(Help.class)
                .withCommands(Help.class, Quote.class);

        Cli<Runnable> gitParser = builder.build();

        gitParser.parse(args).run();
    }

}

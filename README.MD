# Rate Calculator CLI

[![Build Status](https://travis-ci.org/xmeng1/rate-calculator.svg?branch=master)](https://travis-ci.org/xmeng1/rate-calculator)

[![codecov](https://codecov.io/gh/xmeng1/rate-calculator/branch/master/graph/badge.svg)](https://codecov.io/gh/xmeng1/rate-calculator)

The tools is designed for searching the best offer for borrower.

## Task list

1. cli toolkit
2. loan calculator
3. csv parse
4. optimal searching algorithm
5. support various options

## How to use

### build the fat execute jar

```bash
mvn package
```

### run the jar and show help information

```bash
java -jar -Xmx128m target/rate-calculator-0.1.2.jar
```

### usage

```
quote -f market_file_full_path -l loan_amount
```

Both of the option are required.


```
-v after jar is Verbose mode to display more information.
```

### examples

```bash
java -jar -Xmx128m target/rate-calculator-0.q.2.jar -v quote -l 1000 -f D:\Develop\IDEAProject\rate-calculator\src\test\resources\market- file\market-offer-search-test.csv
```

Options [file:D:\Develop\IDEAProject\rate-calculator\src\test\resources\market-file\market-offer-search-test.csv, loan amount:1000.0]
The base offer for 1000.0 in data D:\Develop\IDEAProject\rate-calculator\src\test\resources\market-file\market-offer-search-test.csv is as follows:

Requested amount:       ?1000.0
Rate:                           11.8000%
Monthly repayment:      ?32.84
Total repayment:        ?1182.11
Best offer is:  LenderOffer{name='Alex', rate=0.118, available=1200.0}

```bash
java -jar -Xmx128m target/rate-calculator-1.0-SNAPSHOT.jar quote -l 1000 -f xx
```
Try to get the market data from the file: xx, but failed. xx (The system cannot find the file specified)
22:11:51.732 [main] ERROR s.m.j.t.z.c.c.Quote - Get file xx but get error xx (The system cannot find the file specified)

```bash
java -jar -Xmx128m target/rate-calculator-1.0-SNAPSHOT.jar quote -l 1000 -f file_not_exist
```
Try to get the market data from the filefile_not_exist, but failed. file_not_exist (The system cannot find the file specified)
22:12:02.171 [main] ERROR s.m.j.t.z.c.c.Quote - Get file file_not_exist but get error file_not_exist (The system cannot find the file specified)

```bash
java -jar -Xmx128m target/rate-calculator-1.0-SNAPSHOT.jar quote -l 1000
```
Missing optionRequired option '-f' is missing

```bash
java -jar -Xmx128m target/rate-calculator-1.0-SNAPSHOT.jar quote
```
Missing optionRequired option '-l' is missing 


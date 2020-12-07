#!/bin/bash

echo "creating : src/main/kotlin/Day$1.kt"
sed  "s|DayN|Day$1|g" "src/main/kotlin/DayN.kt" | sed "s|/day/n|/day/$1|g" > "src/main/kotlin/Day$1.kt"

echo "creating : src/test/kotlin/Day$1Test.kt"
sed  "s|ayN|ay$1|g" "src/test/kotlin/DayNTest.kt" | sed "s|(0)|(-1)|g" > "src/test/kotlin/Day$1Test.kt"

echo "creating : src/main/resources/day$1_simple.txt"
touch "src/main/resources/day$1_simple.txt"

echo "creating : src/main/resources/day$1_data.txt"
touch "src/main/resources/day$1_data.txt"

SESSION_ID='53616c7465645f5f82d5d392ee431e91543f61e56db88a265f340ec63b91a3139eb0649c94ca2ec0ec4b1ce8b0f5db24'

echo "loading from : https://adventofcode.com/2020/day/$1/input to src/main/resources/day$1_data.txt"

curl "https://adventofcode.com/2020/day/$1/input" \
  -H 'authority: adventofcode.com' \
  -H 'cache-control: max-age=0' \
  -H 'upgrade-insecure-requests: 1' \
  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36' \
  -H 'accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9' \
  -H 'sec-fetch-site: none' \
  -H 'sec-fetch-mode: navigate' \
  -H 'sec-fetch-user: ?1' \
  -H 'sec-fetch-dest: document' \
  -H 'referer: https://adventofcode.com/2020/day/5' \
  -H 'accept-language: en-GB,en-US;q=0.9,en;q=0.8' \
  -H "cookie: _ga=GA1.2.510370946.1605541548; session=$SESSION_ID; _gid=GA1.2.1838548585.1606854098" \
  --compressed > "src/main/resources/day$1_data.txt"

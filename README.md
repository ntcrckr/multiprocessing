Код в src/main/kotlin/Quicksort.kt и src/main/kotlin/ParallelQuicksort.kt
Основной тест в src/test/kotlin/ComparisonTest.kt

Результаты запуска теста (размер 10^8, 4 процесса, 5 запусков):

```
parallel sort: 4.511731708s
parallel sort: 4.028979333s
parallel sort: 4.337783083s
parallel sort: 4.491651750s
parallel sort: 4.744795917s
Sum time: 22.114941791s
Average time: 4.422988358s
Fastest time: 4.028979333s
```

```
sort: 9.247297084s
sort: 9.332082667s
sort: 9.093567166s
sort: 9.034138833s
sort: 9.217312167s
Sum time: 45.924397917s
Average time: 9.184879583s
Fastest time: 9.034138833s
```

Получается, что параллельный сорт в среднем быстрее в 2,076623052 раза, 
а самый быстрый из запусков быстрее в 2,2422896933 раза.
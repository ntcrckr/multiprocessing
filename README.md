Код в src/main/kotlin/Quicksort.kt и src/main/kotlin/ParallelQuicksort.kt
Основной тест в src/test/kotlin/ComparisonTest.kt

Результаты запуска теста (размер 10^8, 4 процесса, 5 запусков):

```
parallel sort: 4.520969625s
parallel sort: 5.098575625s
parallel sort: 4.432676917s
parallel sort: 4.902452959s
parallel sort: 4.555772833s
Sum time: 23.510447959s
Average time: 4.702089591s
Fastest time: 4.432676917s
```

```
sort: 9.353872958s
sort: 9.314223750s
sort: 9.416051625s
sort: 9.441027500s
sort: 9.341818333s
Sum time: 46.866994166s
Average time: 9.373398833s
Fastest time: 9.314223750s
```

Получается, что параллельный сорт в среднем быстрее в 1,9934538999
Основной тест в src/main/kotlin/Main.kt

Результаты запуска теста (размер 500^3, 4 процесса, 5 запусков):

```
26.568
21.829
21.67
19.892
19.937
6.908
7.021
6.963
6.846
6.884
Sequential BFS average time: 21.98 seconds
Parallel BFS average time: 6.92 seconds
Speedup: 3.17x
```

Получается, что параллельный сорт в среднем быстрее в 3.17 раз
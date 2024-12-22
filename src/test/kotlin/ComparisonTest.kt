import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import ru.ntcrckr.measured
import ru.ntcrckr.parallelQuicksort
import ru.ntcrckr.quicksort
import java.time.Duration
import kotlin.random.Random
import kotlin.time.toJavaDuration
import kotlin.time.toKotlinDuration

class ComparisonTest {
    @Test
    fun `large quicksort`() = measureMultipleTimes { array ->
        // given
        array.shuffle()
        // when
        val time = measured("sort") { array.quicksort() }.toJavaDuration()
        // then
        assert(array.isSorted())
        time
    }

    @Test
    fun `large parallel quicksort`() = measureMultipleTimes { array ->
        var time = Duration.ZERO
        // given
        array.shuffle()
        // when
        time = measured("parallel sort") { array.parallelQuicksort() }.toJavaDuration()
        // then
        assert(array.isSorted())
        time
    }

    val times = 5
    val size = 100_000_000

    private fun measureMultipleTimes(block: suspend (array: IntArray) -> Duration) {
        val durations = (1..times).map {
            val array = IntArray(size) { Random.nextInt() }
            runBlocking { block.invoke(array) }
        }
        val sumDuration = durations.fold(Duration.ZERO) { acc, d -> acc + d }
        println("Sum time: ${sumDuration.toKotlinDuration()}")
        println("Average time: ${sumDuration.dividedBy(times.toLong()).toKotlinDuration()}")
        println("Fastest time: ${durations.min().toKotlinDuration()}")
    }

    private fun IntArray.isSorted(): Boolean {
        for (i in 0 until size - 1) {
            if (this[i] > this[i + 1]) {
                return false
            }
        }
        return true
    }
}
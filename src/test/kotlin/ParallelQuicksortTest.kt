import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertArrayEquals
import ru.ntcrckr.parallelQuicksort
import kotlin.test.Test

class ParallelQuicksortTest {
    @Test
    fun `check sorting of parallelQuickSort()`(): Unit = runBlocking {
        // given
        val array = intArrayOf(15, 3, 1521, 521, 51, 215, 15, 63, 1)
        // when
        val actual = array.parallelQuicksort(4)
        // then
        val expected = intArrayOf(1, 3, 15, 15, 51, 63, 215, 521, 1521)
        assertArrayEquals(expected, actual)
    }
}
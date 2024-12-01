import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeEach
import ru.ntcrckr.parallelQuicksort
import kotlin.test.Test

class ParallelQuicksortTest {
    @OptIn(DelicateCoroutinesApi::class)
    private val sortPool = newFixedThreadPoolContext(4, "sorting")

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(sortPool)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        sortPool.close()
    }

    @Test
    fun `check sorting of parallelQuickSort()`(): Unit = runTest {
        launch(Dispatchers.Main) {
            // given
            val array = intArrayOf(15, 3, 1521, 521, 51, 215, 15, 63, 1)
            // when
            array.parallelQuicksort()
            // then
            val expected = intArrayOf(1, 3, 15, 15, 51, 63, 215, 521, 1521)
            assertArrayEquals(expected, array)
        }
    }
}
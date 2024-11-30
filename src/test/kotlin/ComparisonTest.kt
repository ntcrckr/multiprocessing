import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.ntcrckr.measured
import ru.ntcrckr.parallelQuicksort
import ru.ntcrckr.quicksort
import kotlin.random.Random

class ComparisonTest {
    private val array = IntArray(1_000_000) { Random.nextInt() }
//    private val expected = array.sorted().toIntArray()

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

    val times = 1

    @Test
    fun `large quicksort`(): Unit = runBlocking {
        repeat(times) {
            // given / when
            val actual = measured("sort") { array.quicksort() }
            // then
//            assertArrayEquals(expected, actual)
        }
    }

    @Test
    fun `large parallel quicksort`() = repeat(times) {
        runTest {
            launch(Dispatchers.Main) {
                // given / when
                val actual = array.parallelQuicksort()
                // then
//                assertArrayEquals(expected, actual)
            }
        }
    }
}
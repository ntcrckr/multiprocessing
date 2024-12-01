import org.junit.jupiter.api.Assertions.assertArrayEquals
import ru.ntcrckr.quicksort
import kotlin.test.Test

class QuicksortTest {
    @Test
    fun `check sorting of quickSort`() {
        // given
        val array = intArrayOf(15, 3, 1521, 521, 51, 215, 15, 63, 1)
        // when
        array.quicksort()
        // then
        val expected = intArrayOf(1, 3, 15, 15, 51, 63, 215, 521, 1521)
        assertArrayEquals(expected, array)
    }
}
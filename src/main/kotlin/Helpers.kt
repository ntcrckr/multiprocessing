import java.lang.invoke.MethodHandles.arrayElementVarHandle
import java.lang.invoke.VarHandle

private val handle: VarHandle = arrayElementVarHandle(IntArray::class.java)

fun IntArray.cas(index: Int, newVal: Int): Boolean =
    handle.compareAndSet(this, index, -1, newVal)
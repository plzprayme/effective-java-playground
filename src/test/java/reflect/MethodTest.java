package reflect;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import chap9.reflection.ParentBook;

public class MethodTest {
    static Class<ParentBook> bookClass;
    static ParentBook instance;

    @BeforeAll
    public static void given() {
        instance = new ParentBook();
        bookClass = ParentBook.class;
    }

    @Test
    public void 메서드를_읽을_수_있다() {
        // given
        List<String> expected = List.of(
            "public java.lang.String chap9.reflection.ParentBook.toString()",
            "public java.lang.String chap9.reflection.ParentBook.h()",
            "public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException",
            "public final void java.lang.Object.wait() throws java.lang.InterruptedException",
            "public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException",
            "public boolean java.lang.Object.equals(java.lang.Object)",
            "public native int java.lang.Object.hashCode()",
            "public final native java.lang.Class java.lang.Object.getClass()",
            "public final native void java.lang.Object.notify()",
            "public final native void java.lang.Object.notifyAll()"
        );

        // when
        List<String> actual = getStringList(bookClass.getMethods());

        // then
        assertEquals(actual, expected);
    }

    private List<String> getStringList(Method[] methods) {
        return Arrays.stream(methods)
            .map(Method::toString)
            .collect(Collectors.toList());
    }

    @Test
    public void 메서드를_실행할_수_있다() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        Method method = bookClass.getMethod("h");
        String expected = instance.h();

        // when
        String actual = method.invoke(instance).toString();

        // then
        assertEquals(actual, expected);
    }
}

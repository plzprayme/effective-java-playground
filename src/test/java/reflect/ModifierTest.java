package reflect;


import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import chap9.reflection.ParentBook;

public class ModifierTest {
    static Class<ParentBook> bookClass;
    static ParentBook instance;

    @BeforeAll
    public static void given() {
        instance = new ParentBook();
        bookClass = ParentBook.class;
    }

    @Test
    public void 접근제어자_정보를_알_수_있다() throws NoSuchMethodException {
        // given
        Method method = bookClass.getDeclaredMethod("p");

        // when
        boolean actual = Modifier.isPrivate(method.getModifiers());

        // then
        assertTrue(actual);
    }
}

package reflect;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import chap9.reflection.ParentBook;

public class ConstructorTest {
    static Class<ParentBook> bookClass;
    static ParentBook instance;

    @BeforeAll
    public static void given() {
        instance = new ParentBook();
        bookClass = ParentBook.class;
    }

    @Test
    public void 생성자를_호출할_수_있다() throws
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException,
        NoSuchMethodException
    {
        // given
        Constructor<ParentBook> constructor = bookClass.getConstructor(null);
        ParentBook expected;


        // when
        expected = constructor.newInstance();

        // then
        assertNotNull(expected);
    }
}

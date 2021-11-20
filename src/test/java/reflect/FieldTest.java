package reflect;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import chap9.reflection.ParentBook;

public class FieldTest {

    static Class<ParentBook> bookClass;

    @BeforeAll
    public static void given() {
        bookClass = ParentBook.class;
    }

    @Test
    public void Public_필드_값을_읽을_수_있다_() {
        // given
        List<String> expected = List.of(
            "public java.lang.String chap9.reflection.ParentBook.d",
            "public static java.lang.String chap9.reflection.ParentBook.e"
        );

        // when
        List<String> actual = getStringList(bookClass.getFields());

        // then
        assertEquals(actual, expected);
    }

    @Test
    public void Private_필드_값을_읽을_수_있다_() {
        // given
        List<String> expected = List.of(
            "private java.lang.String chap9.reflection.ParentBook.a",
            "private static java.lang.String chap9.reflection.ParentBook.b",
            "private static final java.lang.String chap9.reflection.ParentBook.c",
            "public java.lang.String chap9.reflection.ParentBook.d",
            "public static java.lang.String chap9.reflection.ParentBook.e",
            "private static final java.lang.String chap9.reflection.ParentBook.f",
            "protected java.lang.String chap9.reflection.ParentBook.g"
        );

        // when
        List<String> actual = getStringList(bookClass.getDeclaredFields());

        // then
        assertEquals(actual, expected);
    }

    private List<String> getStringList(Field[] declaredFields) {
        return Arrays.stream(declaredFields)
            .map(Field::toString)
            .collect(Collectors.toList());
    }
}

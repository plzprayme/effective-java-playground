package reflect.tutorial;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import chap9.reflection.ParentBook;
import chap9.reflection.di.annotation.Inject;
import chap9.reflection.di.service.BookService;

class BookServiceTest {

    static Class<BookService> bookClass;
    static ParentBook instance;

    @BeforeAll
    public static void given() {
        instance = new ParentBook();
        bookClass = BookService.class;
    }


    @Test
    public void 필드에_애너테이션이_붙어있는지_확인할_수_있다() throws NoSuchFieldException {
        // given
        Field given = bookClass.getDeclaredField("book");

        // when
        Inject actual = given.getAnnotation(Inject.class);

        // then
        assertNotNull(actual);
    }
}

package chap9.reflection.di.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class InjectServiceTest {

    static InjectService injectService;

    @BeforeAll
    public static void given() {
        injectService = new InjectService();
    }

    @Test
    public void 의존성_주입을_할_수_있다() throws ReflectiveOperationException {
        // given
        BookService unInjected = new BookService();

        // when
        BookService injected = injectService.init(unInjected);
        String actual = injected.callH();

        // then
        assertEquals("METHOD", actual);
    }


}
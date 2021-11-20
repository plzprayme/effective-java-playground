package chap9.reflection.di.service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import chap9.reflection.ParentBook;
import chap9.reflection.di.annotation.Inject;

public class InjectService {

    public BookService init(BookService service) throws ReflectiveOperationException {
        Class<? extends BookService> reflectionAPI = service.getClass();

        Field needInject = Arrays.stream(reflectionAPI.getDeclaredFields())
            .filter(f -> Objects.nonNull(f.getAnnotation(Inject.class)))
            .collect(Collectors.toList())
            .get(0);

        needInject.setAccessible(true);
        needInject.set(service, new ParentBook());

        return service;
    }

}

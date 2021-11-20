package chap9.reflection.di.service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import chap9.reflection.ParentBook;
import chap9.reflection.di.annotation.Inject;

public class InjectService {

    public BookService init(BookService service) throws ReflectiveOperationException {
        Class<? extends BookService> api = service.getClass();
        BookService instance = newInstance(api);
        Field needInject = filterAnnotatedField(api);
        needInject.setAccessible(true);
        needInject.set(instance, new ParentBook());;
        return instance;
    }

    private Field filterAnnotatedField(Class<? extends BookService> api) {
        return Arrays.stream(api.getDeclaredFields())
            .filter(f -> Objects.nonNull(f.getAnnotation(Inject.class)))
            .collect(Collectors.toList())
            .get(0);
    }

    private BookService newInstance(Class<? extends BookService> api) throws ReflectiveOperationException {
        return api.getConstructor().newInstance();
    }
}

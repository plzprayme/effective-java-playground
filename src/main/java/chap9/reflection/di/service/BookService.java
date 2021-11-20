package chap9.reflection.di.service;

import chap9.reflection.ParentBook;
import chap9.reflection.di.annotation.Inject;

public class BookService {

    @Inject
    private ParentBook book;

    public String callH() {
        return book.h();
    }
}

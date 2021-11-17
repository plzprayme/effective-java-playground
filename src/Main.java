import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import chap9.forloop.AUSG;
import chap9.forloop.Member;
import chap9.reflection.ParentBook;
import chap9.reflection.di.service.BookService;
import chap9.reflection.di.service.InjectService;

public class Main {
    public static void main(String[] args) throws Exception {
        // forloop();
        // reflection();

        BookService service = new InjectService().init(new BookService());
        System.out.println(service.callH());
    }

    private static void reflection() throws Exception {
        Class<ParentBook> bookClass = get();
        // printFields(bookClass);
        // printFieldsValue(bookClass);
        // printConstructor(bookClass);
        // printMethod, SuperClass, Inteface, Annotation...



        // workWithConstructor(bookClass);
        // workWithModifier(bookClass);
        // workWithMethod(bookClass);
    }


    private static void workWithMethod(Class<ParentBook> bookClass) throws Exception {
        Method[] ms = bookClass.getDeclaredMethods(); // 메서드 다 가져오기

        Method m = bookClass.getMethod("h");
        System.out.println(m.invoke(new ParentBook()));

        // private method
        Method m2 = bookClass.getDeclaredMethod("p");
        m2.setAccessible(true);
        System.out.println(m2.invoke(new ParentBook()));
    }

    private static void workWithConstructor(Class<ParentBook> bookClass) throws Exception {
        Constructor<ParentBook> c1 = bookClass.getConstructor(null); // 기본 생성자
        Constructor<ParentBook> c2 = bookClass.getConstructor(String.class, String.class, String.class); // 파라미터에 맞는 생성자

        ParentBook book = new ParentBook();
        ParentBook i1 = c1.newInstance();
        ParentBook i2 = c2.newInstance("a", "b", "c"); // 리플렉션으로 Constructor로 생성하기
        System.out.println(i1);
        System.out.println(i2);
    }

    private static void workWithModifier(Class<ParentBook> bookClass) {
        Arrays.stream(bookClass.getDeclaredFields())
            .forEach(f -> System.out.println(Modifier.isPrivate(f.getModifiers())));
    }

    private static void printConstructor(Class<ParentBook> bookClass) throws Exception {
        Constructor<?>[] cs = bookClass.getConstructors();
        Constructor<ParentBook> c1 = bookClass.getConstructor(null); // 기본 생성자
        Constructor<ParentBook> c2 = bookClass.getConstructor(String.class, String.class, String.class); // 파라미터에 맞는 생성자
        Arrays.stream(cs).forEach(System.out::println);

        ParentBook book = new ParentBook();
        ParentBook i1 = c1.newInstance();
        ParentBook i2 = c2.newInstance("a", "b", "c"); // Constructor로 생성하기
    }

    private static void printFieldsValue(Class<ParentBook> bookClass) {
        ParentBook book = new ParentBook();
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            f.setAccessible(true); // 접근 지시자 무시하기

            try {
                System.out.printf("%s %s", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    private static void printFields(Class<ParentBook> bookClass) {
        Arrays.stream(bookClass.getFields()).forEach(System.out::println); // public 필드만
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println); // 모든 필드

    }


    private static Class<ParentBook> get() {
        // 초기화 1 .class로 가져오기
        Class<ParentBook> bookClass1 = ParentBook.class;

        // // 초기화 2 인스턴스화 된 녀석으로 부터 가져오기
        // ParentBook book = new ParentBook();
        // Class<? extends ParentBook> bookClass2 = book.getClass();
        //
        // // 초기화 3 fully qualified class name 으로 가져오기
        // Class<?> bookClass3 = Class.forName("chap9.reflection.ParentBook");

        return bookClass1;
    }

    private static void forloop() {
        List<Member> members = List.of(
            new Member("성찬"),
            new Member("진수"),
            new Member("상우"),
            new Member("정우"),
            new Member("정민"),
            new Member("진영")
        );
        AUSG ausg = new AUSG(members);

        for (Member member : ausg) {
            System.out.println(member);
        }
    }
}

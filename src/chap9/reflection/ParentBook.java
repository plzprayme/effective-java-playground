package chap9.reflection;

public class ParentBook {

    private String a = "PRIVATE";
    private static String b = "PRIVATE STATIC";
    private static final String c = "PRIVATE STATIC FINAL";

    public String d = "PUBLIC";
    public static String e = "PUBLIC STATIC";
    private static final String f = "PUBLICSTATIC FINAL";

    protected String g = "PROTECTED";

    public String h() {
        return "METHOD";
    }

    private String p() {
        return "PRIVATE METHOD";
    }

    public ParentBook() {}

    public ParentBook(String a, String d, String g) {
        this.a = a;
        this.d = d;
        this.g = g;
    }

    @Override
    public String toString() {
        return "ParentBook{" +
            "a='" + a + '\'' +
            ", d='" + d + '\'' +
            ", g='" + g + '\'' +
            '}';
    }
}

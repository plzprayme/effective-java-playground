package chap9.forloop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AUSG implements Iterable<Member> {
    List<Member> members;

    public AUSG(List<Member> members) {
        this.members = members;
    }

    public void join(Member member) {
        members.add(member);
    }

    @Override
    public Iterator<Member> iterator() {
        return members.iterator();
    }
}

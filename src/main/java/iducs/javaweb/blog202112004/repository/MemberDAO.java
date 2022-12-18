package iducs.javaweb.blog202112004.repository;

import iducs.javaweb.blog202112004.model.Member;

import java.util.List;

public interface MemberDAO {
    int create(Member m);
    Member read(Member m);
    List<Member> readList();
    int update(Member m);
    int delete(Member m);

    Member login(Member m);
}

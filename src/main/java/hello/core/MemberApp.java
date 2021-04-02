package hello.core;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "MemberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member    Id = " + member.getId());
        System.out.println("new member  Name = " + member.getName());
        System.out.println("new member Grade = " + member.getGrade());
        System.out.println("findMember    Id = " + findMember.getId());
        System.out.println("findMember  Name = " + findMember.getName());
        System.out.println("findMember Grade = " + findMember.getGrade());
    }

}

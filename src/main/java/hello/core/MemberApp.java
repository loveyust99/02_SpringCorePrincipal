package hello.core;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "MemberA", Grade.VIP);
        System.out.println("member = " + member);
        
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = " + findMember);
    }

}

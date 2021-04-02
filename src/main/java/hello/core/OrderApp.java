package hello.core;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.order.domain.Order;
import hello.core.order.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId1 = 1L;
        Member memberVip = new Member(memberId1, "memberVip", Grade.VIP);
        memberService.join(memberVip);
        Order OrderVip = orderService.createOrder(memberId1, "itemA", 10000);
        System.out.println("OrderVip = " + OrderVip);

        Long memberId2 = 2L;
        Member memberBasic = new Member(memberId2, "memberBasic", Grade.BASIC);
        memberService.join(memberBasic);
        Order orderBasic = orderService.createOrder(memberId2, "itemA", 10000);
        System.out.println("orderBasic = " + orderBasic);

    }

}

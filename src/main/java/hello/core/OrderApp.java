package hello.core;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import hello.core.order.domain.Order;
import hello.core.order.service.OrderService;
import hello.core.order.service.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

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

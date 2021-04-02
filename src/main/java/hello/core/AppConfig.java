package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import hello.core.order.service.OrderService;
import hello.core.order.service.OrderServiceImpl;

public class AppConfig {

    //멤버서비스 역할(멤버 가입/조회 등)을 수행하는 것에만 집중.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    //주문서비스 역할(상품 주문 등)을 수행하는 것에만 집중. 별도 서비스인 상품 조회는 현재 구현하지 않음.
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //저장소(메모리DB, Oracle/MS-SQL/MySQL/Redis/MongoDB 등 상관없이)의 역할(CRUD)에만 집중.
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
        //return new JDBCMemberRepository();
    }

    //할인정책 역할(정액 또는 정률)에만 집중.
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
        //return new RateDiscountPolicy();
    }

}

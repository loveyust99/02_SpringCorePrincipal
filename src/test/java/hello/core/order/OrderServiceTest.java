package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.order.domain.Order;
import hello.core.order.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    AppConfig appConfig;

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        //given
        Long memberId1 = 1L;
        Long memberId2 = 2L;
        Member memberVip = new Member(memberId1, "memberVip", Grade.VIP);
        Member memberBasic = new Member(memberId2, "memberBasic", Grade.BASIC);

        //when
        memberService.join(memberVip);
        Order orderVip = orderService.createOrder(memberId1, "itemA", 10000);
        System.out.println("orderVip = " + orderVip);

        memberService.join(memberBasic);
        Order orderBasic = orderService.createOrder(memberId2, "itemA", 10000);
        System.out.println("orderBasic = " + orderBasic);

        //then
        Assertions.assertThat(orderVip.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(orderBasic.getDiscountPrice()).isEqualTo(0);
    }

}

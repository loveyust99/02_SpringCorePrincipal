package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("Spring 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        // 즉, Spring이 없는 순수한 DI컨테이너인 AppConfig는 요청할 때마다 객체를 새로 생성한다.(메모리 낭비 심함)
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("Spring 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : 호출할 때마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //2. 조회 : 호출할 때마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        //참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 == memberService2
        //즉, Spring 컨테이너(DI컨테이너)에 의해 객체 인스턴스가 싱글톤으로 관리된다.
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }

}

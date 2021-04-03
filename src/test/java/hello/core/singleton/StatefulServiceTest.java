package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("stateful 싱글톤 서비스 테스트")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        System.out.println("statefulService1 = " + statefulService1);
        System.out.println("statefulService2 = " + statefulService2);

        //ThreadA : A 사용자가 10,000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB : B 사용자가 20,000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA : A 사용자가 주문 금액 조회 - 10,000원 예상했는데 20,000원 나옴
        System.out.println("statefulService1 주문금액 = " + statefulService1.getPrice());
        //ThreadB : B 사용자가 주문 금액 조회 - 20,000원 예상했는데 예상대로 20,000원 나옴
        System.out.println("statefulService2 주문금액 = " + statefulService2.getPrice());

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }

}
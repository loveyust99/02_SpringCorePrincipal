package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_APPLICATION;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("ApplicationContext컨테이너(즉, DI컨테이너)에 등록된 모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //System.out.println("beanDefinitionName = " + beanDefinitionName);
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + ", bean = " + bean);
        }
    }

    @Test
    @DisplayName("ApplicationContext컨테이너(즉, DI컨테이너)에 등록된 사용자 정의 어플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            /*
                ROLE_APPLICATION(0)     : 사용자가 정의하여 등록한 어플리케이션 빈
                ROLE_SUPPORT(1)         : org.springframework.beans.factory.parsing.ComponentDefinition을 지원하는 빈.
                ROLE_INFRASTRUCTURE(2)  : 스프링이 내부에서 사용하는 빈(완전히 백그라운드 역할을 제공하고 최종 사용자와 관련이 없음)
             */
            if(beanDefinition.getRole() == ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName = " + beanDefinitionName + ", bean = " + bean);
            }
        }
    }

}

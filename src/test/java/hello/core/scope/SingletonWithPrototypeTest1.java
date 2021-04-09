package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

        ac.close();
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        System.out.println("clientBean1.logic() = " + count1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        System.out.println("clientBean2.logic() = " + count2);

        Assertions.assertThat(count1).isEqualTo(1);
//        Assertions.assertThat(count2).isEqualTo(2);
        Assertions.assertThat(count2).isEqualTo(1);

        ac.close();
    }

    @Scope("singleton")
    static class ClientBean {
//        private final PrototypeBean prototypeBean;  //생성시점에 주입
//
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic() {
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }

//        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;
        private Provider<PrototypeBean> provider;

//        @Autowired
//        public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanObjectProvider) {
//            this.prototypeBeanObjectProvider = prototypeBeanObjectProvider;
//        }
        @Autowired
        public ClientBean(Provider<PrototypeBean> prototypeBeanObjectProvider) {
            this.provider = prototypeBeanObjectProvider;
        }

        public int logic() {
//            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }

}

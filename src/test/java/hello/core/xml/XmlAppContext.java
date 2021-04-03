package hello.core.xml;

import hello.core.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext xac = new GenericXmlApplicationContext("AppConfig.xml"); //xml설정 파일은 src/main/resources폴더에 저장
        MemberService memberService = xac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    }

}

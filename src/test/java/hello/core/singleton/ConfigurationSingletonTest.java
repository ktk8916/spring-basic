package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("설정파일에서 두번 호출되는 빈이 같은지 확인")
    public void configurationTest(){

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
        Assertions.assertThat(memberRepository).isSameAs(memberRepository2);
    }
    
    @Test
    @DisplayName("configuration 정보 확인")
    void configurationDeep(){
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
        //bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$ee00ca4c
        //AppConfig도 스프링 빈에 등록되는데, AppConfig를 상속하여 싱글톤을 보장하는 새로운 객체를 만들어서 등록(바이트코드 조작)
    }
}

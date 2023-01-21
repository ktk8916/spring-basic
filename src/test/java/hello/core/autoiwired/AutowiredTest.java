package hello.core.autoiwired;

import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    @DisplayName("의존관계 주입 옵션테스트")
    public void autowiredOption(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutowiredConfig.class);
    }

    static class AutowiredConfig{

        //Member는 스프링 컨테이너에 등록되는 Bean이 아니다
        
        //의존성을 주입해줄 Bean이 없으면 메서드가 실행되지 않음
        @Autowired(required = false)
        public void requiredFalse(Member member){
            System.out.println("member = " + member);
        }

        //Nullable하기 때문에 null로 의존성이 주입됨
        @Autowired
        public void nullAble(@Nullable Member member){
            System.out.println("member = " + member);
        }
        
        //Optional로 감싸진 객체로 의존성이 주입됨
        @Autowired
        public void optional(Optional<Member> member){
            System.out.println("member = " + member);
        }

    }
}

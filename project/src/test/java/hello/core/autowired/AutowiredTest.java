package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.core.member.Member;

// 자동 주입 옵션 처리 테스트
public class AutowiredTest {

	@Test
	void AutowiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
		
	}
	
	static class TestBean {
		
		/* 
		 Member 클래스는 스프링 빈으로 등록되어 있지 않기 때문에 (자동 주입 대상이 없음)
		 의존 관계 주입이 되지 않아 오류가 발생한다. (NoSuchBeanDefinitionException)
		 */
//		@Autowired(required = true) // Default
//		public void setNoBean1(Member noBean1) {
//			System.out.println("noBean1 : " + noBean1);
//		}
		
		// @Autowired(required = false) : 자동 주입할 대상이 없으면 수정자 메소드 자체가 호출이 안된다.
		@Autowired(required = false)
		public void setNoBean1(Member noBean1) {
			System.out.println("noBean1 : " + noBean1);
		}
		
		// @Nullable :  자동 주입할 대상이 없으면 null을 입력해서 호출한다.
		@Autowired
		public void setNoBean2(@Nullable Member noBean2) {
			System.out.println("noBean2 : " + noBean2); // noBean2 : null
		}
		
		// Optional<> : 자동 주입할 대상이 없으면 Optional.empty을 입력해서 호출한다.
		@Autowired
		public void setNoBean3(Optional<Member> noBean3) {
			System.out.println("noBean3 : " + noBean3); // noBean3 : Optional.empty
		}
		
		/* 
		 @Nullable, Optional<>은 스프링 전반에 걸쳐서 지원된다.
		 예를 들어서 생성자 자동 주입에서 특정 필드에만 사용해도 된다.
		 */
	}
}

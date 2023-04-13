package hello.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;

public class AutoAppConfigTest {

	@Test
	void basicScan() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		
		MemberRepository memberRepository = ac.getBean("memoryMemberRepository", MemoryMemberRepository.class); 
		assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
		
		/*
		 * **자동 빈 등록과 수동 빈 등록에서 빈 이름이 중복되도 충돌하지 않고 테스트가 성공하는 이유
		 *  - 수동 빈 등록이 우선권을 가짐!
		 *  => 수동으로 등록된 빈이 자동으로 등록된 빈을 오버라이딩 해버린다.
		 *  
		 *  **로그
		 *  ```
		 *  Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing ~
		 *  ```
		 *  
		 * **스프링 부트에서는 자동 빈 등록과 수동 빈 등록 충돌 시 기본값으로 오버라이딩을 막아서 오류가 발생함. (spring.main.allow-bean-definition-overriding=false)
		 */
	}
}

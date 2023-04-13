package hello.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;

public class AutoAppConfigTest {

	@Test
	void basicScan() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		
		MemberService memberService = ac.getBean("service", MemberService.class); // �� ��� �� �ߺ��Ǵ� �̸����� ���� �浹 - ConflictingBeanDefinitionException 
		assertThat(memberService).isInstanceOf(MemberService.class);
	}
}

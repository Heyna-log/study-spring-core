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
		 * **�ڵ� �� ��ϰ� ���� �� ��Ͽ��� �� �̸��� �ߺ��ǵ� �浹���� �ʰ� �׽�Ʈ�� �����ϴ� ����
		 *  - ���� �� ����� �켱���� ����!
		 *  => �������� ��ϵ� ���� �ڵ����� ��ϵ� ���� �������̵� �ع�����.
		 *  
		 *  **�α�
		 *  ```
		 *  Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing ~
		 *  ```
		 *  
		 * **������ ��Ʈ������ �ڵ� �� ��ϰ� ���� �� ��� �浹 �� �⺻������ �������̵��� ���Ƽ� ������ �߻���. (spring.main.allow-bean-definition-overriding=false)
		 */
	}
}

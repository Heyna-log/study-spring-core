package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	void configurationTest() {
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean(MemberRepository.class);
		
		MemberRepository MSMemberRepository = memberService.getMemberRepository();
		MemberRepository OSMemberRepository = orderService.getMemberRepository();
		
		System.out.println("memberService -> memberRepository : " + MSMemberRepository);
		System.out.println("orderService -> memberRepository : " + OSMemberRepository);
		System.out.println("memberRepository : " + memberRepository);
		
		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
		
		/* 
		 * AppConfig�� �ڹ� �ڵ��ζ�� 
		 * 'new MemberRepository'�� �� �� �� ȣ��ǰ�,
		 * memberRepository ��ü�� 3�� ������ �Ǿ� �̱��� ������ ���� �ʴ´�.
		 * 
		 * ������, �׽�Ʈ ��� ����� ���� 
		 * 'new MemberRepository'�� �� ���� ȣ��ǰ�, 
		 * memberRepository ��ü�� 1���� �����Ǿ� �̱��� ������ �Ǵ� ���� �� �� �ִ�.
		 * 
		 * */
	}
	
	@Test
	void configurationDeep() {
		AppConfig bean = ac.getBean(AppConfig.class);
		
		// AppConfig ��Ÿ�� Ŭ���� ��ü Ȯ��
		System.out.println("been : " + bean.getClass());
		
		/* ���� ��� ��� : class hello.core.AppConfig
		 * ���� ��� ��� : class hello.core.AppConfig$$EnhancerBySpringCGLIB$$291fa58a
		 * 
		 * AppConfig�� @Configuration�� �����ϸ�
		 * ����Ʈ�ڵ� ���� ���̺귯�� 'CGLIB'�� ���� AppConfig Ŭ������ ��ӹ��� ������ �ٸ� Ŭ����(AppConfig@CGLIB)�� �����, 
		 * �� ������ Ŭ������ ������ ������ �����.
		 * ������ Ŭ������ ���� �̱����� �����.
		 * 
		 * *AppConfig@CGLIB�� AppConfig�� �ڽ� Ÿ���̹Ƿ�, AppConfig Ÿ������ ��ȸ�� �� �ִ�.
		 * 
		 * --------------------------------------
		 * 
		 * **AppConfig@CGLIB ���� �ڵ�
		 ```
		 @Bean
		 public MemberRepository memberRepository() {
			 if (memoryMemberRepository�� �̹� ������ �����̳ʿ� ��ϵǾ� ������?) {
			  	 return ������ �����̳ʿ��� ã�Ƽ� ��ȯ;
			 } else { // <- ������ �����̳ʿ� ������
			  	 ���� ����(���� AppConfig Ŭ������ �ִ� memberRepository �޼ҵ�)�� ȣ���ؼ� MemoryMemberRepository�� �����ϰ� ������ �����̳ʿ� ���
				 return ��ȯ
			 }
		 }
		 ```
		 * @Bean�� ���� �޼��帶�� �̹� ������ ���� �����ϸ� �����ϴ� ���� ��ȯ�ϰ�,
		 * ������ ���� ������ �����ؼ� ������������ ����ϰ� ��ȯ�ϴ� �ڵ尡 �������� ���������.
		 * => �̱��� ����
		*/
	}
}

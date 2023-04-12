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

	@Test
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
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
}

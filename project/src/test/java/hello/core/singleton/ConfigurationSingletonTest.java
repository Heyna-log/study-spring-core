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
	}
	
	@Test
	void configurationDeep() {
		AppConfig bean = ac.getBean(AppConfig.class);
		
		// AppConfig 런타임 클래스 객체 확인
		System.out.println("been : " + bean.getClass());
	}
}

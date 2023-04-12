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
		 * AppConfig의 자바 코드대로라면 
		 * 'new MemberRepository'가 총 세 번 호출되고,
		 * memberRepository 객체가 3개 생성이 되어 싱글톤 보장이 되지 않는다.
		 * 
		 * 하지만, 테스트 출력 결과를 보면 
		 * 'new MemberRepository'는 한 번만 호출되고, 
		 * memberRepository 객체가 1개만 생성되어 싱글톤 보장이 되는 것을 알 수 있다.
		 * 
		 * */
	}
}

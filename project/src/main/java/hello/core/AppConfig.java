package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// **어노테이션 기반 자바 설정 클래스
//@Configuration // '애플리케이션 설정(구성) 정보 담당'을 의미
public class AppConfig {
	
	/*
	 * **스프링 빈 등록
	 *  - @Bean이 붙어있는 메소드를 호출 후 반환되는 객체를 스프링 빈으로 등록
	 *  - 메소드 이름을 빈 이름으로 사용
	 *  - 빈 이름을 직접 부여할 수도 있음. ex) @Bean(name="memberService2")
	 *  	- 빈 이름은 항상 다른 이름을 부여해야 함.
	 *  	- 같은 이름을 부여하면 다른 빈이 무시되거나, 기존 빈을 덮어버리는 등 설정에 따라 오류가 발생할 수 있음.
	 */
	
	@Bean // 메소드 호출 후 반환되는 객체를 스프링 빈으로 등록
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository(); // 빈으로 등록
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		return new RateDiscountPolicy();
	}

	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}
}

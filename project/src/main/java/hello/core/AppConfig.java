package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


/*
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
 * 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)
 *  => 생성자 주입 (Constructor Injection)
 */

public class AppConfig {
	
	// ** 회원 저장소
	// memberRepository 구현 객체는 MemoryMemberRepository 사용
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
	
	// ** 할인 정책
	// discountPolicy 구현 객체는 FixDiscountPolicy 사용 (정액 할인 정책 사용)
	public DiscountPolicy discountPolicy() {
		return new FixDiscountPolicy();
	}

	// ** 회원 관련 Service
	// memberService 구현 객체인 MemberServiceImpl에 memberRepository 구현 객체 주입
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}
	
	// ** 주문 관련 Service
	// orderService 구현 객체인 OrderServiceImpl에 memberRepository, discountPolicy 구현 객체 주입
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}
}

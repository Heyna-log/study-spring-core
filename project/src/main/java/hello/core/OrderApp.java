package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class OrderApp {

	public static void main(String[] args) {

//		// 외부(AppConfig)를 통해 구현 객체를 가져옴
//		AppConfig appconfig = new AppConfig();
//		MemberService memberService = appconfig.memberService();
//		OrderService orderService = appconfig.orderService();
		
		// 스프링 컨테이너 생성
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// 스프링 빈(객체) 사용
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		OrderService orderService = ac.getBean("orderService", OrderService.class);
		
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP); // 회원 생성
		
		memberService.join(member); // 회원 가입
		
		Order order = orderService.createOrder(memberId, "itemA", 20000); // 주문 생성
		
		System.out.println("order = " + order.toString()); // 주문 확인
		System.out.println("order.calculatePrice = " + order.calculatePrice()); // 할인된 가격 확인
	}

}

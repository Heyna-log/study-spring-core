package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class OrderServiceTest {
	
	MemberService memberService;
	OrderService orderService;
	
	// 각 테스트 메소드 실행 전 무조건 실행
	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}
	
	@Test
	void createorder() {
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		
		memberService.join(member); // 회원 가입
		
		Order order = orderService.createOrder(memberId, "itemA", 10000); // 주문 생성
		
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // 검증
	}
	
}

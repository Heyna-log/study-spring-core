package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class OrderServiceTest {

	MemberService memberService = new MemberServiceImpl();
	OrderService orderService = new OrderServiceImpl();
	
	@Test
	void createorder() {
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		
		memberService.join(member); // 회원 가입
		
		Order order = orderService.createOrder(memberId, "itemA", 10000); // 주문 생성
		
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // 검증
	}
	
}

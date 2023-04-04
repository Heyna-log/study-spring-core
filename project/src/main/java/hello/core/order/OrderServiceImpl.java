package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
	
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	// 할인 정책
//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 정액 할인 정책
	private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정를 할인 정책
	
	/*
	 * ** DIP 위반
	 *  - 추상(인터페이스) 의존 => DiscountPolicy
	 *  - 구체(구현) 클래스 의존 => FixDiscountPolicy, RateDiscountPolicy
	 *  
	 * ** OCP 위반
	 *  - 기능 확장, 변경 시 클라이언트 코드(Service)에 영향을 줌 => 할인 정책 변경 시 ServiceImpl 코드를 변경해야 한다.
	 *  
	 */
	
	

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		// 회원 정보 조회
		Member member = memberRepository.findById(memberId);
		
		// 회원 등급에 따른 할인 가격 찾기
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		// 주문 반환
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	
}

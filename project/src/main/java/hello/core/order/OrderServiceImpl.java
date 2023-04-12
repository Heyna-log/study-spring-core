package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {
	
	private final MemberRepository memberRepository;
	
	// 할인 정책
	private final DiscountPolicy discountPolicy;
	
	// ** 의존관계 주입(Dependency Injection) - 생성자 주입(Constructor Injection)
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}
	
	/*
	 * ** DIP
	 *  - 추상(인터페이스) 의존
	 *    => DiscountPolicy
	 *  - 구체(구현) 클래스 의존하지 않음
	 *    => FixDiscountPolicy, RateDiscountPolicy 중 어떤 구현 객체가 들어올지 OrderServiceImpl에서 알 수 없음.
	 *    => OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정함.
	 *  
	 * ** OCP
	 *  - 기능 확장, 변경 시 클라이언트 코드(Service)에 영향이 없음
	 *    => 할인 정책 변경 시에도 ServiceImpl 코드에는 변화가 생기지 않는다.
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

	
	// test용
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
}

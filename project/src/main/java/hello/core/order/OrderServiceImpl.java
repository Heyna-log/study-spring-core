package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	/*
	* ** 생성자 주입 방식을 사용하면 final 키워드 사용 가능
	*
	* final 키워드 사용
	*  - 필드 선언 시 초기값을 세팅하거나, 생성자 메소드를 통해서만 값을 넣어줄 수 있다.
	*  - 이후에는 값을 변경할 수 없다.
	*/
	@Nullable private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy; // 할인 정책
	
	// ** @RequiredArgsConstructor를 사용하면 생성자 의존관계 주입 메소드(아래 주석 코드)를 자동으로 생성해준다!!
	// final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
//	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//		this.memberRepository = memberRepository;
//		this.discountPolicy = discountPolicy;
//	}
	
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

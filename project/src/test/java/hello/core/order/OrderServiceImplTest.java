package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {

        // 의존관계 주입 방식이 수정자 주입일 경우
//        OrderServiceImpl orderService = new OrderServiceImpl(); // -> 어떤 데이터를 필수로 넣어주어야 하는지 코드 상에서 바로 보이지 않음.

        // 의존관계 주입 방식이 생성자 주입일 경우
        // -> 어떤 데이터를 필수로 넣어주어야 하는지 생성자 호출 시점에서 바로 알 수 있음. (데이터 누락 방지)
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());

        Order order = orderService.createOrder(1L, "item", 10000);
        Assertions.assertThat(order.getItemPrice()).isEqualTo(10000);
    }
}
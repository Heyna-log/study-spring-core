package hello.core.singleton;

public class StatefulService {
	
//	private int price; // 상태를 유지하는 필드
	
	// 주문 시 가격을 저장
	public int order(String name, int price) {
		System.out.println("name : " + name + " | price : " + price);
		return price; // 공유되지 않는 지역변수로 값을 넘김
	}
	
//	public int getPrice() {
//		return price;
//	}

}

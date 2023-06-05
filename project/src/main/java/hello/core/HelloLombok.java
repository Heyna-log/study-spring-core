package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
	
	private String name;
	private int age;
	
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok();
		
		// Lombok Annotation을 사용해서 getter, setter 메소드를 만들지 않고도 사용 가능
		// getter, setter 외에도 생성자 등 다양한 기능을 제공하고 있음. ex) @NoArgsConstructor
		helloLombok.setName("Hello Lombok!");
		String name = helloLombok.getName();
		
		System.out.println("name: " + name);
		System.out.println("HelloLombok: " + helloLombok); // ToString
	}
}

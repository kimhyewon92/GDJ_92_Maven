package com.winter.app.lamda;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LamdaTest {

	@Test
	void test() {
		int n1 = 10;
		int n2 = 10;
		// 람다식 재정의를 위해서는 인터페이스에 메서드 1개만 선언하기 어떤걸 재정의할지 몰라서 에러나기때문..
		TestFunction testFunction = (int a, int b)-> a+b;
		int result = testFunction.f1(n1, n2);
		
		// 내장 (객체타입을넣어야함 제네릭에)
		Consumer<Integer> con = (n)-> System.out.println(n);
		//오른쪽 출력문이 accept
		
		con.accept(3);
		
		System.out.println(result);
	}

}

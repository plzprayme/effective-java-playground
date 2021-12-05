package dynamicproxy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

class StockRepositoryTest {

	private static SimpleRepositoryInterface repository;

	@BeforeAll
	static void DI() {
		SimpleRepositoryInterface target = new SimpleRepositoryTarget(); // 인터페이스 타입을 프록시 구현체로 인스턴스화 생성
		repository = (SimpleRepositoryInterface) Proxy.newProxyInstance(
				StockRepository.class.getClassLoader(),							// 내가 정의한 레포지토리
				StockRepository.class.getInterfaces(),							// 내가 정의한 레포지토리의 인터페이스 SimpleRepositoryInterface()
				new DynamicProxyInvocationHandler(target));              // InvocationHandler 정의 (트랜잭션, 로깅 등 가능)

		System.out.println("");
		// 그런데 StockRepository 타입으로 인스턴스화 할 수 없는걸까?
		// 문제 1. Proxy 를 제대로 이해하고 있지 않다.
		// 문제 2. Spring Data JPA가 Repository를 어떻게 생성하는지 모르고 있다.


		ProxyFactory proxyFactory = new ProxyFactory();
	}

	@Test
	void 다이나믹_프록시_테스트() {

		repository.save(new Stock("삼성전자", 70000L));
		System.out.println();
		assertEquals("삼성전자", repository.findById(1L).name());
		System.out.println();
		assertEquals(70000L, repository.findById(1L).price());
		System.out.println();
	}

}
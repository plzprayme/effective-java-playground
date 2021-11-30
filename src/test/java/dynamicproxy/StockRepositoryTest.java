package dynamicproxy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StockRepositoryTest {

	private static SimpleRepositoryInterface repository;

	@BeforeAll
	static void 다이나믹_프록시로_Repository_구현체_생성() {
		SimpleRepositoryInterface proxyInstance = new SimpleRepositoryProxy(); // 인터페이스 타입을 프록시 구현체로 인스턴스화 생성

		repository = (SimpleRepositoryInterface) Proxy.newProxyInstance(
				StockRepository.class.getClassLoader(),							// 내가 정의한 레포지토리
				StockRepository.class.getInterfaces(),							// 내가 정의한 레포지토리의 인터페이스 SimpleRepositoryInterface()
				new DynamicProxyInvocationHandler(proxyInstance));              // InvocationHandler 정의 (트랜잭션, 로깅 등 가능)
	}

	@Test
	void 다이나믹_프록시_테스트() {
		repository.save(new Stock("삼성전자", 70000L));
		assertEquals("삼성전자", repository.findById(1L).name());
		assertEquals(70000L, repository.findById(1L).price());
	}

}
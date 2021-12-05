package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyInvocationHandler implements InvocationHandler {

	private final Object target;

	public DynamicProxyInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("START TRANSACTION " + method.getName());

		if (method.getName().equals("findById")) {
			System.out.printf("SELECT * FROM stock WHERE id = %d\n", args[0]);
		}

		if (method.getName().equals("save")) {
			Stock entity = (Stock) args[0];
			System.out.printf("INSERT INTO stock (id, name, price) VALUES (?, %s, %d)\n", entity.name(), entity.price());
		}

		Object result = method.invoke(target, args);
		System.out.println("COMMIT TRANSACTION " + method.getName());
		return result;
	}
}

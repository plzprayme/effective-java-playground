package dynamicproxy;

import java.util.HashMap;
import java.util.Map;

public class SimpleRepositoryTarget implements SimpleRepositoryInterface {

	private static Long id = 1L;

	private static final Map<Long, Stock> dataSource = new HashMap<>();

	@Override
	public Stock findById(Long id) {
		return dataSource.get(id);
	}

	@Override
	public void save(Stock entity) {
		dataSource.put(id++, entity);
	}
}

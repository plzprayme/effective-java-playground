package dynamicproxy;

import dynamicproxy.annotation.SimpleRepository;

@SimpleRepository
public interface SimpleRepositoryInterface {

	Stock findById(Long id);

	void save(Stock entity);

}

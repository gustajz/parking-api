package parking.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import parking.domain.Veiculo;

/**
 * 
 * @author gustavojotz
 *
 */
public interface VeiculoRepository
		extends PagingAndSortingRepository<Veiculo, Long>, QueryDslPredicateExecutor<Veiculo> {

}

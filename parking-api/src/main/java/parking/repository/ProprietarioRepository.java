package parking.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import parking.domain.Proprietario;

public interface ProprietarioRepository extends PagingAndSortingRepository<Proprietario, Long>, QueryDslPredicateExecutor<Proprietario> {

}

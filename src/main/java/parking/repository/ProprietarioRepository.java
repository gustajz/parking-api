package parking.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import parking.domain.Proprietario;

/**
 * 
 * @author gustavojotz
 *
 */
public interface ProprietarioRepository
		extends PagingAndSortingRepository<Proprietario, Long>, QueryDslPredicateExecutor<Proprietario> {

	Proprietario findByUsuarioIgnoreCase(final String usuario);

}

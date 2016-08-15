package parking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.Predicate;

import parking.domain.QVeiculo;
import parking.repository.VeiculoRepository;

/**
 * 
 * @author gustavojotz
 *
 */
@Component("veiculoSecurityEvaluator")
public class VeiculoSecurityEvaluator {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	public boolean isOwner(final Long id, final UserDetails user) {
		QVeiculo qVeiculo = QVeiculo.veiculo;
		Predicate predicate = qVeiculo.id.eq(id).and(qVeiculo.proprietario.usuario.eq(user.getUsername()));
		return veiculoRepository.exists(predicate);
	}
	
}

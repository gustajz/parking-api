package parking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.Predicate;

import parking.domain.Proprietario;
import parking.domain.QProprietario;
import parking.repository.ProprietarioRepository;

/**
 * 
 * @author gustavojotz
 *
 */
@Component("proprietarioSecurityEvaluator")
public class ProprietarioSecurityEvaluator {

	@Autowired
	private ProprietarioRepository proprietarioRepository;
	
	public boolean isOwner(final Proprietario proprietario, final Authentication authentication) {
		QProprietario qProprietario = QProprietario.proprietario;
		Predicate predicate = qProprietario.usuario.eq(authentication.getName()).and(qProprietario.id.eq(proprietario.getId()));
		return proprietarioRepository.exists(predicate);
	}
	
}

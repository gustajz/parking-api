package parking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
@Component("permissionEvaluator")
public class PermissionEvaluator {

	@Autowired
	private ProprietarioRepository proprietarioRepository;
	
	public boolean isOwner(final Proprietario proprietario, final UserDetails user) {
		QProprietario qProprietario = QProprietario.proprietario;
		Predicate predicate = qProprietario.usuario.eq(user.getUsername()).and(qProprietario.id.eq(proprietario.getId()));
		return proprietarioRepository.exists(predicate);
	}
	
}

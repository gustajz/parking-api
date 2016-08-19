package parking.security.listener;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import parking.domain.Proprietario;
import parking.repository.ProprietarioRepository;

/**
 * Listener para inclusão do {@link Proprietario} no primeiro acesso.
 *  
 * @author gustavojotz
 *
 */
@Component
@Slf4j
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	@Autowired
	private ProprietarioRepository proprietarioRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		LdapUserDetails userDetails = (LdapUserDetails) event.getAuthentication().getPrincipal();
		log.info("Login Successful: {}", userDetails.getUsername());

		Proprietario proprietario = proprietarioRepository.findByUsuario(userDetails.getUsername());
		if (proprietario == null) {
			log.debug("Primeiro acesso de {}", userDetails.getUsername());

			proprietario = new Proprietario();
			proprietario.setUsuario(userDetails.getUsername());

			completarComNome(proprietario, userDetails);
		}

		proprietario.setDataLogin(new Date());
		proprietarioRepository.save(proprietario);

	}

	/**
	 * Obtem o nome do retorno da autenticação e atribui ao objeto {@link Proprietario#setNome(String)} .
	 * 
	 * @param proprietario
	 *            objeto não nulo
	 * @param userDetails
	 *            objeto não nulo
	 */
	protected void completarComNome(final Proprietario proprietario, final LdapUserDetails userDetails) {
		try {
			LdapName ldapName = new LdapName(userDetails.getDn());
			Optional<Rdn> rdn = ldapName.getRdns().stream().filter(r -> "CN".equals(r.getType())).findFirst();
			rdn.ifPresent(new Consumer<Rdn>() {

				@Override
				public void accept(Rdn rdn) {
					proprietario.setNome(rdn.getValue().toString());
				}
			});
		} catch (InvalidNameException ine) {
			log.error(ine.getExplanation(), ine);
		}
	}

}

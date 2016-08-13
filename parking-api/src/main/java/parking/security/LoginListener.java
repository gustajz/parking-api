package parking.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import parking.domain.Proprietario;
import parking.repository.ProprietarioRepository;

/**
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
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
		log.info("Login Successful: {}", userDetails.getUsername());
		
		Proprietario proprietario = proprietarioRepository.findByUsuario(userDetails.getUsername());
		if (proprietario == null) {
			log.debug("Primeiro acesso de {}", userDetails.getUsername());
			
			proprietario = new Proprietario();
			proprietario.setUsuario(userDetails.getUsername());
		}

		proprietario.setDataLogin(new Date());
		proprietarioRepository.save(proprietario);
		
	}
}

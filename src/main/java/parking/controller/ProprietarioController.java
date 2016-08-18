package parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parking.domain.Proprietario;
import parking.repository.ProprietarioRepository;

/**
 * 
 * @author gustavojotz
 *
 */
@RestController
@RequestMapping(path = "/proprietario", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Transactional(propagation = Propagation.NEVER)
public class ProprietarioController {

	@Autowired
	private ProprietarioRepository proprietarioRepository;

	/**
	 * Atualiza as informações do proprietario autenticado.
	 * 
	 * @param proprietario
	 *            objeto {@link Proprietario}
	 * @return returna o proprietario atualizado
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("@proprietarioSecurityEvaluator.isOwner(#proprietario, authentication)")
	public Proprietario update(@RequestBody(required = true) Proprietario proprietario) {
		return proprietarioRepository.save(proprietario);
	}

	/**
	 * Retorna o proprietario autenticado.
	 * 
	 * @param authentication
	 *            objeto Authentication
	 * @return returna o proprietario autenticado
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Proprietario get(Authentication authentication) {
		return proprietarioRepository.findByUsuario(authentication.getName());
	}
	
}

package parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import parking.domain.Proprietario;
import parking.repository.ProprietarioRepository;
import springfox.documentation.annotations.ApiIgnore;

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
	@ApiOperation(value = "Atualiza os dados do Proprietário")
	public ResponseEntity<Proprietario> update(@RequestBody(required = true) Proprietario proprietario) {
		proprietarioRepository.save(proprietario);
		return new ResponseEntity<>(proprietario, HttpStatus.OK);
	}

	/**
	 * Retorna o proprietario autenticado.
	 * 
	 * @param authentication
	 *            objeto Authentication
	 * @return returna o proprietario autenticado
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Retorna o Proprietário autenticado")
	public ResponseEntity<Proprietario> get(@ApiIgnore Authentication authentication) {
		return new ResponseEntity<>(proprietarioRepository.findByUsuarioIgnoreCase(authentication.getName()), HttpStatus.OK);
	}
	
}

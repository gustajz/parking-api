package parking.controller;

import java.security.Principal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import parking.domain.QVeiculo;
import parking.domain.Veiculo;
import parking.repository.VeiculoRepository;

/**
 * 
 * @author gustavojotz
 *
 */
@RestController
@RequestMapping(path = "/veiculo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Transactional(propagation = Propagation.NEVER)
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;

	/**
	 * Inclui ou atualiza um {@link Veiculo}Veiculo do Usuário autenticado.
	 * 
	 * @param veiculo
	 * @return {@link Veiculo} cadastrado.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@PreAuthorize("@proprietarioSecurityEvaluator.isOwner(#veiculo.proprietario, authentication)")
	public ResponseEntity<Veiculo> updateOrNew(@RequestBody(required = true) Veiculo veiculo) {

		if (veiculo.getId() != null && !veiculoRepository.exists(veiculo.getId())) {
			veiculo.setId(null);
		}
		
		veiculoRepository.save(veiculo);
		
        return new ResponseEntity<>(veiculo, HttpStatus.OK);
	}

	/**
	 * Remove um {@link Veiculo} do Usuário autenticado.
	 * 
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	@PreAuthorize("@veiculoSecurityEvaluator.isOwner(#id, authentication)")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		veiculoRepository.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Retorna todos os Veiculos do Usuário autenticado.
	 * 
	 * @param principal
	 *            objeto {@link Principal}
	 * @param pageable
	 * @return todos os Veiculos do Usuário paginado de 10 em 10.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Veiculo>> get(Authentication authentication, 
											@PageableDefault(sort = { "placa" }, direction = Direction.ASC) Pageable pageable) {
		
		Predicate predicate = QVeiculo.veiculo.proprietario.usuario.eq(authentication.getName());
		
		Page<Veiculo> page = veiculoRepository.findAll(predicate, pageable);
		
		page.forEach(v -> v.getProprietario().getId());
		
        return new ResponseEntity<>(page, HttpStatus.OK);

	}

	/**
	 * Retorna os Veiculos de acordo com o filtro.
	 * 
	 * @param placa
	 *            Número da Placa do Veiculo. Busca com like à direita.
	 * @return todos os Veiculos do Usuário paginado de 10 em 10.
	 */
	@RequestMapping(value = "/pesquisar", method = RequestMethod.GET)
	public ResponseEntity<Page<Veiculo>> pesquisar(
			@RequestParam(name = "placa", required = true) String placa, 
			@PageableDefault(sort = { "placa" }, direction = Direction.ASC) Pageable pageable) {
		
		Validate.notBlank(placa);
		
		Predicate predicate = QVeiculo.veiculo.placa.likeIgnoreCase(StringUtils.remove(placa, '%') + '%');

		Page<Veiculo> page = veiculoRepository.findAll(predicate, pageable);
		
		page.forEach(v -> v.getProprietario().getId()); // TODO buscar uma
														// forma de arrumar
														// no
														// QueryDslPredicateExecutor
		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED, reason="Placa não informada")
	@ExceptionHandler(IllegalArgumentException.class)
	public void illegalArgumentException() {}
	
	@ResponseStatus(value=HttpStatus.CONFLICT, reason="Registro existente")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void DataIntegrityViolationException() {}
	
}

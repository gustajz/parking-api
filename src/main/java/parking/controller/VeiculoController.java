package parking.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping(path = "/veiculo")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@PreAuthorize("@permissionEvaluator.isOwner(#veiculo.proprietario, principal)")
	public Veiculo update(@RequestBody(required = true) Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@RequestMapping(method = { RequestMethod.DELETE })
	@PreAuthorize("@permissionEvaluator.isOwner(#veiculo.proprietario, principal)")
	public void delete(@RequestBody(required = true) Veiculo veiculo) {
		veiculoRepository.delete(veiculo);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Veiculo> pesquisar(@RequestParam(name = "placa", required = true) @Size(min = 3) String placa) {

		QVeiculo qVeiculo = QVeiculo.veiculo;

		Predicate predicate = qVeiculo.placa.likeIgnoreCase(placa + '%');

		Iterable<Veiculo> iterable = veiculoRepository.findAll(predicate);
		iterable.forEach(v -> v.getProprietario().getId()); // TODO buscar uma
															// forma de arrumar
															// no
															// QueryDslPredicateExecutor

		Stream<Veiculo> stream = StreamSupport.stream(iterable.spliterator(), false);

		return stream.collect(Collectors.toList());
	}

}

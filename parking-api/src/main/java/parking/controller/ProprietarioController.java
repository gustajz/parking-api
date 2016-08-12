package parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import parking.model.Proprietario;
import parking.repository.NoSQLProprietarioRepository;

@RestController
@RequestMapping(path = "/proprietario")
public class ProprietarioController {

	@Autowired
	private NoSQLProprietarioRepository proprietarioRepository;

	@RequestMapping(path = "/placa/{placa}", method = RequestMethod.GET)
	public List<Proprietario> buscarPorPlaca(@PathVariable(value = "placa") String placa) {
		return proprietarioRepository.getByPlaca(placa);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void newProprietario(@RequestBody Proprietario proprietario) {
		proprietarioRepository.add(proprietario);
	}

}

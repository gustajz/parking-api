package parking.repository;

import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.springframework.stereotype.Component;

import parking.model.Veiculo;

@Component
public class VeiculoRepository extends CouchDbRepositorySupport<Veiculo> {

	public VeiculoRepository(CouchDbConnector db) {
		super(Veiculo.class, db, true);
		initStandardDesignDocument();
	}

//	@View(name = "by_placa", map = "function(doc) { if (doc.tipo) { emit(doc.placa, doc) } }")
	@View(name = "by_placa", map = "function(doc) { if (doc.usuario) { doc.veiculos.forEach(function(v) { emit(v.placa, v); }); }	}")
	public List<Veiculo> getVeiculoByPlaca(final String placa) {
		return queryView("by_placa", placa);
	}

}

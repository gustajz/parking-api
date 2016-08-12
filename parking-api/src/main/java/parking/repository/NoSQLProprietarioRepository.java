package parking.repository;

import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.springframework.stereotype.Component;

import parking.model.Proprietario;

@Component
public class NoSQLProprietarioRepository extends CouchDbRepositorySupport<Proprietario> {

	public NoSQLProprietarioRepository(CouchDbConnector db) {
		super(Proprietario.class, db, true);
		initStandardDesignDocument();
	}
	
	@View(name = "by_placa", map = "function(doc) { if (doc.usuario) { doc.veiculos.forEach(function(v) { emit(v.placa, doc); }); }	}")
	public List<Proprietario> getByPlaca(final String placa) {
		return queryView("by_placa", placa);
	}
	
}

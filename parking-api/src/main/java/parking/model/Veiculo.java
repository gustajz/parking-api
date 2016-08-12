package parking.model;

import javax.validation.constraints.NotNull;

import org.ektorp.support.CouchDbDocument;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author gustavojotz
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({ "_attachments", "_id", "_rev" })
public class Veiculo extends CouchDbDocument {

	private static final long serialVersionUID = 1L;

	public enum Tipo {
		CARRO, MOTO
	}

	@NotNull
	private Tipo tipo;

	@NotEmpty
	private String cor;

	@NotEmpty
	private String modelo;

	@NotEmpty
	private String placa;

}
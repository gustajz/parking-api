package parking.model;

import java.util.HashSet;
import java.util.Set;

import org.ektorp.support.CouchDbDocument;
import org.hibernate.validator.constraints.Email;
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
//@JsonIgnoreProperties(ignoreUnknown = true, value = { "_attachments", "_id", "_rev" })
public class Proprietario extends CouchDbDocument {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String nome;

	@NotEmpty
	private String celular;

	@Email
	private String email;

	@NotEmpty
	private String usuario;

	private Set<Veiculo> veiculos = new HashSet<>();

}
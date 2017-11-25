package parking.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author gustavojotz
 *
 */
@Setter
@Getter
@Entity
@DynamicUpdate
@DynamicInsert
public class Proprietario extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "proprietario_id_seq", sequenceName = "proprietario_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proprietario_id_seq")
	@Column(updatable = false)
	private Long id;

	@NotEmpty
	@Column(updatable = false)
	private String usuario;

	@NotEmpty
	private String nome;
	
	private String celular;

	private Integer ramal;
	
	private Integer andar;

	private String posicao;
	
	private String gravatar;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_criacao", updatable = false)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_login")
	private Date dataLogin;
	
	@NotNull
	@Column(name = "participa_sorteio")
	private Boolean participaSorteio = Boolean.FALSE;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_contemplacao", updatable = false, insertable = false)
	private Date dataContemplacao;

	@Column(name = "vaga_gerencial", updatable = false, insertable = false)
	private Boolean vagaGerencial = Boolean.FALSE;

	@Column(name = "numero_vaga", updatable = false, insertable = false)
	private String numeroVaga;
	
	private String cidade;
	
	private String bairro;

	@PrePersist
	public void before() {
		this.dataCriacao = new Date();
		setUsuario(StringUtils.lowerCase(getUsuario()));
	}
	
}

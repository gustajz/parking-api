package parking.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
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
@Audited
public class Veiculo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "veiculo_id_seq", sequenceName = "veiculo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo_id_seq")
	@Column(updatable = false)
	private Long id;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(updatable = false)
	private Proprietario proprietario;

	@NotEmpty
	private String cor;

	@NotEmpty
	private String modelo;

	@NotEmpty
	private String placa;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_criacao", updatable = false)
	private Date dataCriacao;
	
	@PrePersist
	public void before() {
		this.dataCriacao = new Date();
	}

}
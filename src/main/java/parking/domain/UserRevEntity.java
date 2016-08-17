package parking.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import lombok.Getter;
import lombok.Setter;
import parking.security.UserRevisionListener;

@Entity
@Table(name = "REVINFO")
@Setter
@Getter
@DynamicUpdate
@DynamicInsert
@RevisionEntity(UserRevisionListener.class)
public class UserRevEntity extends DefaultRevisionEntity {

	private static final long serialVersionUID = 1L;

	private String usuario;
}

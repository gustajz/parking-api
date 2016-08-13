package parking.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * 
 * @author gustavojotz
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Serializable getId();

}
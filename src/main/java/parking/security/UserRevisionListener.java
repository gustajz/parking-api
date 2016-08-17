package parking.security;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import parking.domain.UserRevEntity;

public class UserRevisionListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		((UserRevEntity) revisionEntity).setUsuario(auth.getName());
	}

}

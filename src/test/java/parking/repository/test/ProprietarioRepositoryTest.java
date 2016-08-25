package parking.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import parking.domain.Proprietario;
import parking.repository.ProprietarioRepository;

/**
 * 
 * @author gustavojotz
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@Sql("classpath:data-test.sql")
public class ProprietarioRepositoryTest {

	@Autowired
	private ProprietarioRepository repository;

	@Test
	public void findByUsuarioRetornaProprietario() {
		Proprietario p = this.repository.findByUsuarioIgnoreCase("gustavojotz");

		assertThat(p.getNome()).isEqualTo("Gustavo Jotz");
		assertThat(p.getUsuario()).isEqualTo("gustavojotz");
	}

}
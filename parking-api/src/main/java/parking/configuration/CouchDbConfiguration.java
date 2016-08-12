package parking.configuration;

import java.util.Properties;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.spring.HttpClientFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CouchDbConfiguration {

	@Autowired
	private Environment environment;

	@Bean
    public CouchDbConnector connector() throws Exception {

		String url = environment.getProperty("COUCHDB_URL", "http://localhost:5984/");
        String databaseName = environment.getProperty("COUCHDB_DBNAME", "parking");
        
        log.info("Connection to {}{}", url, databaseName);

        Properties properties = new Properties();
        properties.setProperty("autoUpdateViewOnChange", "true");

        HttpClientFactoryBean factory = new HttpClientFactoryBean();
        factory.setUrl(url);
        factory.setProperties(properties);
        factory.afterPropertiesSet();
        HttpClient client = factory.getObject();

        CouchDbInstance dbInstance = new StdCouchDbInstance(client);
        return new StdCouchDbConnector(databaseName, dbInstance);
    }

}

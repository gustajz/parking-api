package parking.controller;

import java.lang.management.ManagementFactory;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author gustavojotz
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HealthController {

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health() {
		return "Running";
	}

	@RequestMapping(value = "/uptime", method = RequestMethod.GET)
	public String uptime() {
		return DurationFormatUtils.formatDurationWords(ManagementFactory.getRuntimeMXBean().getUptime(), false, false);
	}
	
	@RequestMapping(value = "/logged-in", method = RequestMethod.GET)
	public ResponseEntity<Boolean> loggedIn(Authentication authentication) {
		return new ResponseEntity<>(authentication.isAuthenticated(), HttpStatus.OK);
	}
	
}

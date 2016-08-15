package parking.controller;

import java.lang.management.ManagementFactory;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author gustavojotz
 *
 */
@RestController
public class HealthController {

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health() {
		return "Running";
	}

	@RequestMapping(value = "/uptime", method = RequestMethod.GET)
	public String uptime() {
		return DurationFormatUtils.formatDurationWords(ManagementFactory.getRuntimeMXBean().getUptime(), false, false);
	}
}

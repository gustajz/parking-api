package parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@ComponentScan(basePackageClasses = ParkingApplication.class)
@Controller
public class ParkingApplication {

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	@ResponseBody
	public String index() {
		return "Running";
	}

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

}
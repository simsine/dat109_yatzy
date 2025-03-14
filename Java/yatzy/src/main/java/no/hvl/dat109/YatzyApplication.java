package no.hvl.dat109;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class YatzyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(YatzyApplication.class, args);
	}
	
	/**
	 * Metode for bygging i war format
	 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(YatzyApplication.class);
    }
}

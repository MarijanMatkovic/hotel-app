package hr.fer.progi.projekt.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class HotelProjektApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelProjektApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			/**
			 * za lokalno testiranje dodato samo .allowedOrigins("http://localhost:4200//")
			 */
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("https://hotelprogi.herokuapp.com/").allowedOrigins("http://localhost:4200");
			}
		};
	}
}



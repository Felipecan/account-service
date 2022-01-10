package com.example.AccountService.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Configuration
@ConfigurationProperties
public class Properties {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class App {
		
		private String name;
		private Map<String, Services> services = new HashMap<>();
	
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public static class Services {
			
			private String name;
			private String host;
		}
	}
	
	private final App app = new App();
}

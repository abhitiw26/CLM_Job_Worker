package com.ltim.lc.app.clm;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class CLMJobWorkerStarterApplication {

	Logger log = LoggerFactory.getLogger(CLMJobWorkerStarterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CLMJobWorkerStarterApplication.class, args);
	}


	@PostConstruct
	public void setProperties() {
		System.setProperty("smtp.host", "smtp-mail.outlook.com");
		System.setProperty("smtp.port", "587");
		System.setProperty("smtp.email.account", "");
		System.setProperty("smtp.email.account.password", "");
		System.setProperty("smtp.auth.enabled", "true");
		System.setProperty("smtp.starttls.enabled", "true");
	}
	
	/**
	 * Don't delete or comment before Bean
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
	    RestTemplate restTemplate = new RestTemplate();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setConnectTimeout(30000);
	    requestFactory.setReadTimeout(30000);

	    restTemplate.setRequestFactory(requestFactory);
	    return restTemplate;
	}
	
	
}

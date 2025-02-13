package com.lvr.Dhakiya_backend;

import com.lvr.Dhakiya_backend.appConfig.CustomBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DhakiyaBackendApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DhakiyaBackendApplication.class);
    	application.setBanner(new CustomBanner());
		application.run(args);
	}

}

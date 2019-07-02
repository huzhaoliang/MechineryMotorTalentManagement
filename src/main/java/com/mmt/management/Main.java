package com.mmt.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Main 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(Main.class, args);
	}
	
	protected SpringApplicationBuilder configure( SpringApplicationBuilder builder) 
	{
        return builder.sources(this.getClass());
	}

}

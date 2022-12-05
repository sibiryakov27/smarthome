package com.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class SmartHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		Locale rus = new Locale("ru", "RU");
		localeResolver.setDefaultLocale(rus);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	InfoContributor getInfoContributor() {
		Map<String, Object> details = new HashMap<>();
		details.put("nameApp", "SmartHome");
		details.put("developers", "Ivan Sibiryakov and Ivan Smeyukha");
		Map<String, Object> wrapper = new HashMap<>();
		wrapper.put("info", details);
		return new MapInfoContributor(wrapper);
	}
}

package com.JediMasterSpring.JediBot;

import com.JediMasterSpring.JediBot.config.TelegramBotConfig;
import com.github.unafraid.telegrambot.handlers.CommandHandlers;
import com.github.unafraid.telegrambot.handlers.ICommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;


@EnableConfigurationProperties(TelegramBotConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.JediMasterSpring.JediBot.repo")
@SpringBootApplication
public class JediBotApplication extends SpringBootServletInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(JediBotApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JediBotApplication.class);
	}

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(JediBotApplication.class);

		// Handlers setup
		final Map<String, ICommandHandler> handlers = context.getBeansOfType(ICommandHandler.class);
		handlers.values().forEach(handler -> {
					CommandHandlers.getInstance().addHandler(handler);
					LOGGER.info("Loaded handler: {}", handler.getClass().getSimpleName());
				});

		LOGGER.info("Loaded {} handlers ", handlers.size());

	}
}

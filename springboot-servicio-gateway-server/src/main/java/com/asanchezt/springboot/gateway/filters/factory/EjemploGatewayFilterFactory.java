/**
 * 
 */
package com.asanchezt.springboot.gateway.filters.factory;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

/**
 * @author asancheztornero
 *
 */
@Component
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<Configuracion> {

	private final Logger log = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);

	public EjemploGatewayFilterFactory() {
		super(Configuracion.class);
	}

	@Override
	public GatewayFilter apply(Configuracion config) {
		return (exchange, chain) -> {
			log.info("Ejecutando pre gateway filter factory " + config.getMensaje());
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				Optional.ofNullable(config.getCookieValor()).ifPresent(cookie -> {
					exchange.getResponse().addCookie(ResponseCookie.from(config.getCookieNombre(), cookie).build());
				});
				log.info("Ejecutando post gateway filter factory");
			}));
		};
	}

	@Override
	public String name() {
		return "EjemploCookie";
	}

}

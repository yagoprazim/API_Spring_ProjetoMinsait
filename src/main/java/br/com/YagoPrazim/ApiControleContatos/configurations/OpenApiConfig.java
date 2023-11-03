package br.com.YagoPrazim.ApiControleContatos.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("basicScheme",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.info(new Info()
						.title("API Controle de Contatos")
						.description("Esta API permite gerenciar 2 Entidades que se relacionam: " +
								"Pessoas e Contatos, possibilitando, resumidamente: o cadastro, visualização, " +
								"atualização e exclusão de Pessoas e os seus Contatos. Para " +
								"saber mais sobre a estrutura implementada, por favor, acessar " +
								"o Readme no GitHub clicando abaixo.")
						.contact(new Contact().name("Yago Prazim")
								.email("prazimyago@gmail.com")
								.url("https://github.com/yagoprazim/API_Spring_ProjetoMinsait.git"))
						.version("Versão 0.0.1-SNAPSHOT"));
	}
}

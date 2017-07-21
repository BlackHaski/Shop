package shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by blackhaski on 03.07.17.
 */
@Configuration
@EnableWebSecurity
public class SecurityInit extends AbstractSecurityWebApplicationInitializer{
}

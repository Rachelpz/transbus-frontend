package pweb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* Este code le indica a spring donde se encuentran los componentes */
@Configuration
@ComponentScan(basePackages = {"pweb"})
public class SpringConfig {

}

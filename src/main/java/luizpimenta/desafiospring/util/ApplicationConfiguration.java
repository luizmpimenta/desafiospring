package luizpimenta.desafiospring.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class ApplicationConfiguration implements EnvironmentAware {

    private static Environment environment;

    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    public String readProperty(String key) {
        return environment.getProperty(key);
    }

}
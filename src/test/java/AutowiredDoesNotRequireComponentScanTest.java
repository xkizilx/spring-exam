import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowiredDoesNotRequireComponentScanTest {

    @Test
    void testAnnotationConfigApplicationContext() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Assertions.assertNotNull(context.getBean(ComplexBean.class).simpleBean);
    }

    @Test
    void testClassPathXmlApplicationContext() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("AutowiredDoesNotRequireComponentScanTest.xml");

        Assertions.assertNotNull(context.getBean(ComplexBean.class).simpleBean);
    }
}

@Configuration
class Config {

    @Bean
    public ComplexBean complexBean() {
        return new ComplexBean();
    }

    @Bean
    public SimpleBean simpleBean() {
        return new SimpleBean();
    }
}

class ComplexBean {

    @Autowired
    SimpleBean simpleBean;
}

class SimpleBean {
}


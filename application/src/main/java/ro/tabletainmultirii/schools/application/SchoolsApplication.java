package ro.tabletainmultirii.schools.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "ro.tabletainmultirii.api")
public class SchoolsApplication { //implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SchoolsApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(SchoolsApplication.class, args);
	}

//	@Autowired
//	private ApplicationContext appContext;
//
//	@Override
//	public void run(String... args) throws Exception
//	{
//		String[] beans = appContext.getBeanDefinitionNames();
//		Arrays.sort(beans);
//		for (String bean : beans)
//		{
//			System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
//		}
//	}

}

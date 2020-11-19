package ro.tabletainmultirii.api.infrastructure.schools.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.tabletainmultirii.api.schools.domain.services.SchoolDataPointService;
import ro.tabletainmultirii.api.schools.domain.services.SchoolDataPointServiceImpl;
import ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository;


@Configuration
public class SchoolDataPointServiceConfiguration {

    @Autowired
    private SchoolDataPointRepository repository;

    @Bean
    public SchoolDataPointService schoolDataPointService() {
        return new SchoolDataPointServiceImpl(repository);
    }
}

package bg.softuni.ElevatorRegister.config;

import bg.softuni.ElevatorRegister.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    @Bean
//    public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
//                                                       RoleRepository userRoleRepository,
//                                                       ResourceLoader resourceLoader) {
//        DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource);
//
//        if (userRoleRepository.count() == 0) {
//            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//            populator.addScript(resourceLoader.getResource("classpath:data.sql"));
//            initializer.setDatabasePopulator(populator);
//        }
//
//        return initializer;
//    }
}

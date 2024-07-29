package bg.softuni.ElevatorRegister;

import bg.softuni.ElevatorRegister.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElevatorRegisterApplication {

        public static void main(String[] args) {
            SpringApplication.run(ElevatorRegisterApplication.class, args);
        }

}

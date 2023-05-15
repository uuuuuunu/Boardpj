package ptp.myboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan()
public class MyboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyboardApplication.class, args);
	}


}

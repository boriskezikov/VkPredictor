package kezikov.vkprocessor;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VkprocessorApplication {


	public static void main(String[] args) {
		SpringApplication.run(VkprocessorApplication.class, args);

	}


}

package kezikov.vkprocessor;


import kezikov.vkprocessor.service.TvShowsService;
import kezikov.vkprocessor.service.TvShowsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.w3c.dom.NodeList;

@SpringBootApplication
@EnableCaching
public class VkprocessorApplication {


	public static void main(String[] args) {
		SpringApplication.run(VkprocessorApplication.class, args);

	}


}

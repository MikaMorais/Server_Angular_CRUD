package com.crud.server;

import com.crud.server.enumeration.Status;
import com.crud.server.model.Server;
import com.crud.server.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16GB", "Personal PC", "http://localhost:8080/server/images/server1.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.58", "Red Hat Linux", "64GB", "Mail Server", "http://localhost:8080/server/images/server2.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.21", "MS 2008", "32GB", "Web Server", "http://localhost:8080/server/images/server3.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.14", "Fedora Linux", "16GB", "Dell Tower", "http://localhost:8080/server/images/server4.png", Status.SERVER_UP));
		};
	}

}

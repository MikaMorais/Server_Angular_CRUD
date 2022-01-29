package com.crud.server.resource;

import com.crud.server.enumeration.Status;
import com.crud.server.model.Response;
import com.crud.server.model.Server;
import com.crud.server.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                    .timeStamp(now())
                    .data(Map.of("servers", serverService.list(30)))
                    .message("Servers retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping Failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.create(server)))
                        .message("Server Created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.get(id)))
                        .message("Server Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", serverService.delete(id)))
                        .message("Server Deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/images/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get("./images"  + filename));
    }
}

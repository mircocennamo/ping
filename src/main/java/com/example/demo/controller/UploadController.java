package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author mirco.cennamo on 08/04/2025
 * @project spring-boot-rest-jpa
 */
@RestController
@Slf4j
public class UploadController {

   /*
        per creare un file di upload in un container docker, è necessario montare una directory del container
        come volume, in modo che i file caricati siano persistenti anche dopo il riavvio del container.
        Ad esempio, puoi utilizzare il flag -v di Docker per montare una directory locale come volume nel container:
        docker run -v /path/to/local/dir:/data/uploads your-image-name
        In questo modo, i file caricati verranno salvati nella directory locale specificata e saranno accessibili anche dopo il riavvio del container.

    */
    private static final String UPLOAD_DIR = "/data/uploads";

    @PostMapping(path="/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        log.info("File upload request received");
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            file.transferTo(filePath.toFile());
            log.info("File saved to: " + filePath);
            return ResponseEntity.ok("Saved to: " + filePath + " " + pingHost());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }



    private String pingHost() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();
        String hostname = inetAddress.getHostName();
        return String.format("ciaociao" + "pong IP: %s, Hostname: %s", ip, hostname);
    }
}

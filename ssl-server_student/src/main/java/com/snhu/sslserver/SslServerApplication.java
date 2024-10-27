package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SslServerApplication.class, args);
    }
}

// Create a RestController to handle requests
@RestController
class ChecksumController {

    // Define a GET endpoint for /hash
    @GetMapping("/hash")
    public String getChecksum() {
        // Static data
        String data = "Daniel Aguiar; Hello World Check Sum!";
        
        // Generate checksum using SHA-256
        String checksum = generateSHA256Checksum(data);

        // Return checksum as part of the response
        return "Data: " + data + " | Checksum: " + checksum;
    }

    // Helper method to generate SHA-256 checksum
    private String generateSHA256Checksum(String data) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Compute the hash for the data string
            byte[] hashBytes = md.digest(data.getBytes());
            // Convert byte array to hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle exception if SHA-256 algorithm is not available
            return "Error generating checksum";
        }
    }
}

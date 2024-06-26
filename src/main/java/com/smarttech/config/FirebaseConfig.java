package com.smarttech.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config.location}")
    private String location;

    @Value("${firebase.bucket}")
    private String bucket;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        InputStream inputStream = FirebaseConfig.class.getResourceAsStream("/" + location);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build();
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public Bucket bucket(FirebaseApp app) {
        return StorageClient
                .getInstance(app)
                .bucket(this.bucket);
    }
}
package com.smarttech.service.upload;

import com.google.cloud.storage.Bucket;
import com.smarttech.constant.ImageType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class FirebaseUploader implements IUploader {

    private final Bucket bucket;

    @Value("${firebase.url-image}")
    private String urlImage;

    @Override
    @SneakyThrows
    public String upload(MultipartFile fileItem, String fileName, ImageType imageType) {
        String fullFileName = imageType.getType() + fileName;
        this.bucket.create(fullFileName, fileItem.getInputStream(), fileItem.getContentType());
        return fullFileName;
    }

    @Override
    public String handleAfterUpload(String url) {
        String encode = URLEncoder.encode(url, StandardCharsets.UTF_8);
        return MessageFormat.format(urlImage, this.bucket.getName(), encode);
    }
}

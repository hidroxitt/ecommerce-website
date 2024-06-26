package com.smarttech.service;

import com.smarttech.constant.ImageType;
import com.smarttech.entity.Image;
import com.smarttech.repository.ImageRepository;
import com.smarttech.service.upload.IUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageDao;
    private final IUploader uploader;

    public Image insertNotCommit(MultipartFile fileItem, String fileName, ImageType imageType, String objectId) {
        String url = this.uploader.uploadFile(fileItem, fileName, imageType);
        Image image = new Image();
        image.setObjectId(objectId);
        image.setUrl(url);
        image.setType(imageType);
        return this.imageDao.save(image);
    }

    public Image insertOrUpdate(MultipartFile fileItem, String fileName, ImageType imageType, String objectId) {
        return this.imageDao.findByTypeAndObjectId(imageType, objectId)
                .map(image -> {
                    String url = this.uploader.uploadFile(fileItem, fileName, imageType);
                    image.setUrl(url);
                    return this.imageDao.save(image);
                })
                .orElseGet(() -> this.insertNotCommit(fileItem, fileName, imageType, objectId));
    }
}

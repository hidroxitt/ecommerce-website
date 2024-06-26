package com.smarttech.service.upload;

import com.smarttech.constant.ImageType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IUploader {

    default boolean validateBeforeUpload(MultipartFile multipartFile) {
        return Optional.ofNullable(multipartFile)
                .filter(x -> x.getSize() > 0)
                .isPresent();
    }

    default String uploadFile(MultipartFile item, String fileName, ImageType imageType) {
        if (!this.validateBeforeUpload(item)) return StringUtils.EMPTY;
        String upload = this.upload(item, fileName, imageType);
        return handleAfterUpload(upload);
    }

    String upload(MultipartFile fileItem, String fileName, ImageType imageType);

    String handleAfterUpload(String url);
}

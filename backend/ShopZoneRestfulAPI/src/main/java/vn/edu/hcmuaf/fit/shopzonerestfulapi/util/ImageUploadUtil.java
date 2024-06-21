package vn.edu.hcmuaf.fit.shopzonerestfulapi.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ImageUploadUtil {
    public final long MAX_IMAGE_SIZE = 1024 * 1024 * 2;
    public final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    public final String DATE_FORMAT = "yyyy-MM-dd";
    public final String FILE_NAME_FORMAT = "%s_%s_%s";

    public boolean isAllowedExtension(String fileName, String pattern) {
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(fileName);
        return matcher.matches();
    }

    public void assertAllowed(MultipartFile file, String pattern) {
        long size = file.getSize();
        if (size > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("Maximum image size is 2MB");
        }
        String fileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileName);
        if (!isAllowedExtension(extension, pattern)) {
            throw new IllegalArgumentException("Only jpg, png, gif, bmp are allowed");
        }
    }

    public String getFileName(String name) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String date = dateFormat.format(System.currentTimeMillis());
        return String.format(FILE_NAME_FORMAT, name, date);
    }
}

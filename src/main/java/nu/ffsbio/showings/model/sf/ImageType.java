package nu.ffsbio.showings.model.sf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ImageType {

    JPEG("image/jpeg", ".jpg"),
    PNG("image/png", ".png"),
    GIF("image/gif", ".gif");

    private final String contentType;
    private final String fileType;
    private static Map<String, String> imageTypeMap = Collections.unmodifiableMap(initializeMapping());

    ImageType(String contentType, String fileType) {
        this.contentType = contentType;
        this.fileType = fileType;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFileType() {
        return fileType;
    }


    public static String getFileType(String contentType) {
        if (imageTypeMap == null) {
            initializeMapping();
        }
        return imageTypeMap.get(contentType);
    }

    private static HashMap<String, String> initializeMapping() {
        HashMap<String, String> imageTypeMap = new HashMap<>();
        for (ImageType s : ImageType.values()) {
            imageTypeMap.put(s.contentType, s.fileType);
        }
        return imageTypeMap;
    }

}

package nu.ffsbio.showings.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ImageDownloader {
    private static Logger LOG = LoggerFactory.getLogger(ImageDownloader.class);

    void download(String imageUrl, String filmName) {
        String filePath = "../images/" + filmName;
        File file = new File(filePath);
        if (file.exists()) {
            return;
        }
        URLConnection connection = null;
        try {
            URL url = new URL(imageUrl);
            String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
            connection = url.openConnection();
            connection.setRequestProperty("User-Agent", USER_AGENT);
        } catch (IOException e) {
            LOG.error("Error connection with: " + imageUrl, e);
        }

        if (connection != null) {
            try (InputStream inputStream = connection.getInputStream();
                 OutputStream outputStream = new FileOutputStream(filePath)) {

                byte[] buffer = new byte[2048];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
            } catch (IOException e) {
                LOG.error("Error with reading or writing images files", e);
            }
        }
    }

    void deleteOldImages(Set<String> movieNames) {
        File imageFolder = new File("images");
        //Get all movie files
        File[] fileArray = imageFolder.listFiles(File::isFile);
        if (fileArray == null) {
            return;
        }
        //Get fileNames that should be deleted
        List<String> fileNames = Arrays.stream(fileArray)
                        .map(File::getName)
                        .filter(fileName -> !movieNames.contains(fileName))
                        .collect(Collectors.toList());
        for (String fileName : fileNames) {
            File file = new File("images/" + fileName);
            if (!file.delete()) {
                LOG.warn("Could not delete image: " + fileName);
            }
        }
    }
}

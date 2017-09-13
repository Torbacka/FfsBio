package nu.ffsbio.showings.tasks;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.imgscalr.Scalr;
import org.junit.Test;

public class SfDataCollectorTest {

    @Test
    public void test() throws IOException, URISyntaxException {
        BufferedImage img = ImageIO.read(new File("images/24-veckor1.jpg"));
        BufferedImage scaledImg = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY,
                        240,360, Scalr.OP_ANTIALIAS);
    }
}

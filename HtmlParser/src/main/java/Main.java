import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> images = new ArrayList<>();
        Document document = Jsoup.connect("https://lenta.ru/").get();
        Elements link = document.select("img[src]");
        for (Element img : link)
        {
            String url = img.attr("src");
            if (url.startsWith("https")) images.add(url);
            System.out.println(url.substring(url.lastIndexOf('/') + 1));
        }
        getImages(images);
    }

    public static void getImages(List<String> images) throws IOException {
        String folder = "images\\";
        for (String path : images)
        {
            InputStream in = new URL(path).openStream();
            Files.createDirectories(Paths.get(folder));
            Files.copy(in, Paths.get(folder
                    + new File(path).getName()), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
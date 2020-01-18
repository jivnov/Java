import java.io.*;
import java.net.URL;

class Downloader implements Runnable {
    private final String url;

    Downloader(String url) {
        this.url = url;
    }

    public void run() {
        String[] url_parts = url.split("/");
        String fileName = url_parts[ url_parts.length - 1 ];

        try (InputStream in = new URL(url).openStream();
             FileOutputStream out = new FileOutputStream(fileName)) {
            for (;;) {
                var b = in.read();
                if (b < 0)
                    break;
                out.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DownloadExample.count.getAndIncrement();
        DownloadExample.sem.release();
        System.out.println("Done: "+fileName);
    }
}
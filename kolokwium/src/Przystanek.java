import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Przystanek {
    static List<Przystanek> przystanki = new ArrayList<>();
    static String filepath = "/Users/andreizhyunou/IdeaProjects/Obiektowe/kolokwium/src/przystanki.csv";

    int lp;
    String nazwa;
    String droga;
    String kilom;
    Double lat;
    Double lon;

    public Przystanek(int lp, String nazwa, String droga, String kilom, Double lat, Double lon) {

        this.lp = lp;
        this.nazwa = nazwa;
        this.droga = droga;
        this.kilom = kilom;
        this.lat = lat;
        this.lon = lon;
    }


    static void read() throws IOException {
        CSVReader csvr = new CSVReader(filepath, ";", true);
        while (csvr.next()) {
            przystanki.add(new Przystanek(csvr.getInt(0), csvr.get(1), csvr.get(2), csvr.get(3), csvr.getDouble(4), csvr.getDouble(5)));
        }
    }

    public static void main(String[] args) throws IOException {
        read();
        przystanki.stream().filter(przystanek -> przystanek.nazwa.matches("^P.*")).forEach(przystanek -> System.out.println(przystanek.nazwa));
        przystanki.stream().filter(przystanek -> przystanek.lat > 50.54 && przystanek.lat < 50.62 && przystanek.lon > 21.63 && przystanek.lon < 21.73).forEach(przystanek -> System.out.println(przystanek.nazwa));
        przystanki.stream().filter(przystanek -> przystanek.nazwa.matches("Warszawska.*"))
                .sorted(Comparator.comparingDouble(przystanek -> Double.parseDouble(przystanek.kilom.replace('+', '.')))).forEach(przystanek -> System.out.println(przystanek.nazwa));
        var xmin = przystanki.stream().min(Comparator.comparingDouble(przystanek -> przystanek.lat)).get();
        var xmax = przystanki.stream().max(Comparator.comparingDouble(przystanek -> przystanek.lat)).get();
        var ymin = przystanki.stream().min(Comparator.comparingDouble(przystanek -> przystanek.lon)).get();
        var ymax = przystanki.stream().max(Comparator.comparingDouble(przystanek -> przystanek.lon)).get();
        System.out.printf("%f, %f, %f, %f", xmin.lat, xmax.lat, ymin.lon, ymax.lon);
    }
}
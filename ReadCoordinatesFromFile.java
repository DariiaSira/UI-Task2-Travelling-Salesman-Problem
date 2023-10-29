import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCoordinatesFromFile {
    public static List<City> readCoordinatesFromFile(String filename) {
        List<City> cities = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    cities.add(new City(x, y));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}


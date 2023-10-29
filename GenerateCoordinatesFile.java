import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateCoordinatesFile {
    public static void main(String[] args) {
        String filename = "coordinates.txt"; // Output file name
        int numCities = 20;

        try {
            FileWriter writer = new FileWriter(filename);
            Random random = new Random();

            for (int i = 0; i < numCities; i++) {
                int x = random.nextInt(200);
                int y = random.nextInt(200);
                writer.write(x + " " + y + "\n");
            }

            writer.close();
            System.out.println("Coordinates have been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

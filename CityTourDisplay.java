import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CityTourDisplay extends JPanel {
    private List<City> tour;
    private String algorithmName;

    public CityTourDisplay(List<City> tour, String algorithmName) {
        this.tour = tour;
        this.algorithmName = algorithmName;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color
        setBackground(new Color(255, 255, 204)); // RGB values for light yellow

        // Draw the cities
        for (City city : tour) {
            int x = city.getX();
            int y = city.getY();
            g.setColor(Color.RED);
            g.fillOval(x - 5, y - 5, 10, 10);
        }

        // Draw the tour path
        g.setColor(Color.BLUE);
        for (int i = 0; i < tour.size(); i++) {
            City city1 = tour.get(i);
            City city2 = tour.get((i + 1) % tour.size());
            g.drawLine(city1.getX(), city1.getY(), city2.getX(), city2.getY());
        }

        // Draw the algorithm name at the bottom and centered
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 22)); // Adjust font as needed
        int textWidth = g.getFontMetrics().stringWidth(algorithmName);
        int xCenter = getWidth() / 2 - textWidth / 2;
        int pathY = getHeight() - 10; // Adjust the vertical position for the path text
        g.drawString(algorithmName, xCenter, pathY - 20); // Adjust the vertical position for the algorithm name
    }

    public void updateTour(List<City> newTour) {
        tour = newTour;
        repaint();
    }

    public void setCenterText(String text) {
        algorithmName = text;
    }
}

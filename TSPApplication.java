import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TSPApplication extends JFrame {
    final String filename = "coordinates.txt"; // Input file name
    private final CityTourDisplay displayPanel;

    public TSPApplication() {
        setTitle("TSP Solution");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        setLayout(new BorderLayout());

        JButton tabuSearchButton = new JButton("Tabu Search");
        JButton simulatedAnnealingButton = new JButton("Simulated Annealing");

        tabuSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runTabuSearch();
            }
        });

        simulatedAnnealingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runSimulatedAnnealing();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(tabuSearchButton);
        buttonPanel.add(simulatedAnnealingButton);

        displayPanel = new CityTourDisplay(new ArrayList<>(), "");
        add(buttonPanel, BorderLayout.SOUTH);
        add(displayPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void runTabuSearch() {
        java.util.List<City> cities = ReadCoordinatesFromFile.readCoordinatesFromFile(filename); // Read coordinates from the file

        int tabuListSize = 100; // Adjust the tabu list size as needed
        int maxIterations = 1000; // Adjust the number of iterations as needed

        // Run the Tabu Search algorithm
        List<City> bestSolution = TabuSearchSolution.tabuSearch(cities, tabuListSize, maxIterations);

        // Calculate the best distance using the objective function
        double bestDistance = TabuSearchSolution.calculateTotalDistance(bestSolution);

        // Update the display with the best solution and algorithm name
        displayPanel.updateTour(bestSolution);
        displayPanel.setCenterText("Tabu Search");

        System.out.println("Best Distance: " + bestDistance);
    }

    private void runSimulatedAnnealing() {
        List<City> cities = ReadCoordinatesFromFile.readCoordinatesFromFile(filename); // Read coordinates from the file

        double initialTemperature = 1000; // Adjust the initial temperature
        double coolingRate = 0.00001; // Adjust the cooling rate

        SimulatedAnnealingSolution saSolution = new SimulatedAnnealingSolution(cities, initialTemperature, coolingRate);
        saSolution.findOptimalSolution();

        List<City> bestSolution = saSolution.getCurrentSolution();
        // Calculate the best distance using the objective function
        double bestDistance = saSolution.getCurrentDistance();

        // Update the display with the best solution and algorithm name
        displayPanel.updateTour(bestSolution);
        displayPanel.setCenterText("Simulated Annealing");

        System.out.println("Best Distance: " + bestDistance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TSPApplication::new);
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealingSolution {
    private final List<City> cities;
    private double temperature; // Initial temperature
    private final double coolingRate; // Cooling rate
    private List<City> currentSolution;
    private double currentDistance;

    public SimulatedAnnealingSolution(List<City> cities, double initialTemperature, double coolingRate) {
        this.cities = new ArrayList<>(cities);
        this.temperature = initialTemperature;
        this.coolingRate = coolingRate;
        this.currentSolution = new ArrayList<>(cities);
        this.currentDistance = calculateTotalDistance(currentSolution);
    }

    public List<City> getCurrentSolution() {
        return currentSolution;
    }

    public double getCurrentDistance() {
        return currentDistance;
    }

    public void findOptimalSolution() {
        Random random = new Random();

        while (temperature > 1.0) {
            List<City> neighbor = new ArrayList<>(currentSolution);
            int cityIndex1 = random.nextInt(cities.size());
            int cityIndex2 = random.nextInt(cities.size());

            // Swap the order of two neighboring cities
            swapCities(neighbor, cityIndex1, cityIndex2);

            double neighborDistance = calculateTotalDistance(neighbor);
            double delta = neighborDistance - currentDistance;

            if (delta < 0 || random.nextDouble() < acceptanceProbability(delta, temperature)) {
                currentSolution = neighbor;
                currentDistance = neighborDistance;
            }

            temperature *= 1 - coolingRate;
        }
    }

    private void swapCities(List<City> tour, int index1, int index2) {
        City temp = tour.get(index1);
        tour.set(index1, tour.get(index2));
        tour.set(index2, temp);
    }

    private double acceptanceProbability(double delta, double temperature) {
        if (delta < 0) {
            return 1.0;
        }
        return Math.exp(-delta / temperature);
    }

    private double calculateTotalDistance(List<City> tour) {
        double distance = 0;
        for (int i = 0; i < tour.size(); i++) {
            City city1 = tour.get(i);
            City city2 = tour.get((i + 1) % tour.size());
            distance += city1.distanceTo(city2);
        }
        return distance;
    }
}

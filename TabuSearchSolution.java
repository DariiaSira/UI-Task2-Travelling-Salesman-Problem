import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TabuSearchSolution {
    public static List<City> tabuSearch(List<City> initialSolution, int tabuListSize, int maxIterations) {
        List<City> bestSolution = new ArrayList<>(initialSolution);
        double bestSolutionFitness = calculateTotalDistance(initialSolution);
        double currentSolutionFitness = bestSolutionFitness;
        List<City> currentSolution = new ArrayList<>(initialSolution);
        HashSet<List<City>> tabuList = new HashSet<>();

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            List<List<City>> neighbors = getNeighbors(currentSolution);
            List<City> bestNeighbor = null;
            double bestNeighborFitness = Double.MAX_VALUE;

            for (List<City> neighbor : neighbors) {
                if (!tabuList.contains(neighbor)) {
                    double neighborFitness = calculateTotalDistance(neighbor);
                    if (neighborFitness < bestNeighborFitness) {
                        bestNeighbor = neighbor;
                        bestNeighborFitness = neighborFitness;
                    }
                }
            }

            if (bestNeighbor == null) {
                // No non-tabu neighbors found, terminate the search
                break;
            }

            currentSolution = bestNeighbor;
            tabuList.add(bestNeighbor);

            if (tabuList.size() > tabuListSize) {
                // Remove the oldest entry from the tabu list if it exceeds the size
                tabuList.remove(tabuList.iterator().next());
            }

            if (bestNeighborFitness < bestSolutionFitness) {
                bestSolution = bestNeighbor;
                bestSolutionFitness = bestNeighborFitness;
            }
        }

        return bestSolution;
    }



    // Calculate the total distance directly from the list of cities
    public static double calculateTotalDistance(List<City> solution) {
        double totalDistance = 0;
        int size = solution.size();
        for (int i = 0; i < size; i++) {
            City currentCity = solution.get(i);
            City nextCity = solution.get((i + 1) % size);
            totalDistance += currentCity.distanceTo(nextCity);
        }
        return totalDistance;
    }

    public static List<List<City>> getNeighbors(List<City> solution) {
        List<List<City>> neighbors = new ArrayList<>();
        int size = solution.size();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                List<City> neighbor = new ArrayList<>(solution);
                // Swap elements at indices i and j
                City temp = neighbor.get(i);
                neighbor.set(i, neighbor.get(j));
                neighbor.set(j, temp);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }
}

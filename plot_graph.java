import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.*;
import java.util.*;

public class SortingGraph {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("results.csv"));
        String line;

        Map<String, List<int[]>> map = new HashMap<>();

        // Read CSV
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            String algo = parts[0];
            int size = Integer.parseInt(parts[1]);
            int steps = Integer.parseInt(parts[2]);

            map.putIfAbsent(algo, new ArrayList<>());
            map.get(algo).add(new int[]{size, steps});
        }

        br.close();

        // Create dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String algo : map.keySet()) {
            for (int[] arr : map.get(algo)) {
                dataset.addValue(arr[1], algo, String.valueOf(arr[0]));
            }
        }

        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Sorting Analysis",
                "Input Size",
                "Steps",
                dataset
        );

        // Create folder
        File dir = new File("graphs");
        if (!dir.exists()) dir.mkdir();

        // Save graph
        ChartUtils.saveChartAsPNG(
                new File("graphs/sorting_analysis.png"),
                chart,
                800,
                600
        );

        System.out.println("Graph saved!");
    }
}

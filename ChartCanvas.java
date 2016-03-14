/*
 * Deze ChartCanvas komt van http://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm en is aangepast door Veerle van Winden
 * Dit om te zorgen dat deze goed gebruikt kan worden in de applicatie.
 * Voor deze class is wel een module geinstalleerd van http://www.tutorialspoint.com/jfreechart/jfreechart_installation.htm
 * Hierin wordt door het ingeven van de array met het aantal unieke en overeenkomstige pubmeds die uitgezet worden in een pie chart
 */
package tentamen;


import java.awt.Font;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author Garfield
 */
public class ChartCanvas extends ApplicationFrame {

    private static Integer[] array;
    public ChartCanvas(String name, Integer[] arrLengtes)
    {
        super(name);
        array = arrLengtes;
    }

    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Double total = Double.parseDouble(Integer.toString(array[0]+array[1]+array[2]));
        Double uniek1 = array[0]/total;
        Double uniek2 = array[1]/total;
        Double overlap = array[2]/total;
        dataset.setValue("UniekA", uniek1*100);
        dataset.setValue("UniekB", uniek2*100);
        dataset.setValue("Overlap", overlap*100);

        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
                "PieChart PubmedID's",  // chart title
                dataset,             // data
                true,               // include legend
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 10));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;

    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }


}
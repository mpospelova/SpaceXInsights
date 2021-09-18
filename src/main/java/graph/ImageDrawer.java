package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static utils.Constants.*;

public class ImageDrawer {
    private final double[] values;
    private final String[] labels;
    private final Color[] colors;

    public ImageDrawer(double[] values, String[] labels) {
        this.values = values;
        this.labels = labels;

        this.colors = new Color[values.length];
        Arrays.fill(colors, Color.red);
    }

    public BufferedImage drawImage() {
        System.out.printf("%sStarting to draw image%n", INFO);

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("SpaceX insights");
        String title = "Payload in relation to time (progress)";

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 900);

        BarChart bc = new BarChart(this.values, this.labels, this.colors, title);

        frame.add(bc);
        frame.setVisible(true);

        BufferedImage awtImage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = awtImage.createGraphics();
        frame.paint(graphics2D);

        System.out.printf("%sFinished drawing image%n", INFO);

        return awtImage;
    }
}

package com.spaceXinsights.restservice.graph;
/* Code taken from https://www.javacodex.com/Graphics/Bar-Chart */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BarChart extends JPanel {
    private final double[] values;
    private final String[] labels;
    private final Color[] colors;
    private final String title;

    public BarChart(double[] values, String[] labels, Color[] colors, String title) {
        this.labels = labels;
        this.values = values;
        this.colors = colors;
        this.title = title;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null || values.length == 0) {
            return;
        }

        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;

        for (double value : values) {
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }

        Dimension dim = getSize();
        int panelWidth = dim.width;
        int panelHeight = dim.height;
        int barWidth = panelWidth / values.length;

        Font titleFont = new Font("Arial", Font.BOLD, 15);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);

        Font labelFont = new Font("Arial", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth(title);
        int stringHeight = titleFontMetrics.getAscent();
        int stringWidth = (panelWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, stringWidth, stringHeight);

        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue) {
            return;
        }

        double scale = (panelHeight - top - bottom) / (maxValue - minValue);
        stringHeight = panelHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);

        List<Double> yAxisValues = new ArrayList<>();
        for (int j = 0; j < values.length; j++) {
            int valueP = j * barWidth + 1;
            int valueQ = top;
            int height = (int) (values[j] * scale);

            if (values[j] >= 0) {
                valueQ += (int) ((maxValue - values[j]) * scale);
            } else {
                valueQ += (int) (maxValue * scale);
                height = -height;
            }

            g.setColor(colors[j]);
            g.fillRect(valueP, valueQ, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueP, valueQ, barWidth - 2, height);

            if (j % 5 == 0) {
                int labelWidth = labelFontMetrics.stringWidth(labels[j]);
                stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
                g.drawString(labels[j], stringWidth, stringHeight);
            }

            yAxisValues.add(values[j]);
        }

        yAxisValues.sort(Collections.reverseOrder());
        for (int j = 0; j < yAxisValues.size(); j++) {
            double value = yAxisValues.get(j);
            if (j % 5 == 0) {
                int labelWidth = labelFontMetrics.stringWidth(labels[j]);
                stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
                g.drawString(String.valueOf(value), 0, stringWidth);
            }
        }
    }
}

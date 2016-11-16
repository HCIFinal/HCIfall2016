/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jheatchart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.Timer;
import org.tc33.jheatchart.HeatChart;

/**
 *
 * @author aditya.bhasin
 */
public class JHeatChart extends JApplet{
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    
    private HeatChart heatmap1;
    private HeatChart heatmap2;
    private HeatChart heatmap3;
    
    private int counter;
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        JFrame frame = new JFrame("Heat Map");
//        JHeatChart chart = new JHeatChart();
//        chart.init();
//        chart.start();
//        frame.add(chart);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setPreferredSize(new Dimension(600,600));
//        frame.pack();
//        frame.setVisible(true);
//    }
    
    public void init(){
        super.init();
        
        double[][] zValues = new double[50][50];
        for(int x=0;x<50;x++){
            for(int y=0;y<50;y++){
                zValues[x][y] = Math.sin(y+x+counter/100.0);
            }
        }
        
        heatmap1 = createHeatMap(zValues);
        heatmap2 = createHeatMap(zValues);
        heatmap3 = createHeatMap(zValues);
        
        Timer t = new Timer(1000/30, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                repaint();
            }
        });
        t.start();
    }
    
    public void paint(Graphics g){
        counter+=10;
//        double[][] zValues = new double[300][150];
//        for(int x=70;x<95;x++){
//            for(int y=70;y<95;y++){
//                zValues[x][y] = Math.sin(y+x+counter/100.0);
//            }
//        }
//        heatmap2.setZValues(zValues);
        image1 = toBufferedImage(heatmap2.getChartImage());
        g.drawImage(image1, 0, 0, null);
        
        image2 = toBufferedImage(heatmap2.getChartImage());
        g.drawImage(image2, 150, 0, null);
        
        image3 = toBufferedImage(heatmap2.getChartImage());
        g.drawImage(image3, 300, 0, null);
    }
    
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage)
        { return (BufferedImage) img; }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    
    public static HeatChart createHeatMap(double[][] zValues){
        HeatChart out = new HeatChart(zValues);
        
        out.setCellSize(new Dimension(2,2));
        
        out.setShowXAxisValues(false);
        out.setShowYAxisValues(false);
        out.setAxisThickness(0);
        
        out.setLowValueColour(new Color(0, 255, 0, 0));
        out.setHighValueColour(new Color(255, 0, 0, 100));
        
        return out;
    }
}

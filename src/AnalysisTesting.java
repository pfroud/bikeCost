import javax.swing.*;
import java.awt.*;

/**
 * Used to test Analysis and Histogram drawing before putting them in the PDF.
 */
public class AnalysisTesting extends JPanel {

    private static Analysis a;

    public static void main(String[] args) {
        a = new Analysis(4, -1, -1, -1);
        a.init(Bike.readBikes("bikesInput.txt"));

        // http://php.scripts.psu.edu/djh300/cmpsc221/notes-graphics-intro.php
        AnalysisTesting panel = new AnalysisTesting();
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(panel);
        application.setSize(1800, 500);
        application.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        a.draw(g);
    }


}
import java.awt.*;
import java.util.Vector;

/**
 * Eventually will print histograms and stuff.
 */
class Analysis {

    private int numBins, x, y, histSize, histsPerRow;
    private Vector<Histogram> hists = new Vector<>();

    Analysis(int numBins, int x, int y, int histSize, int histsPerRow) {
        this.numBins = numBins;
        this.x = x;
        this.y = y;
        this.histSize = histSize;
        this.histsPerRow = histsPerRow;
    }


    /**
     * Initialize the vector of histograms.
     * This must be separate from the constructor and called by a Diagram instance.
     *
     * @param bikes vector of all bikes
     */
    void init(Vector<Bike> bikes) {
        Histogram h;

        final int NUM_BIKES = bikes.size();
        final int SPACING   = 50;

        // NUM_BIKES might not be a multiple of histsPerRow. We need to find out how many complete rows to draw:
        int numRowsToDraw  = NUM_BIKES / histsPerRow; //integer division important here
        int numHistsToDraw = numRowsToDraw * histsPerRow; // guaranteed to be a multiple of histsPerRow

        // draw complete rows
        Bike bike;
        int  rowNumber;
        for (int start = 0, end = histsPerRow - 1; end <= numHistsToDraw; start = end + 1, end += histsPerRow) {
            rowNumber = start / histsPerRow;
            for (int i = start; i <= end; i++) {
                bike = bikes.get(i);
                h = new Histogram(bike.getHistogramData(numBins), bike.modelName);
                h.init(x + (histSize + SPACING) * (i % histsPerRow),
                        y + rowNumber * (histSize + SPACING),
                        histSize);
                hists.add(h);
            }
        }

        // draw a partial row if needed
        if (numHistsToDraw != NUM_BIKES) {

            rowNumber = numHistsToDraw / histsPerRow;

            for (int i = numHistsToDraw; i < NUM_BIKES; i++) {
                bike = bikes.get(i);
                h = new Histogram(bike.getHistogramData(numBins), bike.modelName);
                h.init(x + (histSize + SPACING) * (i % histsPerRow),
                        y + rowNumber * (histSize + SPACING),
                        histSize);
                hists.add(h);
            }
        }

    }

    void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 9999, 9999);

        for (Histogram hist : hists) hist.draw(g);
    }

}

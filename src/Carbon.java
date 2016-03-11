import java.awt.*;

/**
 * Describes the material of a bike by how much of it is carbon fiber.
 */
public enum Carbon {
    ALL, FORK, NONE;

    // I didn't know you could do this. http://stackoverflow.com/a/18883717
    private Color color;
    private String description;

    static {
        ALL.color = Color.cyan;
        FORK.color = Color.yellow;
        NONE.color = Color.red;

        ALL.description = "full carbon-fiber frame";
        FORK.description = "carbon-fiber fork only";
        NONE.description = "no carbon-fiber";
    }

    public String getDescription() {
        return this.description;
    }

    public void draw(Graphics2D g, int dotX, int dotY, int size, boolean inLegend) {
        g.setColor(this.color);
        g.fillOval(dotX, dotY, size, size);
        if (inLegend) {
            g.setColor(Color.black);
            g.drawOval(dotX, dotY, size, size);
        }
    }

    /**
     * Parses a string into a Carbon enum.
     *
     * @param s string to parse into Carbon
     * @return Carbon parsed from string
     */
    public static Carbon parseString(String s) {
        switch (s) {
            case "all":
                return Carbon.ALL;
            case "fork":
                return Carbon.FORK;
            case "none":
                return Carbon.NONE;
            default:
                System.err.println("unrecognized carbon value \"" + s + "\"");
        }
        return null;
    }

}

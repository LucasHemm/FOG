package dat.backend.model.services;

public class SVG {


    private int x;
    private int y;
    private int height;
    private int width;
    private String viewbox;
    private StringBuilder svgString = new StringBuilder();


    private final static String HEADERTEMPLATE =
            "<svg x=\"%d\" y=\"%d\" height=\"%d%%\" width=\"%d%%\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";

    private final static String RECTTEMPLATE =
            "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\"\n style=\"stroke:#000000; fill:#ffffff\" stroke-width=\"2\"/>";
    private final static String RECTSHEDTEMPLATE =
            "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\"\n style=\"stroke:#000000; fill:none\" stroke-width=\"4\"/>";

    private final static String LINETEMPLATE =
            "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"black\" style =\"stroke-width:2;\" stroke-dasharray=\"9\"/>";
    private final static String LINEWITHARROWSTEMPLATE =
            "<line x1=\"%d\"  y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; marker-start:url(#beginArrow); marker-end:url(#endArrow);\"/>";
    private final static String TEXTTEMPPLATE =
            "<text style=\"text-anchor: middle\" transform=\"translate(%d,%d) rotate(%d)\">%s</text>";




    private final static String ARROWHEADSTEMPLATE = "<defs>\n" +
            "        <marker\n" +
            "                id=\"beginArrow\"\n" +
            "                markerWidth=\"12\"\n" +
            "                markerHeight=\"12\"\n" +
            "                refX=\"0\"\n" +
            "                refY=\"6\"\n" +
            "                orient=\"auto\">\n" +
            "            <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
            "        </marker>\n" +
            "        <marker\n" +
            "                id=\"endArrow\"\n" +
            "                markerWidth=\"12\"\n" +
            "                markerHeight=\"12\"\n" +
            "                refX=\"12\"\n" +
            "                refY=\"6\"\n" +
            "                orient=\"auto\">\n" +
            "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
            "        </marker>\n" +
            "    </defs>";





    public SVG(int x, int y, int height, int width, String viewbox) {
        svgString.append(String.format(HEADERTEMPLATE,x, y, height, width, viewbox));
        svgString.append(ARROWHEADSTEMPLATE);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.viewbox = viewbox;
    }

    public void addRect(int x, int y, double height, double width){

        svgString.append(String.format(RECTTEMPLATE,x,y,height,width));
    }
    public void addShedRect(int x, int y, double height, double width){

        svgString.append(String.format(RECTSHEDTEMPLATE,x,y,height,width));
    }


    public void addLine(int x1, int y1, int x2, int y2)
    {
        svgString.append(String.format(LINETEMPLATE, x1, y1, x2, y2));
    }

    public void addArrows(int x1, int y1, int x2, int y2){
        svgString.append(String.format(LINEWITHARROWSTEMPLATE,x1,y1,x2,y2));
    }



    public void addText(int x, int y, int rotation, String text){
        svgString.append(String.format(TEXTTEMPPLATE,x,y,rotation,text));
    }



    @Override
    public String toString() {
        return svgString + "</svg>";
    }



}

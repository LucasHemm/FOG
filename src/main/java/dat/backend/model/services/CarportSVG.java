package dat.backend.model.services;

public class CarportSVG {


    public static SVG createNewSVG(int x, int y, int height, int width, String viewbox) {
        return new SVG(x, y, height, width, viewbox);
    }


    public static SVG addRafters(SVG svg, int length, int width) {
        int space = (int) Math.round(Calculator.spaceBetweenRafters(length));
        for (int i = 0; i < Calculator.amountOfRafters(length) - 1; i++) {
            svg.addRect(40 + i * space, 0, width, 4.5);
        }
        svg.addRect(length + 40, 0, width, 4.5);

        return svg;
    }


    public static SVG addBeams(SVG svg, int length, int width) {

        for (int i = 35; i < width; i += width - 70) {
            svg.addRect(40, i, 4.5, length);
        }
        return svg;
    }

    public static SVG addPosts(SVG svg, int length, int width, boolean hasShed, int shedlength, int shedwidth) {
        double var = Calculator.amountOfPosts(length, hasShed, width,shedwidth);
        System.out.println(var);
        if(hasShed){
            var -= 4;
            if(shedwidth == width-70){
                var--;
            }
        }
        var = Math.round(var/2);
        var--;

        int stop = 1;

        if(width == 600 && length<=330){
            stop--;
        }

        for (int i = 0; i < var; i++) {

            svg.addRect((int) (0.14 * length + 40) + i * 300, 32, 10, 10);
            if(i == stop){
                break;
            }
        }
        svg.addRect((int) (length - length * 0.03) + 40, 32, 10, 10);


        for (int i = 0; i < var; i++) {

            svg.addRect((int) (0.14 * length + 40) + i * 300, width - 37, 10, 10);
            if(i == stop){
                break;
            }
        }

        svg.addRect((int) (length - length * 0.03) + 40, width - 38, 10, 10);

        if (hasShed) {
            svg.addRect(length + 37 - shedlength, width - 38, 10, 10);
            svg.addRect(length + 37 - shedlength, width / 2 - 3, 10, 10);
            svg.addRect((int) (length + 40 - length * 0.03), width / 2 - 3, 10, 10);
            if (shedwidth == width - 70) {
                svg.addRect(length + 37 - shedlength, 32, 10, 10);
            } else {
            }

        }
        return svg;
    }

    public static SVG addShedRect(SVG svg, int length, int width, boolean hasShed, int shedlength, int shedwidth){
        if (hasShed) {
            if (shedwidth == width - 70) {
                svg.addShedRect(length + 37 - shedlength, 32, shedwidth+10, shedlength-7);
            } else {
                svg.addShedRect(length + 37 - shedlength, width / 2 - 3, shedwidth+10, shedlength-7);
            }
        }
        return svg;
    }

    public static SVG addHollowBand(SVG svg, int length, int width) {
        svg.addLine((int) (length * 0.07 + 40), 35, (int) (length - length * 0.28), width - 30);
        svg.addLine((int) (length * 0.07 + 40), width - 30, (int) (length - length * 0.28), 35);
        return svg;
    }

    public static SVG addArrows(SVG svg, int length, int width) {
        svg.addArrows(15, 0, 15, width);
        svg.addArrows(33, 35, 33, width - 35);
        svg.addArrows(40, width + 15, length + 42, width + 15);
        svg.addText(12, width / 2, -90, String.valueOf(width) + " cm");
        svg.addText(30, width / 2, -90, String.valueOf(width - 70) + " cm");
        svg.addText(length / 2 + 40, width + 30, 0, (length + " cm"));
        return svg;
    }


}

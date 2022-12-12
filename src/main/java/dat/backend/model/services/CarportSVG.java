package dat.backend.model.services;

public class CarportSVG {


    public static SVG createNewSVG(int x, int y, int height, int width, String viewbox){
        return new SVG(x,y,height,width,viewbox);
    }


    public static SVG addRafters(SVG svg, int length, int width){
        int space = (int) Math.round(Calculator.spaceBetweenRafters(length));
        for(int i = 0; i < Calculator.amountOfRafters(length)-1;i ++ ) {
            svg.addRect(40+i*space, 0, width, 4.5);
        }
        svg.addRect(length+40, 0, width, 4.5);

        return svg;
    }


    public static SVG addBeams(SVG svg, int length, int width){

        for(int i = 35; i < width;i += width-70 ) {
            svg.addRect(40, i, 4.5, length);
        }
        return svg;
    }

    public static SVG addPosts(SVG svg, int length, int width){
        for(int i = 0; i < Calculator.amountOfPosts(length)/2-1;i++) {
            svg.addRect( (int) (0.14*length+40)+i*300, 32, 10, 10);
        }
          svg.addRect((int) (length-length*0.03)+40,32,10,10);


        for(int i = 0; i < Calculator.amountOfPosts(length)/2-1 ;i++) {

            svg.addRect((int) (0.14*length+40)+i*300, width-37, 10, 10);
        }

        svg.addRect((int) (length-length*0.03)+40,width-37,10,10);

        return svg;
    }

    public static SVG addHollowBand(SVG svg, int length, int width){
            svg.addLine((int) (length*0.07+40), 35, (int) (length-length*0.28), width-30);
            svg.addLine((int) (length*0.07+40), width-30, (int) (length-length*0.28), 35);
            return svg;
    }

    public static SVG addArrows(SVG svg,int length, int width){
        svg.addArrows(15,0,15,width);
        svg.addArrows(30,35,30,width-35);
        svg.addArrows(40,width+15,length+40,width+15);

        return svg;
    }





}

package dat.backend.model.services;

import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.PartFacade;

import java.util.ArrayList;

public class Calculator {

    //Code for posts
    //1 is hardcoded in methods since we don't choose between different posts on our site

    public static int amountOfPosts(int length) {
        int amount = 4;

        if (2 * ((length - length * 0.165) / 310 + 1) > 4) {

            amount = (int) (2 * ((length - length * 0.165) / 310 + 1));
            if (amount % 2 != 0) {
                amount++;
            }

        }
        return amount;
    }

    public static int postid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(1, connectionPool);
    }

    private static double postPrice(int length, ConnectionPool connectionPool) {

        int amount = amountOfPosts(length);
        int partid = postid(connectionPool);


        //300 since the post is always 3 meters
        double price = PartFacade.pricePrMeter(300, partid, connectionPool) * amount;

        return price;
    }

    private static double postCostPrice(int length, ConnectionPool connectionPool) {
        int amount = amountOfPosts(length);
        int partid = postid(connectionPool);

        //300 since the post is always 3 meters
        double price = PartFacade.costPricePrMeter(300, partid, connectionPool) * amount;

        return price;
    }


    //Code for rafters

    //2 is hardcoded since we don't choose between different rafter on our site
    public static int rafterVariantID(int width, ConnectionPool connectionPool) {

        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(2, connectionPool);

        int variantid = 0;

        for (int i = 0; i < lengthList.size(); i++) {
            if (lengthList.get(i) >= width) {
                variantid = PartFacade.variantidFromLengthAndPartid(lengthList.get(i), 2, connectionPool);
                break;
            }
        }
        return variantid;
    }

    public static int amountOfRafters(double lenght) {
        double num = lenght / 55 + 1;
        num = Math.round(num);
        int amount = (int) num;
        return amount;
    }


    public static double rafterPrice(int length, int width, ConnectionPool connectionPool) {

        int amount = amountOfRafters(length);

        double price = PartFacade.pricePrMeter(width, 2, connectionPool) * amount;

        return price;
    }

    private static double rafterCostPrice(int length, int width, ConnectionPool connectionPool) {
        int amount = amountOfRafters(length);

        double price = PartFacade.costPricePrMeter(width, 2, connectionPool) * amount;

        return price;
    }


    //Code for beams

    //3 is hardcoded in methods since we don't choose between different beams on our site
    public static int[] beamVariantsid(int length, ConnectionPool connectionPool) {
        int[] idArray = new int[2];


        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(3, connectionPool);

        if (length <= 660) {
            for (int i = 0; i < lengthList.size(); i++) {
                if (lengthList.get(i) >= length) {
                    idArray[0] = PartFacade.variantidFromLengthAndPartid(lengthList.get(i), 3, connectionPool);
                    break;
                }
            }
        } else {
            idArray = getVariantsidsFromLength(length, 3, connectionPool);
        }

        return idArray;
    }


    public static double beamPrice(int lenght, ConnectionPool connectionPool) {
        double price = 0;

        int partid1 = beamVariantsid(lenght, connectionPool)[0];
        int partid2 = beamVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);

        price += PartFacade.pricePrMeter(length1, 3, connectionPool) * 2;
        System.out.println(price);
        price += PartFacade.pricePrMeter(length2, 3, connectionPool) * 2;
        System.out.println(price);

        return price;
    }

    private static double beamCostPrice(int lenght, ConnectionPool connectionPool) {
        double price = 0;

        int partid1 = beamVariantsid(lenght, connectionPool)[0];
        int partid2 = beamVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);

        price += PartFacade.costPricePrMeter(length1, 3, connectionPool) * 2;
        price += PartFacade.costPricePrMeter(length2, 3, connectionPool) * 2;

        return price;
    }


    //Code for screws
    //4 is hardcoded in methods since we don't choose between different screws on our site
    //Amount is hardcoded since it will always stay the same
    private static int screwAmount = 2;

    public static int screwid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(4, connectionPool);
    }

    private static double screwPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(4, connectionPool) * screwAmount;
    }

    private static double screwCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(4, connectionPool) * screwAmount;
    }


    //Code for roof screws
    //5 is hardcoded in methods since we don't choose between different roof screws on our site
    //Amount is hardcoded since it will always stay the same
    private static int roofScrewAmount = 2;

    public static int roofScrewid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(5, connectionPool);
    }

    private static double roofScrewPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(5, connectionPool) * roofScrewAmount;
    }

    private static double roofScrewCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(5, connectionPool) * roofScrewAmount;
    }

    //Code for roof panels
    //6 is hardcoded in methods since we don't choose between different roof panels on our site
    public static int amountOfRoofPanels(int width) {
        return Math.round(width / 100);
    }

    public static int[] roofPanelsVariantsid(int length, ConnectionPool connectionPool) {
        int[] idArray = {0, 0};


        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(6, connectionPool);

        if (length <= 600) {
            for (int i = 0; i < lengthList.size(); i++) {
                if (lengthList.get(i) >= length) {
                    idArray[0] = PartFacade.variantidFromLengthAndPartid(lengthList.get(i), 6, connectionPool);
                    break;
                }
            }
        } else {
            idArray = getVariantsidsFromLength(length, 6, connectionPool);
        }

        return idArray;
    }

    public static double roofPanelPrice(int lenght, int width, ConnectionPool connectionPool) {

        int partid1 = roofPanelsVariantsid(lenght, connectionPool)[0];
        int partid2 = roofPanelsVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);
        int amount = amountOfRoofPanels(width);

        double price = PartFacade.pricePrMeter(length1, 6, connectionPool) * amount;
        price += PartFacade.pricePrMeter(length2, 6, connectionPool) * amount;

        return price;
    }

    private static double roofPanelCostPrice(int lenght, int width, ConnectionPool connectionPool) {
        double price = 0;

        int partid1 = roofPanelsVariantsid(lenght, connectionPool)[0];
        int partid2 = roofPanelsVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);
        int amount = amountOfRoofPanels(width);

        price += PartFacade.costPricePrMeter(length1, 6, connectionPool) * amount;
        price += PartFacade.costPricePrMeter(length2, 6, connectionPool) * amount;

        return price;
    }


    //Code for bolts
    //7 is hardcoded in methods since we don't choose between different bolts on our site

    public static int boltid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(7, connectionPool);
    }

    public static int amountOfBolts(int length, ConnectionPool connectionPool) {
        int amount = amountOfPosts(length);

        int[] arr = beamVariantsid(length, connectionPool);

        if (arr[1] != 0) {
            amount += 6;
        }

        return amount;
    }


    private static double boltPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(7, connectionPool) * amountOfBolts(length, connectionPool);
    }

    private static double boltCostPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(7, connectionPool) * amountOfBolts(length, connectionPool);
    }


    //Code for square discs
    //8 is hardcoded in methods since we don't choose between different square discs on our site
    public static int discid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(8, connectionPool);
    }

    public static int amountOfDiscs(int length) {
        return amountOfPosts(length) + 1;
    }

    private static double discPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(8, connectionPool) * amountOfDiscs(length);
    }

    private static double discCostPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(8, connectionPool) * amountOfDiscs(length);
    }


    //Calculations for totalprices


    public static double totalPriceBeforeTax(int length, int width, ConnectionPool connectionPool) {
        double price = 0;
        price += postPrice(length, connectionPool);
        price += rafterPrice(length, width, connectionPool);
        price += beamPrice(length, connectionPool);
        price += screwPrice(connectionPool);
        price += roofScrewPrice(connectionPool);
        price += roofPanelPrice(length, width, connectionPool);
        price += boltPrice(length, connectionPool);
        price += discPrice(length, connectionPool);
        return price;
    }

    public static double totalPrice(int length, int width, ConnectionPool connectionPool) {
        return totalPriceBeforeTax(length, width, connectionPool) * 1.25;
    }

    public static double totalCostPrice(int length, int width, ConnectionPool connectionPool) {
        double price = 0;
        price += postCostPrice(length, connectionPool);
        price += rafterCostPrice(length, width, connectionPool);
        price += beamCostPrice(length, connectionPool);
        price += screwCostPrice(connectionPool);
        price += roofScrewCostPrice(connectionPool);
        price += roofPanelCostPrice(length, width, connectionPool);
        price += boltCostPrice(length, connectionPool);
        price += discCostPrice(length, connectionPool);
        return price;
    }


    public static double percentageGainBeforeTax(int length, int width, ConnectionPool connectionPool) {
        return totalPriceBeforeTax(length, width, connectionPool) / totalCostPrice(length, width, connectionPool) * 100;
    }

    public static double percentageGainAfterTax(int length, int width, ConnectionPool connectionPool) {
        return totalPrice(length, width, connectionPool) / totalCostPrice(length, width, connectionPool) * 100;
    }

    public static double percentageGainAfterChangedPriceBeforeTax(int newPrice, int length, int width, ConnectionPool connectionPool) {
        return newPrice * 0.8 / totalCostPrice(length, width, connectionPool) * 100;
    }

    public static double percentageGainAfterChangedPriceAfterTax(int newPrice, int length, int width, ConnectionPool connectionPool) {
        return newPrice / totalCostPrice(length, width, connectionPool) * 100;
    }


    //Lists of amount and/or IDs of each part

    public static ArrayList<Integer> listOfPartAmounts(int length, int width, ConnectionPool connectionPool) {
        ArrayList<Integer> listOfPartAmounts = new ArrayList<>();

        //amount of posts
        listOfPartAmounts.add(amountOfPosts(length));
        //Amount of rafters
        listOfPartAmounts.add(amountOfRafters(length));
        //There will always be 2 beams for beamid1
        listOfPartAmounts.add(2);
        //amount for beamid2 if the carport has a length larger than 6.6 meters
        if (beamVariantsid(length, connectionPool)[1] != 0) {
            listOfPartAmounts.add(2);
        }
        //amount for beamid2 if the carport has a length less than 6.6 meters
//        else {
//            listOfPartAmounts.add(0);
//        }
        //Amount of screws
        listOfPartAmounts.add(screwAmount);
        //Amount of roofscrews
        listOfPartAmounts.add(roofScrewAmount);
        //amount for roofid1
        listOfPartAmounts.add(amountOfRoofPanels(width));
        //amount for roofid2 if the carport has a length larger than 6 meters
        if (roofPanelsVariantsid(length, connectionPool)[1] != 0) {
            listOfPartAmounts.add(amountOfRoofPanels(width));
        }
        //amount for roofid2 if the carport has a length less than 6 meters
//        else {
//            listOfPartAmounts.add(0);
//        }
        //Amount of bolts
        listOfPartAmounts.add(amountOfBolts(length, connectionPool));
        listOfPartAmounts.add(amountOfDiscs(length));

        return listOfPartAmounts;
    }

    public static ArrayList<Integer> listOfIDs(int length, int width, ConnectionPool connectionPool) {
        ArrayList<Integer> listOfIDs = new ArrayList<>();
        listOfIDs.add(postid(connectionPool));
        listOfIDs.add(rafterVariantID(width, connectionPool));
        listOfIDs.add(beamVariantsid(length, connectionPool)[0]);
        if (beamVariantsid(length, connectionPool)[1] != 0) {
            listOfIDs.add(beamVariantsid(length, connectionPool)[1]);
        }
        //ID for beamid2 if the carport has a length less than 6.6 meters
        else {
            listOfIDs.add(0);
        }
        listOfIDs.add(screwid(connectionPool));
        listOfIDs.add(roofScrewid(connectionPool));
        listOfIDs.add(roofPanelsVariantsid(length, connectionPool)[0]);
        if (roofPanelsVariantsid(length, connectionPool)[1] != 0) {
            listOfIDs.add(roofPanelsVariantsid(length, connectionPool)[1]);
        }
        //ID for roofid2 if the carport has a length less than 6 meters
        else {
            listOfIDs.add(0);
        }
        listOfIDs.add(boltid(connectionPool));
        listOfIDs.add(discid(connectionPool));

        return listOfIDs;
    }


    //Helper methods
    //Used for both beams and roof panels when the length of one is not enough
    private static int[] getVariantsidsFromLength(int length, int partid, ConnectionPool connectionPool) {
        int[] idArray = new int[2];

        switch (length) {
            case 630:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(330, partid, connectionPool);
                break;
            case 660:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(360, partid, connectionPool);
                break;
            case 690:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(390, partid, connectionPool);
                break;

            case 720:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(420, partid, connectionPool);
                break;

            case 750:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(450, partid, connectionPool);
                break;

            default:
                idArray[0] = PartFacade.variantidFromLengthAndPartid(300, partid, connectionPool);
                idArray[1] = PartFacade.variantidFromLengthAndPartid(480, partid, connectionPool);
                break;
        }

        return idArray;
    }
}
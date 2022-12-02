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

    public int postid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(1, connectionPool);
    }

    private int postPrice(int length, ConnectionPool connectionPool) {

        int amount = amountOfPosts(length);
        int partid = postid(connectionPool);

        int price = PartFacade.pricePrMeter(length, partid, connectionPool) * amount;

        return price;
    }

    private int postCostPrice(int length, ConnectionPool connectionPool) {
        int amount = amountOfPosts(length);
        int partid = postid(connectionPool);

        int price = PartFacade.costPricePrMeter(length, partid, connectionPool) * amount;

        return price;
    }


    //Code for rafters

    //2 is hardcoded since we don't choose between different rafter on our site
    public static int rafterVariantID(int width, ConnectionPool connectionPool) {

        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(2, connectionPool);

        int variantid = 0;

        for (int i : lengthList) {
            if (lengthList.get(i) >= width) {
                variantid = lengthList.get(i);
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


    private int rafterPrice(int length, int width, ConnectionPool connectionPool) {

        int amount = amountOfRafters(length);
        int partid = rafterVariantID(width, connectionPool);

        int price = PartFacade.pricePrMeter(length, partid, connectionPool) * amount;

        return price;
    }

    private int rafterCostPrice(int length, int width, ConnectionPool connectionPool) {
        int amount = amountOfRafters(length);
        int partid = rafterVariantID(width, connectionPool);

        int price = PartFacade.costPricePrMeter(length, partid, connectionPool) * amount;

        return price;
    }


    //Code for beams

    //3 is hardcoded in methods since we don't choose between different beams on our site
    public static int[] beamVariantsid(int length, ConnectionPool connectionPool) {
        int[] idArray = new int[2];


        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(3, connectionPool);

        if (length <= 660) {
            for (int i : lengthList) {
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


    private static int beamPrice(int lenght, ConnectionPool connectionPool) {
        int price = 0;

        int partid1 = beamVariantsid(lenght, connectionPool)[0];
        int partid2 = beamVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);

        price += PartFacade.pricePrMeter(length1, partid1, connectionPool);
        price += PartFacade.pricePrMeter(length2, partid2, connectionPool);

        return price;
    }

    private static int beamCostPrice(int lenght, ConnectionPool connectionPool) {
        int price = 0;

        int partid1 = beamVariantsid(lenght, connectionPool)[0];
        int partid2 = beamVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);

        price += PartFacade.costPricePrMeter(length1, partid1, connectionPool);
        price += PartFacade.costPricePrMeter(length2, partid2, connectionPool);

        return price;
    }


    //Code for screws
    //4 is hardcoded in methods since we don't choose between different screws on our site
    //Amount is hardcoded since it will always stay the same
    private int screwAmount = 2;

    public int screwid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(4, connectionPool);
    }

    private int screwPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(screwid(connectionPool), connectionPool) * screwAmount;
    }

    private int screwCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(screwid(connectionPool), connectionPool) * screwAmount;
    }


    //Code for roof screws
    //5 is hardcoded in methods since we don't choose between different roof screws on our site
    //Amount is hardcoded since it will always stay the same
    private int roofScrewAmount = 2;

    public int roofScrewid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(5, connectionPool);
    }

    private int roofScrewPrice(ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(roofScrewid(connectionPool), connectionPool) * roofScrewAmount;
    }

    private int roofScrewCostPrice(ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(roofScrewid(connectionPool), connectionPool) * roofScrewAmount;
    }

    //Code for roof panels
    //6 is hardcoded in methods since we don't choose between different roof panels on our site
    public int amountOfRoofPanels(int width) {
        return Math.round(width / 100);
    }

    public static int[] roofPanelsVariantsid(int length, ConnectionPool connectionPool) {
        int[] idArray = {0, 0};


        ArrayList<Integer> lengthList = PartFacade.partsLengthFromPartid(6, connectionPool);

        if (length <= 600) {
            for (int i : lengthList) {
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

    private static int roofPanelPrice(int lenght, ConnectionPool connectionPool) {
        int price = 0;

        int partid1 = roofPanelsVariantsid(lenght, connectionPool)[0];
        int partid2 = roofPanelsVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);

        price += PartFacade.pricePrMeter(length1, partid1, connectionPool);
        price += PartFacade.pricePrMeter(length2, partid2, connectionPool);

        return price;
    }

    private static int roofPanelCostPrice(int lenght, ConnectionPool connectionPool) {
        int price = 0;

        int partid1 = roofPanelsVariantsid(lenght, connectionPool)[0];
        int partid2 = roofPanelsVariantsid(lenght, connectionPool)[1];
        int length1 = PartFacade.getLengthFromVariantid(partid1, connectionPool);
        int length2 = PartFacade.getLengthFromVariantid(partid2, connectionPool);

        price += PartFacade.costPricePrMeter(length1, partid1, connectionPool);
        price += PartFacade.costPricePrMeter(length2, partid2, connectionPool);

        return price;
    }


    //Code for bolts
    //7 is hardcoded in methods since we don't choose between different bolts on our site

    public int boltid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(7, connectionPool);
    }

    public int amountOfBolts(int length, ConnectionPool connectionPool) {
        int amount = amountOfPosts(length);

        int[] arr = beamVariantsid(length, connectionPool);

        if (arr[1] != 0) {
            amount += 6;
        }

        return amount;
    }


    private int boltPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(boltid(connectionPool), connectionPool) * amountOfBolts(length, connectionPool);
    }

    private int boltCostPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(boltid(connectionPool), connectionPool) * amountOfBolts(length, connectionPool);
    }


    //Code for square discs
    //8 is hardcoded in methods since we don't choose between different square discs on our site
    public int discid(ConnectionPool connectionPool) {
        return PartFacade.variantidFromPartid(8, connectionPool);
    }

    public int amountOfDiscs(int length) {
        return amountOfPosts(length) + 1;
    }

    private int discPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.PricePrAmount(discid(connectionPool), connectionPool) * amountOfDiscs(length);
    }

    private int discCostPrice(int length, ConnectionPool connectionPool) {
        return PartFacade.costPricePrAmount(discid(connectionPool), connectionPool) * amountOfDiscs(length);
    }


    //Calculations for totalprices


    public int totalPriceBeforeTax(int length, int width, ConnectionPool connectionPool) {
        int price = 0;
        price += postPrice(length, connectionPool);
        price += rafterPrice(length, width, connectionPool);
        price += beamPrice(length, connectionPool);
        price += screwPrice(connectionPool);
        price += roofScrewPrice(connectionPool);
        price += roofPanelPrice(length, connectionPool);
        price += boltPrice(length, connectionPool);
        price += discPrice(length, connectionPool);
        return price;
    }

    public double totalPrice(int length, int width, ConnectionPool connectionPool) {
        return totalPriceBeforeTax(length, width, connectionPool) * 1.25;
    }

    public int totalCostPrice(int length, int width, ConnectionPool connectionPool) {
        int price = 0;
        price += postCostPrice(length, connectionPool);
        price += rafterCostPrice(length, width, connectionPool);
        price += beamCostPrice(length, connectionPool);
        price += screwCostPrice(connectionPool);
        price += roofScrewCostPrice(connectionPool);
        price += roofPanelCostPrice(length, connectionPool);
        price += boltCostPrice(length, connectionPool);
        price += discCostPrice(length, connectionPool);
        return price;
    }


    public double percentageGainBeforeTax(int length, int width, ConnectionPool connectionPool) {
        return totalPriceBeforeTax(length, width, connectionPool) / totalCostPrice(length, width, connectionPool) * 100;
    }

    public double percentageGainAfterTax(int length, int width, ConnectionPool connectionPool) {
        return totalPrice(length, width, connectionPool) / totalCostPrice(length, width, connectionPool) * 100;
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
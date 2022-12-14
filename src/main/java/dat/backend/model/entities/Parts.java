package dat.backend.model.entities;

public class Parts {

    String part_Description;
    int pricePrUnit;
    int costPricePrUnit;
    String part_Usage;
    String part_Type;
    String unit;
    String priceUnit;
    int partLength;


    public Parts(String part_Description, int pricePrUnit, int costPricePrUnit, String part_Usage, String part_Type, String unit,String priceUnit, int partLength) {
        this.part_Description = part_Description;
        this.pricePrUnit = pricePrUnit;
        this.costPricePrUnit = costPricePrUnit;
        this.part_Usage = part_Usage;
        this.part_Type = part_Type;
        this.unit = unit;
        this.priceUnit = priceUnit;
        this.partLength = partLength;
    }
    public Parts(String part_Description, int pricePrUnit, int costPricePrUnit, String part_Usage, String part_Type, String unit,String priceUnit) {
        this.part_Description = part_Description;
        this.pricePrUnit = pricePrUnit;
        this.costPricePrUnit = costPricePrUnit;
        this.part_Usage = part_Usage;
        this.part_Type = part_Type;
        this.unit = unit;
        this.priceUnit = priceUnit;
    }

    public String getPart_Description() {
        return part_Description;
    }

    public void setPart_Description(String part_Description) {
        this.part_Description = part_Description;
    }

    public String getPart_Usage() {
        return part_Usage;
    }

    public void setPart_Usage(String part_Usage) {
        this.part_Usage = part_Usage;
    }

    public String getPart_Type() {
        return part_Type;
    }

    public void setPart_Type(String part_Type) {
        this.part_Type = part_Type;
    }

    public int getPricePrUnit() {
        return pricePrUnit;
    }

    public void setPricePrUnit(int pricePrUnit) {
        this.pricePrUnit = pricePrUnit;
    }

    public int getCostPricePrUnit() {
        return costPricePrUnit;
    }

    public void setCostPricePrUnit(int costPricePrUnit) {
        this.costPricePrUnit = costPricePrUnit;
    }


    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPartLength() {
        return partLength;
    }

    public void setPartLength(int partLength) {
        this.partLength = partLength;
    }
}

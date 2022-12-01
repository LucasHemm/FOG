package dat.backend.model.entities;

public class Parts {

    String description;
    int pricePrUnit;
    int costPricePrUnit;
    String usage;
    String type;
    String unit;
    String partLength;

    public Parts(String description, int pricePrUnit, int costPricePrUnit, String usage) {
        this.description = description;
        this.pricePrUnit = pricePrUnit;
        this.costPricePrUnit = costPricePrUnit;
        this.usage = usage;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPartLength() {
        return partLength;
    }

    public void setPartLength(String partLength) {
        this.partLength = partLength;
    }
}

package abby.crystallised.items.jewelry;

public enum MetalBase {
    IRON,
    GOLD,
    COPPER,
    NETHERITE;


    public String getItemSuffix() {
        return "_" + this.name().toLowerCase();
    }

    public String getLowercaseName() {
        return this.name().toLowerCase();
    }
}

package abby.crystallised.items.jewelry;

public enum MetalBase {
    IRON,
    GOLD,
    COPPER,
    NETHERITE;


    public String getItemName() {
        return "_" + this.name().toLowerCase();
    }
}

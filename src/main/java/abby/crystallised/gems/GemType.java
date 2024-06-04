package abby.crystallised.gems;

import abby.crystallised.gems.implementation.*;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class GemType implements Comparable<GemType> {
    public static final Map<String, GemType> MAP = new LinkedHashMap<>();

    public static final GemType AMBER = new GemType("amber", "Amber",
            2.2F,
            5,
            new Amber()).register();
    public static final GemType AZURITE = new GemType("azurite", "Azurite",
            3.5F,
            13,
            new Azurite()).register();
    public static final GemType BULLSEYE = new GemType("bullseye", "Bull's Eye",
            7.5F,
            8,
            new BullsEye()).register();
    public static final GemType FLUORITE = new GemType("fluorite", "Fluorite",
            4F,
            13,
            new Fluorite()).register();
    public static final GemType KUNZITE = new GemType("kunzite", "Kunzite",
            7F,
            18,
            new Kunzite()).register();
    public static final GemType MOISSANITE = new GemType("moissanite", "Moissanite",
            9.5F,
            20,
            new Moissanite()).register();
    public static final GemType MOONSTONE = new GemType("moonstone", "Moonstone",
            6.5F,
            30,
            new Moonstone()).register();
    public static final GemType ONYX = new GemType("onyx", "Onyx",
            7F,
            10,
            new Onyx()).register();
    public static final GemType PERIDOT = new GemType("peridot", "Peridot",
            6.5F,
            13,
            new Peridot()).register();
    public static final GemType PETALITE = new GemType("petalite", "Petalite",
            6.0F,
            22,
            new Petalite()).register();
    public static final GemType PHOSPHOPHYLLITE = new GemType("phosphophyllite", "Phosphophyllite",
            3.5F,
            30,
            new Phosphophyllite()).register();
    public static final GemType ROSEQUARTZ = new GemType("rosequartz", "Rose Quartz",
            7F,
            10,
            new RoseQuartz()).register();
    public static final GemType RUBY = new GemType("ruby", "Ruby",
            9F,
            17,
            new Ruby()).register();
    public static final GemType SAPPHIRE = new GemType("sapphire", "Sapphire",
            9F,
            17,
            new Sapphire()).register();
    public static final GemType TANZANITE = new GemType("tanzanite", "Tanzanite",
            6.5F,
            15,
            new Tanzanite()).register();
    public static final GemType TOPAZ = new GemType("topaz", "Topaz",
            8.0F,
            7,
            new Topaz()).register();

    private final String name;
    private final String displayName;
    private final float hardness;
    private final MaterialGem material;

    private final int capacity;
    private final float rarity;
    private final GemImplementation implementation;

    public GemType(String name,
                   String  displayName,
                   float hardness,
                   int magicCapacity,
                   GemImplementation implementation) {
        this.name = name;
        this.displayName = displayName;
        this.hardness = hardness;
        this.capacity = magicCapacity;
        this.rarity = ((Math.round((20F - hardness - capacity) * 100F) / 100F) + 1F) / 10F;
        this.implementation = implementation;
        material = new MaterialGem(this);
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public float getHardness() {
        return hardness;
    }

    public int getCapacity() {
        return capacity;
    }

    public float getRarity() {
        return rarity;
    }

    public MaterialGem getMaterial() {
        return material;
    }

    public GemImplementation getImplementation() {
        return implementation;
    }

    public GemType register() {
        GemType.MAP.put(this.name, this);
        return this;
    }

    @Override
    public int compareTo(@NotNull GemType o) {
        return this.name.compareTo(o.name);
    }

    public static void forEach(BiConsumer<String, GemType> function) {
        MAP.forEach(function);
    }
}

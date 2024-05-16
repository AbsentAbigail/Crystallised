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
            0xffab02, 0x916100,
            new Amber()).register();
    public static final GemType AZURITE = new GemType("azurite", "Azurite",
            3.5F,
            13,
            0x043681, 0x021c43,
            new Azurite()).register();
    public static final GemType FLUORITE = new GemType("fluorite", "Fluorite",
            4F,
            13,
            0xffab02, 0x916100,
            new Fluorite()).register();
    public static final GemType KUNZITE = new GemType("kunzite", "Kunzite",
            7F,
            18,
            0xab4491, 0xda8bd7,
            new Kunzite()).register();
    public static final GemType MOISSANITE = new GemType("moissanite", "Moissanite",
            9.5F,
            20,
            0xddfbf3, 0xddfbf3,
            new Moissanite()).register();
    public static final GemType MOONSTONE = new GemType("moonstone", "Moonstone",
            6.5F,
            30,
            0xfbf5ff, 0x9cffff,
            new Moonstone()).register();
    public static final GemType ONYX = new GemType("onyx", "Onyx",
            7F,
            10,
            0xffffff, 0x000000,
            new Onyx()).register();
    public static final GemType PERIDOT = new GemType("peridot", "Peridot",
            6.5F,
            13,
            0x089f00, 0x045200,
            new Peridot()).register();
    public static final GemType PETALITE = new GemType("petalite", "Petalite",
            6.0F,
            22,
            0xf1e7ec, 0xe9a3c8,
            new Petalite()).register();
    public static final GemType PHOSPHOPHYLLITE = new GemType("phosphophyllite", "Phosphophyllite",
            3.5F,
            30,
            0x21c773, 0x008340).register();
    public static final GemType ROSEQUARTZ = new GemType("rosequartz", "Rose Quartz",
            7F,
            10,
            0xca5281, 0xe3a9e0,
            new RoseQuartz()).register();
    public static final GemType RUBY = new GemType("ruby", "Ruby",
            9F,
            17,
            0xff0000, 0xff0000,
            new Ruby()).register();
    public static final GemType SAPPHIRE = new GemType("sapphire", "Sapphire",
            9F,
            17,
            0x1700ff, 0x1700ff,
            new Sapphire()).register();
    public static final GemType TANZANITE = new GemType("tanzanite", "Tanzanite",
            6.5F,
            15,
            0xddfbf3, 0xddfbf3,
            new Tanzanite()).register();
    public static final GemType TOPAZ = new GemType("topaz", "Topaz",
            8.0F,
            7,
            0xfee002, 0xe98000,
            new Topaz()).register();

    private final String name;
    private final String displayName;
    private final float hardness;
    private final MaterialGem material;
    private BaseImplementation implementation = new BaseImplementation();

    private final int capacity;
    private final float rarity;
    private final int primaryColour;
    private final int secondaryColour;

    public GemType(String name,
                   String  displayName,
                   float hardness,
                   int magicCapacity,
                   int primaryColour, int secondaryColour) {
        this.name = name;
        this.displayName = displayName;
        this.hardness = hardness;
        this.capacity = magicCapacity;
        this.rarity = ((Math.round((20F - hardness - capacity) * 100F) / 100F) + 1F) / 10F;
        this.primaryColour = primaryColour;
        this.secondaryColour = secondaryColour;
        material = new MaterialGem(this);
    }

    public GemType(String name,
                   String displayName,
                   float hardness,
                   int magicCapacity,
                   int primarycolour, int secondarycolour,
                   BaseImplementation implementation) {
        this(name, displayName, hardness, magicCapacity, primarycolour, secondarycolour);
        this.implementation = implementation;
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

    public int colour1() {
        return primaryColour;
    }

    public int colour2() {
        return secondaryColour;
    }

    public MaterialGem getMaterial() {
        return material;
    }

    public BaseImplementation getImplementation() {
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

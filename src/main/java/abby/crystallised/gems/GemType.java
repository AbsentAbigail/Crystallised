package abby.crystallised.gems;

import abby.crystallised.gems.implementation.*;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class GemType implements Comparable<GemType> {
    public static final Map<String, GemType> MAP = new LinkedHashMap<>();

    static {
        new GemType("amber",
                2.2F,
                1F,
                0xffab02, 0x916100,
                new Amber()).register();
        new GemType("azurite",
                3.5F,
                8F,
                0x043681, 0x021c43,
                new Azurite()).register();
        new GemType("fluorite",
                4F,
                4F,
                0xffab02, 0x916100,
                new Fluorite()).register();
        new GemType("kunzite",
                7F,
                6F,
                0xab4491, 0xda8bd7,
                new Kunzite()).register();
        new GemType("moissanite",
                9.5F,
                10F,
                0xddfbf3, 0xddfbf3).register();
        new GemType("moonstone",
                6.5F,
                13F,
                0xfbf5ff, 0x9cffff,
                new Moonstone()).register();
        new GemType("onyx",
                7F,
                7.5F,
                0xffffff, 0x000000,
                new Onyx()).register();
        new GemType("peridot",
                6.5F,
                4F,
                0x089f00, 0x045200,
                new Peridot()).register();
        new GemType("petalite",
                6.0F,
                4F,
                0xf1e7ec, 0xe9a3c8,
                new Petalite()).register();
        new GemType("phosphophyllite",
                3.5F,
                2F,
                0x21c773, 0x008340).register();
        new GemType("rosequartz",
                7F,
                5F,
                0xca5281, 0xe3a9e0,
                new RoseQuartz()).register();
        new GemType("ruby",
                9F,
                10F,
                0xff0000, 0xff0000,
                new Ruby()).register();
        new GemType("sapphire",
                9F,
                10F,
                0x1700ff, 0x1700ff,
                new Sapphire()).register();
        new GemType("tanzanite",
                6.5F,
                10F,
                0xddfbf3, 0xddfbf3,
                new Tanzanite()).register();
    }

    private String name;
    private String displayname;
    private float hardness;
    private MaterialGem material;
    private BaseImplementation implementation = new BaseImplementation();

    private float capacity;
    private float rarity;
    private int primarycolour;
    private int secondarycolour;

    public GemType(String name,
                   float hardness,
                   float magicCapacity,
                   int primarycolour, int secondarycolour) {
        this.name = name;
        this.hardness = hardness;
        this.capacity = magicCapacity;
        this.rarity = ((Math.round((20F - hardness - capacity) * 100F) / 100F) + 1F) / 10F;
        this.primarycolour = primarycolour;
        this.secondarycolour = secondarycolour;
        material = new MaterialGem(this);
    }

    public GemType(String name,
                   float hardness,
                   float magicCapacity,
                   int primarycolour, int secondarycolour,
                   BaseImplementation implementation) {
        this(name, hardness, magicCapacity, primarycolour, secondarycolour);
        this.implementation = implementation;
    }

    public String getName() {
        return name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public float getHardness() {
        return hardness;
    }

    public float getCapacity() {
        return capacity;
    }

    public float getRarity() {
        return rarity;
    }

    public int colour1() {
        return primarycolour;
    }

    public int colour2() {
        return secondarycolour;
    }

    public MaterialGem getMaterial() {
        return material;
    }

    public BaseImplementation getImplementation() {
        return implementation;
    }

    public void register() {
        GemType.MAP.put(this.name.toUpperCase(), this);
    }

    @Override
    public int compareTo(@NotNull GemType o) {
        return this.name.compareTo(o.name);
    }
}

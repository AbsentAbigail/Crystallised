package abby.crystallised;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {

    public static final Logger LOGGER = LoggerFactory.getLogger(Constants.MODID);

    public static Identifier identifier(String path) {
        return new Identifier(Constants.MODID, path);
    }
}

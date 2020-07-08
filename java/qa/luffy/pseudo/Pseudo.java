package qa.luffy.pseudo;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import qa.luffy.pseudo.setup.Config;
import qa.luffy.pseudo.setup.ClientSetup;
import qa.luffy.pseudo.setup.ModSetup;
import qa.luffy.pseudo.setup.Registration;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Pseudo.MODID)
public class Pseudo {

    public static final String MODID = "pseudo";

    private static final Logger LOGGER = LogManager.getLogger();

    public Pseudo() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        Registration.init();

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    }
}
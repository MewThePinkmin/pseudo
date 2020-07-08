package qa.luffy.pseudo.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import qa.luffy.pseudo.Pseudo;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = Pseudo.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final ItemGroup PSEUDO_GROUP = new ItemGroup("pseudo") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.GRAPHITE.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public static void serverLoad(FMLServerStartingEvent event) {
    }

    @SubscribeEvent
    public static void onDimensionRegistry(RegisterDimensionsEvent event) {
    }
}
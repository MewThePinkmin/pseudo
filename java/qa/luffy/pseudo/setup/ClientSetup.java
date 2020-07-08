package qa.luffy.pseudo.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import qa.luffy.pseudo.Pseudo;
import qa.luffy.pseudo.screen.MeshChestScreen;

@Mod.EventBusSubscriber(modid = Pseudo.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(Registration.MESH_CHEST_CONTAINER.get(), MeshChestScreen::new);
    }

    @SubscribeEvent
    public static void onItemColor(ColorHandlerEvent.Item event) {
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
    }

    @SubscribeEvent
    public void onTooltipPre(RenderTooltipEvent.Pre event) {
        Item item = event.getStack().getItem();
        if (item.getRegistryName().getNamespace().equals(Pseudo.MODID)) {
            event.setMaxWidth(200);
        }
    }
}
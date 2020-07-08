package qa.luffy.pseudo.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import qa.luffy.pseudo.Pseudo;
import qa.luffy.pseudo.inventory.MeshChestContainer;

@OnlyIn(Dist.CLIENT)
public class MeshChestScreen extends ContainerScreen<MeshChestContainer>
{
    private int xSize = 184;
    private int ySize = 276;

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Pseudo.MODID, "textures/gui/mesh_chest.png");

    public MeshChestScreen(MeshChestContainer container, PlayerInventory playerInventory, ITextComponent title)
    {
        super(container, playerInventory, title);

        this.passEvents = false;
        this.guiLeft = 0;
        this.guiTop = 0;
    }

    public static void blit(AbstractGui object, int xPos, int yPos, int textureX, int textureY, int width, int height) {
        object.blit(xPos, yPos, textureX, textureY, width, height);}

    @Override
    public void render(int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        blit((MeshChestScreen) this, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

}
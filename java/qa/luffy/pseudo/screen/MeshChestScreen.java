package qa.luffy.pseudo.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import qa.luffy.pseudo.Pseudo;
import qa.luffy.pseudo.inventory.MeshChestContainer;

@OnlyIn(Dist.CLIENT)
public class MeshChestScreen extends ContainerScreen<MeshChestContainer>{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Pseudo.MODID, "textures/gui/mesh_chest.png");

    public MeshChestScreen(MeshChestContainer container, PlayerInventory playerInventory, ITextComponent titleIn){
		super(container, playerInventory, titleIn);
		this.guiLeft = 0;
		this.guiTop = 0;
		this.xSize = 230;
		this.ySize = 240;
	}
	
	@Override
	public void render(final int mousex, final int mousey, final float partialTicks) {
		this.renderBackground();
		super.render(mousex, mousey, partialTicks);
		this.renderHoveredToolTip(mousex, mousey);
	}
	

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.font.drawString(this.title.getFormattedText(), 7.0f, 6.0f, 786458);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 35f, 146f, 786458);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// red, green, blue, transparency
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
		int x = (this.width-this.xSize)/2;
		int y = (this.height-this.ySize)/2;
		this.blit(x, y, 0, 0, this.xSize, this.ySize);
	}
}
package qa.luffy.pseudo.block;

import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import qa.luffy.pseudo.tileentity.MeshChestTileEntity;

import javax.annotation.Nullable;

public enum MeshChestType implements IStringSerializable
{
    MESH_CHEST(81, 9, "blocks/mesh_chest.png", MeshChestTileEntity.class, "pseudo:mesh_chest", 184, 276, new ResourceLocation("mesh_chest", "textures/gui/mesh_chest.png"), 256, 276);

    public static final MeshChestType VALUES[] = values();

    public final String name;

    public final int size;

    public final int rowLength;

    public final String modelTexture;

    public final Class<? extends MeshChestTileEntity> clazz;

    public final String itemName;

    public final int xSize;

    public final int ySize;

    public final ResourceLocation guiTexture;

    public final int textureXSize;

    public final int textureYSize;

    MeshChestType(int size, int rowLength, String modelTexture, Class<? extends MeshChestTileEntity> clazz, String itemName, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize)
    {
        this.name = this.name().toLowerCase();
        this.size = size;
        this.rowLength = rowLength;
        this.modelTexture = modelTexture;
        this.clazz = clazz;
        this.itemName = itemName;
        this.xSize = xSize;
        this.ySize = ySize;
        this.guiTexture = guiTexture;
        this.textureXSize = textureXSize;
        this.textureYSize = textureYSize;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    public int getRowCount()
    {
        return this.size / this.rowLength;
    }

    public static MeshChestType get(ResourceLocation resourceLocation)
    {
        switch (resourceLocation.toString())
        {
            case "pseudo:mesh_chest":
                return MESH_CHEST;
            default:
                return MESH_CHEST;
        }
    }


}
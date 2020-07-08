package qa.luffy.pseudo.inventory;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import qa.luffy.pseudo.block.MeshChestBlock;

public class MeshChestSlot extends Slot
{
    public MeshChestSlot(IInventory inventoryIn, int slotIndexIn, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndexIn, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return !(Block.getBlockFromItem(stack.getItem()) instanceof MeshChestBlock) && !(Block.getBlockFromItem(stack.getItem()) instanceof net.minecraft.block.ShulkerBoxBlock);
    }
}

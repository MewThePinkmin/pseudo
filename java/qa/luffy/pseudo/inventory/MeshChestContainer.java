package qa.luffy.pseudo.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import qa.luffy.pseudo.block.MeshChestType;
import qa.luffy.pseudo.setup.Registration;
import qa.luffy.pseudo.tileentity.MeshChestTileEntity;

import java.util.Objects;

public class MeshChestContainer extends Container {

    private IInventory inventory;
    public final MeshChestTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public MeshChestContainer(final int windowId, final PlayerInventory playerInventory, IInventory inventory,
                                 final MeshChestTileEntity tileEntity) {
        super(Registration.MESH_CHEST_CONTAINER.get(), windowId);
        assertInventorySize(tileEntity, 81);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        // Main Inventory
        int xSize = 184;
        int ySize = 276;

        for (int row = 0; row < 9; row++)
        {
            for (int meshCol = 0; meshCol < 9; meshCol++)
            {
                this.addSlot(new MeshChestSlot(inventory, meshCol + row * 9, 12 + meshCol * 18, 18 + row * 18));
            }
        }

        int leftCol = (xSize - 162) / 2 + 1;

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++)
        {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++)
            {
                this.addSlot(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, ySize - (4 - playerInvRow) * 18 - 10));
            }

        }

        for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++)
        {
            this.addSlot(new Slot(playerInventory, hotbarSlot, leftCol + hotbarSlot * 18, ySize - 24));
        }
    }

    private static MeshChestTileEntity getTileEntity(final PlayerInventory playerInventory,
                                                        final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof MeshChestTileEntity) {
            return (MeshChestTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, Registration.MESH_CHEST.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 81) {
                if (!this.mergeItemStack(itemstack1, 81, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 81, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
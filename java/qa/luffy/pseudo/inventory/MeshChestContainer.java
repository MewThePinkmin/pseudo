package qa.luffy.pseudo.inventory;

import java.util.Objects;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import qa.luffy.pseudo.setup.Registration;
import qa.luffy.pseudo.tileentity.MeshChestTileEntity;

public class MeshChestContainer extends Container {

	private IInventory inventory;
    public final MeshChestTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public MeshChestContainer(final int windowId, final PlayerInventory playerInventory, IInventory inventory, final MeshChestTileEntity tileEntity) {
        super(Registration.MESH_CHEST_CONTAINER.get(), windowId);
        assertInventorySize(tileEntity, 84);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

		// chest inv
		int startX = 8;
		int startY = 18;
		int slotSizePlus2 = 18;
		for (int row = 0; row < 7; ++row) {
			for (int col = 0; col < 12; ++col) {
				this.addSlot(new Slot(tileEntity, (row * 12) + col, startX + (col * slotSizePlus2),
						startY + (row * slotSizePlus2)));
			}
		}
		// player inv
		startX = 35;
		int startPlayerInvY = 158;
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + col, startX + (col * slotSizePlus2),
						startPlayerInvY + (row * slotSizePlus2)));
			}
		}
		// hotbar
		int hotY = 216;
		for (int col = 0; col < 9; ++col) {
			this.addSlot(new Slot(playerInventory, col, startX + (col * slotSizePlus2), hotY));
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
            if (index < 84) {
                if (!this.mergeItemStack(itemstack1, 84, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 84, false)) {
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
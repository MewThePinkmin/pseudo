package qa.luffy.pseudo.block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.EnumProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import qa.luffy.pseudo.Pseudo;
import qa.luffy.pseudo.setup.Registration;
import qa.luffy.pseudo.tileentity.MeshChestTileEntity;

import javax.annotation.Nullable;
import java.util.List;

public class MeshChestBlock extends Block
{
    public static final ResourceLocation CONTENTS = new ResourceLocation(Pseudo.MODID, "contents");


    public MeshChestBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return Registration.MESH_CHEST_TILE.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult result) {
        if (!worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof MeshChestTileEntity) {
                player.openContainer((MeshChestTileEntity) tile);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof MeshChestTileEntity)
        {
            MeshChestTileEntity meshchesttileentity = (MeshChestTileEntity) tileentity;
            if (!worldIn.isRemote && player.isCreative() && !meshchesttileentity.isEmpty())
            {
                ItemStack itemstack = getItem(worldIn, pos, state);
                CompoundNBT compoundnbt = meshchesttileentity.write(new CompoundNBT());
                if (!compoundnbt.isEmpty())
                {
                    itemstack.setTagInfo("BlockEntityTag", compoundnbt);
                }

                if (meshchesttileentity.hasCustomName())
                {
                    itemstack.setDisplayName(meshchesttileentity.getCustomName());
                }

                ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), itemstack);
                itementity.setDefaultPickupDelay();
                worldIn.addEntity(itementity);
            }
            else
            {
                meshchesttileentity.fillWithLoot(player);
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        TileEntity tileentity = builder.get(LootParameters.BLOCK_ENTITY);
        if (tileentity instanceof MeshChestTileEntity) {
            MeshChestTileEntity MeshChestTileEntity = (MeshChestTileEntity) tileentity;
            builder = builder.withDynamicDrop(CONTENTS, (p_220168_1_, p_220168_2_) -> {
                for (int i = 0; i < MeshChestTileEntity.getSizeInventory(); ++i) {
                    p_220168_2_.accept(MeshChestTileEntity.getStackInSlot(i));
                }
            });
        }

        return super.getDrops(state, builder);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof MeshChestTileEntity) {
                ((MeshChestTileEntity) tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof MeshChestTileEntity) {
                worldIn.updateComparatorOutputLevel(pos, state.getBlock());
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CompoundNBT compoundnbt = stack.getChildTag("BlockEntityTag");
        if (compoundnbt != null)
        {
            if (compoundnbt.contains("LootTable", 8))
            {
                tooltip.add(new StringTextComponent("???????"));
            }

            if (compoundnbt.contains("Items", 9))
            {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(compoundnbt, nonnulllist);
                int i = 0;
                int j = 0;

                for (ItemStack itemstack : nonnulllist)
                {
                    if (!itemstack.isEmpty())
                    {
                        ++j;
                        if (i <= 4)
                        {
                            ++i;
                            ITextComponent itextcomponent = itemstack.getDisplayName().deepCopy();
                            itextcomponent.appendText(" x").appendText(String.valueOf(itemstack.getCount()));
                            tooltip.add(itextcomponent);
                        }
                    }
                }

                if (j - i > 0)
                {
                    tooltip.add((new TranslationTextComponent("container.mesh_chest.more", j - i)).applyTextStyle(TextFormatting.ITALIC));
                }
            }
        }
    }
}
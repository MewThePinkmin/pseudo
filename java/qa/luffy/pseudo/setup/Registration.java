package qa.luffy.pseudo.setup;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import qa.luffy.pseudo.block.MeshChestBlock;
import qa.luffy.pseudo.inventory.MeshChestContainer;
import qa.luffy.pseudo.item.ItemBase;
import qa.luffy.pseudo.item.Mitts;
import qa.luffy.pseudo.tileentity.MeshChestTileEntity;

import static qa.luffy.pseudo.Pseudo.MODID;
import static qa.luffy.pseudo.setup.ModSetup.PSEUDO_GROUP;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    private static final DeferredRegister<ModDimension> DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    //blocks
    public static final RegistryObject<Block> GRAPHITE_BLOCK = BLOCKS.register("graphite_block", () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f, 5.0f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> MESH_BLOCK = BLOCKS.register("mesh_block", () -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 1000.0f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> MESH_CHEST = BLOCKS.register("mesh_chest", () -> new MeshChestBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 1000.0f).sound(SoundType.METAL)));

    //container
    public static final RegistryObject<ContainerType<MeshChestContainer>> MESH_CHEST_CONTAINER = CONTAINERS.register("mesh_chest", () -> IForgeContainerType.create((windowId, inv, data) -> { return new MeshChestContainer(windowId, inv.player.inventory, inv, new MeshChestTileEntity());}));

    //tileentity
    public static final RegistryObject<TileEntityType<MeshChestTileEntity>> MESH_CHEST_TILE = TILES.register("mesh_chest", () -> TileEntityType.Builder.create(MeshChestTileEntity::new, MESH_CHEST.get()).build(null));

    //block-items
    public static final RegistryObject<Item> GRAPHITE_BLOCK_ITEM = ITEMS.register("graphite_block", () -> new BlockItem(GRAPHITE_BLOCK.get(), new Item.Properties().group(PSEUDO_GROUP)));
    public static final RegistryObject<Item> MESH_BLOCK_ITEM = ITEMS.register("mesh_block", () -> new BlockItem(MESH_BLOCK.get(), new Item.Properties().group(PSEUDO_GROUP)));
    public static final RegistryObject<Item> MESH_CHEST_ITEM = ITEMS.register("mesh_chest", () -> new BlockItem(MESH_CHEST.get(), new Item.Properties().group(PSEUDO_GROUP)));

    //items
    public static final RegistryObject<Item> GRAPHITE = ITEMS.register("graphite", ItemBase::new);
    public static final RegistryObject<Item> GRAPHENE_SHEET = ITEMS.register("graphene_sheet", ItemBase::new);
    public static final RegistryObject<Item> GRAPHENE_MESH = ITEMS.register("graphene_mesh", ItemBase::new);
    public static final RegistryObject<Item> MOLE_MITT = ITEMS.register("digging_mitt", () -> new Mitts(PseudoToolMaterials.mole, -1,6.0f, new Item.Properties().group(PSEUDO_GROUP).addToolType(ToolType.PICKAXE, PseudoToolMaterials.mole.getHarvestLevel()).addToolType(ToolType.AXE, PseudoToolMaterials.mole.getHarvestLevel()).addToolType(ToolType.SHOVEL, PseudoToolMaterials.mole.getHarvestLevel())));
    public static final RegistryObject<Item> MESH_MITT = ITEMS.register("mesh_mitt", () -> new Mitts(PseudoToolMaterials.mesh, -1,6.0f, new Item.Properties().group(PSEUDO_GROUP).addToolType(ToolType.PICKAXE, PseudoToolMaterials.mesh.getHarvestLevel()).addToolType(ToolType.AXE, PseudoToolMaterials.mesh.getHarvestLevel()).addToolType(ToolType.SHOVEL, PseudoToolMaterials.mesh.getHarvestLevel())));
}
package qa.luffy.pseudo.setup;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public enum PseudoToolMaterials implements IItemTier
{
    mole(3.0f, 6.5f, 400, 2, 14, Items.FLINT),
    mesh(4.0f, 9.0f, 1000, 3, 12, Registration.GRAPHENE_MESH.get());

    private float attackDamage, efficiency;
    private int durability, harvestLevel, enchantability;
    private Item repairMaterial;

    private PseudoToolMaterials(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial)
    {
        this.attackDamage = attackDamage;
        this.efficiency = efficiency;
        this.durability = durability;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public float getAttackDamage()
    {
        return this.attackDamage;
    }

    @Override
    public float getEfficiency()
    {
        return this.efficiency;
    }

    @Override
    public int getEnchantability()
    {
        return this.enchantability;
    }

    @Override
    public int getHarvestLevel()
    {
        return this.harvestLevel;
    }

    @Override
    public int getMaxUses()
    {
        return this.durability;
    }

    @Override
    public Ingredient getRepairMaterial()
    {
        return Ingredient.fromItems(this.repairMaterial);
    }
}

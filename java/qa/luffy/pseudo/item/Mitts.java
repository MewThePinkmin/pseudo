package qa.luffy.pseudo.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;

public class Mitts extends PickaxeItem
{
    public Mitts(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder)
    {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }
}


package qa.luffy.pseudo.item;

import net.minecraft.item.Item;
import qa.luffy.pseudo.setup.ModSetup;

public class ItemBase extends Item {

    public ItemBase() {
        super(new Item.Properties().group(ModSetup.PSEUDO_GROUP));
    }
}
package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.interfaces.Enchantable;

public abstract class EnchantItem extends Item implements Enchantable {
    public EnchantItem(String description) {
        super(description);
    }
}

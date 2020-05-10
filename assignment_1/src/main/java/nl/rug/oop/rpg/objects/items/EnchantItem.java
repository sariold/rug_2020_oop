package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.interfaces.Enchantable;

/**
 * Class that creates an enchanted item, a normal item can become enchanted which boosts its stats
 */
public abstract class EnchantItem extends Item implements Enchantable {

    private static final long serialVersionUID = 36L;


    /**
     * Creates an enchanted item and sets its description
     * @param description Description
     */
    public EnchantItem(String description) {
        super(description);
    }
}


package net.ender.ess_requiem.item.curio;

import io.redspace.ironsspellbooks.compat.Curios;
import io.redspace.ironsspellbooks.item.curios.SimpleDescriptiveCurio;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;

public class NamelessRingCurio extends SimpleDescriptiveCurio {
    public NamelessRingCurio() {
        super(ItemPropertiesHelper.equipment().stacksTo(1), Curios.RING_SLOT);
    }

}


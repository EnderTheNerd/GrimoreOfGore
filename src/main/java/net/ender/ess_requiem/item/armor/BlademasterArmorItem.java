package net.ender.ess_requiem.item.armor;


import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.registries.ArmorMaterialRegistry;
import net.ender.ess_requiem.entity.armor.BlademasterArmorModel;
import net.ender.ess_requiem.registries.GGAttributeRegistry;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class BlademasterArmorItem extends ImbuableChestplateArmorItem {

    public BlademasterArmorItem(ArmorItem.Type slot, Properties settings) {
        super(ArmorMaterialRegistry.SCHOOL, slot, settings, schoolAttributes(GGAttributeRegistry.BLADE_SPELL_POWER));
    }



    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new BlademasterArmorModel());
    }






}


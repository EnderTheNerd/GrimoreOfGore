package net.ender.ess_requiem.registries;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.ender.ess_requiem.EndersSpellsAndStuffRequiem;
import net.ender.ess_requiem.Util.GGTags;
import net.ender.ess_requiem.damage.GGDamageTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class GGSchoolRegistry extends SchoolRegistry {
    private static final DeferredRegister<SchoolType> ENDER_SCHOOLS = DeferredRegister.create(SCHOOL_REGISTRY_KEY, EndersSpellsAndStuffRequiem.MOD_ID);


    public static void register(IEventBus eventBus) {

        ENDER_SCHOOLS.register(eventBus);
    }


    private static Holder<SchoolType> registerSchool(SchoolType type) {
        return ENDER_SCHOOLS.register(type.getId().getPath(), () -> type);
    }


    @Nullable
    public static SchoolType getSchoolFromFocus(ItemStack focusStack) {

        for (SchoolType school : REGISTRY) {
            if (school.isFocus(focusStack)) {
                return school;
            }
        }
        return null;
    }

    public static final ResourceLocation BLADE_RESOURCE = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "blade");

    public static final Supplier<SchoolType> SPELLBLADE = (Supplier<SchoolType>) registerSchool(new SchoolType(
            BLADE_RESOURCE,
            GGTags.BLADE_FOCUS,
            Component.translatable("school.ess_requiem.spellblade").withColor(15355541),
            GGAttributeRegistry.BLADE_SPELL_POWER,
            GGAttributeRegistry.BLADE_MAGIC_RESIST,
            SoundRegistry.DEAD_KING_SWING,
            GGDamageTypes.BLADE_MAGIC
    ));




}

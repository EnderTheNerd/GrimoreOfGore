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

    public static final ResourceLocation MIND_RESOURCE = ResourceLocation.fromNamespaceAndPath(EndersSpellsAndStuffRequiem.MOD_ID, "mind");

    public static final Supplier<SchoolType> MIND = (Supplier<SchoolType>) registerSchool(new SchoolType(
            MIND_RESOURCE,
            GGTags.MIND_FOCUS,
            Component.translatable("school.endersequipment.mind").withColor(15556694),
            GGAttributeRegistry.MIND_SPELL_POWER,
            GGAttributeRegistry.MIND_MAGIC_RESIST,
            GGSoundRegistry.MIND_GENERIC_CAST,
            GGDamageTypes.MIND_MAGIC
    ));


}

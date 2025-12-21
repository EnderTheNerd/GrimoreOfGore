package net.ender.ess_requiem.effects;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class StunnedEffect extends MobEffect {

    protected StunnedEffect(MobEffectCategory category, int color) {
        super(MobEffectCategory.HARMFUL, 3020845);
    }
}

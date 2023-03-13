package dhyces.stayhydrated.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dhyces.stayhydrated.StayHydrated;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public class PlayerMixin {

    @ModifyReturnValue(method = "getDestroySpeed", at = @At("RETURN"))
    private float stayhydrated$modifyBreakSpeed(float value) {
        return StayHydrated.modifyBreakSpeed((Player) (Object) this, value);
    }
}

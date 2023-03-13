package dhyces.stayhydrated;

import dhyces.stayhydrated.handlers.ThirstHandler;
import dhyces.stayhydrated.services.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public class StayHydrated {
    public static final String MODID = "stayhydrated";

    public static ResourceLocation id(String id) {
        return new ResourceLocation(MODID, id);
    }

    public static void init() {

    }

    public static float modifyBreakSpeed(Player player, float currentVal) {
        Optional<ThirstHandler> thirstOptional = Services.PLATFORM_HELPER.getHandler(player);
        if (thirstOptional.isPresent()) {
            ThirstHandler handler = thirstOptional.get();
            if (handler.isDehydrated()) {
                return currentVal * 0.2f;
            }
        }
        return currentVal;
    }
}

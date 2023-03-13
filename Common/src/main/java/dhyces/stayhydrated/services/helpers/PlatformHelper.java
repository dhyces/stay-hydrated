package dhyces.stayhydrated.services.helpers;

import dhyces.stayhydrated.handlers.ThirstHandler;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public interface PlatformHelper {
    boolean isModLoaded(String modid);

    Optional<ThirstHandler> getHandler(Player player);
}

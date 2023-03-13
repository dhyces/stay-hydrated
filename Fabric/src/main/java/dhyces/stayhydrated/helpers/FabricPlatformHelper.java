package dhyces.stayhydrated.helpers;

import dhyces.stayhydrated.handlers.ModComponents;
import dhyces.stayhydrated.handlers.ThirstHandler;
import dhyces.stayhydrated.services.helpers.PlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public class FabricPlatformHelper implements PlatformHelper {
    @Override
    public boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    @Override
    public Optional<ThirstHandler> getHandler(Player player) {
        return ModComponents.THIRST.maybeGet(player).map(thirstComponent -> thirstComponent); // ... Can't believe that's needed
    }
}

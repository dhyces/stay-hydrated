package dhyces.stayhydrated.helpers;

import dhyces.stayhydrated.handlers.ThirstCapability;
import dhyces.stayhydrated.handlers.ThirstHandler;
import dhyces.stayhydrated.services.helpers.PlatformHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.ModList;

import java.util.Optional;

public class ForgePlatformHelper implements PlatformHelper {
    @Override
    public boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    @Override
    public Optional<ThirstHandler> getHandler(Player player) {
        return player.getCapability(ThirstCapability.CAPABILITY).resolve();
    }
}

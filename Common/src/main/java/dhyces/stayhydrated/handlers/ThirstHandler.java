package dhyces.stayhydrated.handlers;

import dhyces.stayhydrated.StayHydrated;
import dhyces.stayhydrated.util.ClampedIntCounter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;

public class ThirstHandler extends ClampedIntCounter {
    public static final ResourceLocation ID = StayHydrated.id("thirst_level");

    private float exhaustion = 0;
    private int tick = 0;

    public ThirstHandler() {
        this(0, 20, 20);
    }

    public ThirstHandler(int min, int max, int current) {
        super(min, max, current);
    }

    public boolean tick(Player player) {
        boolean hasChanged = false;
        ++tick;
        if (player.level.getDifficulty() != Difficulty.PEACEFUL) {
            if (tick >= 100) {
                exhaustion += 0.5;
                tick = 0;
                hasChanged = true;
            }
            if (!isMin() && exhaustion > 4) {
                set(current-1);
                exhaustion -= 4;
                hasChanged = true;
            }
        } else {
            if (tick >= 10) {
                set(current+1);
                tick = 0;
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    public boolean isDehydrated() {
        return current <= 4;
    }
}

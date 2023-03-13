package dhyces.stayhydrated.handlers;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ThirstComponent extends ThirstHandler implements AutoSyncedComponent, ServerTickingComponent {

    private final Player player;

    public ThirstComponent(Player player) {
        super();
        this.player = player;
    }

    public ThirstComponent(Player player, int min, int max, int current) {
        super(min, max, current);
        this.player = player;
    }

    @Override
    public void serverTick() {
        if (!player.isCreative()) {
            boolean hasChanged = tick(player);
            if (hasChanged) ModComponents.THIRST.sync(player);
        }
    }

    @Override
    public boolean shouldSyncWith(ServerPlayer player) {
        return player.getUUID().equals(this.player.getUUID());
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.current = tag.getInt("Thirst");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("Thirst", current);
    }
}

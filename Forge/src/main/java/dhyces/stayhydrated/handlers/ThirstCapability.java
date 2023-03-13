package dhyces.stayhydrated.handlers;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ThirstCapability extends ThirstHandler implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<ThirstHandler> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    private final LazyOptional<ThirstHandler> lazy = LazyOptional.of(() -> this);

    public ThirstCapability() {
        super();
    }

    public ThirstCapability(int min, int max, int current) {
        super(min, max, current);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CAPABILITY.orEmpty(cap, lazy);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Thirst", current);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.current = nbt.getInt("Thirst");
    }
}

package dhyces.stayhydrated.handlers;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.world.entity.player.Player;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<ThirstComponent> THIRST = ComponentRegistryV3.INSTANCE.getOrCreate(ThirstHandler.ID, ThirstComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(Player.class, THIRST, ThirstComponent::new);
    }
}

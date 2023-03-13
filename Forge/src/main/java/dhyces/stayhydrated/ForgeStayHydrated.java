package dhyces.stayhydrated;

import dhyces.stayhydrated.handlers.ThirstCapability;
import dhyces.stayhydrated.handlers.ThirstHandler;
import dhyces.stayhydrated.util.ClampedIntCounter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;

@Mod(StayHydrated.MODID)
public class ForgeStayHydrated {

    public ForgeStayHydrated() {
        StayHydrated.init();
        Networking.register();
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        modBus.addListener(this::registerCap);
        forgeBus.addGenericListener(Entity.class, this::attachCap);
        forgeBus.addListener(this::playerTick);
        forgeBus.addListener(this::syncThirstOnPlayerJoin);
        forgeBus.addListener(this::exhaustionBreakSpeed);

        if (FMLLoader.getDist().isClient()) {
            ForgeStayHydratedClient.clientInit(modBus);
        }
    }

    private void registerCap(final RegisterCapabilitiesEvent event) {
        event.register(ClampedIntCounter.class);
    }

    private void attachCap(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(ThirstCapability.ID, new ThirstCapability());
        }
    }

    private void syncThirstOnPlayerJoin(final PlayerEvent.PlayerLoggedInEvent event) {
        LazyOptional<ThirstHandler> thirstLazy = event.getEntity().getCapability(ThirstCapability.CAPABILITY);
        thirstLazy.ifPresent(handler -> Networking.sendPlayerThirstSync((ServerPlayer) event.getEntity(), handler.get()));
    }

    private void exhaustionBreakSpeed(final PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        event.setNewSpeed(StayHydrated.modifyBreakSpeed(player, event.getNewSpeed()));
    }

    private void playerTick(final TickEvent.PlayerTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END) && event.side.isServer()) {
            ServerPlayer player = (ServerPlayer) event.player;

            if (!player.isCreative()) {
                LazyOptional<ThirstHandler> thirstLazy = player.getCapability(ThirstCapability.CAPABILITY);
                thirstLazy.ifPresent(handler -> {
                    handler.tick(player);
                    Networking.sendPlayerThirstSync(player, handler.get());
                });
            }
        }
    }
}

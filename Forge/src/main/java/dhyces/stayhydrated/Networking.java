package dhyces.stayhydrated;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Networking {
    private static final String VERSION = "1";
    public static final SimpleChannel NETWORK_CHANNEL = NetworkRegistry.newSimpleChannel(StayHydrated.id("main"), () -> VERSION, VERSION::equals, VERSION::equals);

    public static void register() {
        NETWORK_CHANNEL.registerMessage(0, ThirstSyncPacket.class, ThirstSyncPacket::encoder, ThirstSyncPacket::decoder, ThirstSyncPacket::messageConsumer);
    }

    public static void sendPlayerThirstSync(ServerPlayer player, int value) {
        NETWORK_CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new ThirstSyncPacket(value));
    }
}

package dhyces.stayhydrated.client;

import dhyces.stayhydrated.handlers.ThirstCapability;
import dhyces.stayhydrated.handlers.ThirstHandler;
import dhyces.stayhydrated.ThirstSyncPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.NetworkEvent;

public class ClientNetworking {
    public static void handlePacket(ThirstSyncPacket packet, NetworkEvent.Context ctx) {
        LazyOptional<ThirstHandler> thirstLazy = Minecraft.getInstance().player.getCapability(ThirstCapability.CAPABILITY);
        thirstLazy.ifPresent(thirstHandler -> thirstHandler.set(packet.thirstVal()));
    }
}

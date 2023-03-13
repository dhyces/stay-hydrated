package dhyces.stayhydrated;

import dhyces.stayhydrated.client.ClientNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record ThirstSyncPacket(int thirstVal) {
    public void encoder(FriendlyByteBuf buffer) {
        buffer.writeInt(thirstVal);
    }

    public static ThirstSyncPacket decoder(FriendlyByteBuf buffer) {
        return new ThirstSyncPacket(buffer.readInt());
    }

    public void messageConsumer(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientNetworking.handlePacket(this, ctx.get());
        });
        ctx.get().setPacketHandled(true);
    }
}

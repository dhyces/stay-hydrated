package dhyces.stayhydrated;

import dhyces.stayhydrated.handlers.ThirstCapability;
import dhyces.stayhydrated.client.ThirstOverlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ForgeStayHydratedClient {
    static void clientInit(IEventBus modBus) {
        modBus.addListener(ForgeStayHydratedClient::addOverlay);
    }

    private static void addOverlay(RegisterGuiOverlaysEvent event) {
         event.registerAbove(new ResourceLocation("food_level"), ThirstCapability.ID.getPath(), new ThirstOverlay());
    }
}

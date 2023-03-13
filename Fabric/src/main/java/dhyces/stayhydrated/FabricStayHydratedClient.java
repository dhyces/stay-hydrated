package dhyces.stayhydrated;

import com.mojang.blaze3d.vertex.PoseStack;
import dhyces.stayhydrated.client.ThirstGui;
import dhyces.stayhydrated.mixinutil.GuiAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;

public class FabricStayHydratedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(this::renderThirstOverlay);
    }

    private void renderThirstOverlay(PoseStack poseStack, float partialTick) {
        GuiAccessor accessor = (GuiAccessor) Minecraft.getInstance().gui;
        ThirstGui.INSTANCE.renderThirstOverlay(poseStack, partialTick, accessor.getScreenWidth(), accessor.getScreenHeight(), 0, accessor.getRightHeight());
    }
}

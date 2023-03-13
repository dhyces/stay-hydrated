package dhyces.stayhydrated.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dhyces.stayhydrated.handlers.ThirstCapability;
import dhyces.stayhydrated.handlers.ThirstHandler;
import dhyces.stayhydrated.StayHydrated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.common.util.LazyOptional;

public class ThirstOverlay implements IGuiOverlay {
    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        ThirstGui.INSTANCE.renderThirstOverlay(poseStack, partialTick, screenWidth, screenHeight, gui.leftHeight, gui.rightHeight);
        gui.rightHeight += ThirstGui.INSTANCE.getNumRows() * 10;
    }
}
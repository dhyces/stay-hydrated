package dhyces.stayhydrated.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dhyces.stayhydrated.StayHydrated;
import dhyces.stayhydrated.handlers.ThirstHandler;
import dhyces.stayhydrated.services.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public class ThirstGui {
    public static final ResourceLocation THIRST_OVERLAY = StayHydrated.id("textures/gui/thirst.png");
    public static final ThirstGui INSTANCE = new ThirstGui();

    private int rows = 1;
    private ThirstGui() {}

    public void renderThirstOverlay(PoseStack poseStack, float partialTick, int screenWidth, int screenHeight, int leftHeight, int rightHeight) {
        if (Minecraft.getInstance().gameMode.canHurtPlayer() && Minecraft.getInstance().getCameraEntity() instanceof Player) {
            Optional<ThirstHandler> thirstLazy = Services.PLATFORM_HELPER.getHandler(Minecraft.getInstance().player);
            if (thirstLazy.isPresent()) {
                ThirstHandler handler = thirstLazy.get();
                int currentThirst = handler.get() >> 1;
                int oddBit = (handler.get()) & 1;
                int right = screenWidth / 2 + 90;
                int y = screenHeight - rightHeight + 10;
                int x = right;

                RenderSystem.enableBlend();
                RenderSystem.disableDepthTest();
                RenderSystem.setShaderTexture(0, THIRST_OVERLAY);
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                int max = handler.getMax() >> 1;
                float numRowsRaw = max / 10f;
                int numRows = (int) numRowsRaw;
                rows = numRows == numRowsRaw ? numRows : numRows + 1;
                for (int i = 0; i < max; i++) {
                    if (i % 10 != 0) {
                        x -= 8;
                    } else {
                        x = right - 8;
                        y -= 10;
                    }
                    renderBase(poseStack, x, y);
                    if (i < currentThirst) {
                        renderFull(poseStack, x, y);
                    } else if (i == currentThirst && oddBit == 1) {
                        renderHalf(poseStack, x, y);
                    }
                }
            }
        }
    }

    public int getNumRows() {
        return rows;
    }

    private void renderBase(PoseStack poseStack, int x, int y) {
        GuiComponent.blit(poseStack, x, y, 0, 0, 9, 9, 32, 32);
    }

    private void renderFull(PoseStack poseStack, int x, int y) {
        GuiComponent.blit(poseStack, x, y, 9, 0, 9, 9, 32, 32);
    }

    private void renderHalf(PoseStack poseStack, int x, int y) {
        GuiComponent.blit(poseStack, x, y, 18, 0, 9, 9, 32, 32);
    }
}

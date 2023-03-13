package dhyces.stayhydrated.mixin.client;

import dhyces.stayhydrated.client.ThirstGui;
import dhyces.stayhydrated.mixinutil.GuiAccessor;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Gui.class)
public abstract class GuiMixin implements GuiAccessor {

    @Shadow private int screenHeight;
    @Unique private int rightHeight;

    @Accessor
    public abstract int getScreenWidth();

    @Accessor
    public abstract int getScreenHeight();

    @Override
    public int getRightHeight() {
        return rightHeight;
    }

    @ModifyVariable(method = "renderPlayerHealth", ordinal = 10, at = @At(value = "STORE", ordinal = 0))
    private int stayhydrated$moveBubblesUp(int value) {
        rightHeight = screenHeight - value;
        return value - ThirstGui.INSTANCE.getNumRows() * 10;
    }
}

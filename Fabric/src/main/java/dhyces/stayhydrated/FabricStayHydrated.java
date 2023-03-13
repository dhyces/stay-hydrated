package dhyces.stayhydrated;

import net.fabricmc.api.ModInitializer;

public class FabricStayHydrated implements ModInitializer {
    @Override
    public void onInitialize() {
        StayHydrated.init();
    }
}

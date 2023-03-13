package dhyces.stayhydrated.services;

import dhyces.stayhydrated.services.helpers.PlatformHelper;

import java.util.ServiceLoader;

public class Services {

    public static final PlatformHelper PLATFORM_HELPER = loadService(PlatformHelper.class);

    public static <T> T loadService(Class<T> t) {
        return ServiceLoader.load(t).findFirst().get();
    }
}

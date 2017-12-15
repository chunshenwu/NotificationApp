package widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;

import java.util.List;

/**
 * Created by jason on 17-12-14.
 */

public class WidgetControl {
    private static String[] sName = {Widget1x1.class.getCanonicalName(), Widget2x2.class.getName(), Widget3x3.class.getName(), Widget4x4.class.getName()};
    private static String sPackageName;
    private static WidgetControl self = null;

    private static PackageManager sPackageManager;

    final private Context mContext;

    public static WidgetControl getIns(Context context) {
        if (self == null) {
            self = new WidgetControl(context);
        }
        return self;
    }

    private WidgetControl(Context context) {
        mContext = context;
        sPackageName = context.getPackageName();
        sPackageManager = context.getApplicationContext().getPackageManager();
    }

    public boolean isAnyEnable() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
        List<AppWidgetProviderInfo> AppWidgetProviderInfoList = appWidgetManager.getInstalledProviders();
        for (AppWidgetProviderInfo api : AppWidgetProviderInfoList) {
            if (sPackageName.equals(api.provider.getPackageName())) {
                if (sPackageManager.getComponentEnabledSetting(api.provider) == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void enable(final Context context) {
//        set(context, true);
        for (String str : sName) {
            setComponentState(context, sPackageName, str, true);
        }
    }

    public static void disable(final Context context) {
//        set(context, false);
        for (String str : sName) {
            setComponentState(context, sPackageName, str, false);
        }
    }

    private static void set(final Context context, boolean enable) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        List<AppWidgetProviderInfo> AppWidgetProviderInfoList = appWidgetManager.getInstalledProviders();
        for (AppWidgetProviderInfo api : AppWidgetProviderInfoList) {
            if (sPackageName.equals(api.provider.getPackageName())) {
                setComponentState(api.provider, enable);
            }
        }
    }

    private static void setComponentState(ComponentName componentName, boolean enabled) {
        int state = enabled ?  PackageManager.COMPONENT_ENABLED_STATE_ENABLED :  PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        sPackageManager.setComponentEnabledSetting(componentName, state, 0);

    }

    public static void setComponentState(Context context, String packageName , String componentClassName, boolean enabled) {
        PackageManager pm  = context.getApplicationContext().getPackageManager();
        ComponentName componentName = new ComponentName(packageName, componentClassName);
        int state = enabled ?  PackageManager.COMPONENT_ENABLED_STATE_ENABLED :  PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        pm.setComponentEnabledSetting(componentName, state, 0);
    }

}

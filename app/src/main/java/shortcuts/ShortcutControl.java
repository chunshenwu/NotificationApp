package shortcuts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.widget.Toast;

import com.example.jason.notifyapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jason on 17-12-14.
 */

public class ShortcutControl {

    private static ShortcutControl self = null;

    private Context mContext;
    private ShortcutManager sShortcutManager;

    public static ShortcutControl getIns(Context context) {
        if (self == null) {
            self = new ShortcutControl(context);
        }
        return self;
    }

    private ShortcutControl(Context context) {
        mContext = context;
        sShortcutManager = context.getSystemService(ShortcutManager.class);
    }

    int[] icons  = {android.R.drawable.ic_media_previous, android.R.drawable.ic_media_play,
        android.R.drawable.ic_media_next, android.R.drawable.ic_media_pause, android.R.drawable.ic_media_ff};

    public String addShortcuts() {
        List<ShortcutInfo> oldList = sShortcutManager.getDynamicShortcuts();

        int index = oldList.size() + 1;

        if (index >= sShortcutManager.getMaxShortcutCountPerActivity()) {
            Toast.makeText(mContext, "Too many shortcut.", Toast.LENGTH_SHORT).show();
            return "Too many shortcut.";
        }
        ArrayList<ShortcutInfo> newList = new ArrayList<>();
        String id = "";
        for (int i = 0 ; i < index ; i++) {
            id = "Shortcuts : " + i;
            ShortcutInfo shortcut = new ShortcutInfo.Builder(mContext, id)
                .setShortLabel(id)
                .setLongLabel("LongLabel" + id)
                .setIcon(Icon.createWithResource(mContext, icons[i]))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mi.com/")))
                .build();
            newList.add(shortcut);
        }

        sShortcutManager.removeAllDynamicShortcuts();
        sShortcutManager.setDynamicShortcuts(newList);
        sShortcutManager.getDynamicShortcuts().size();
        return id;
    }

    public void clear() {
        sShortcutManager.removeAllDynamicShortcuts();
    }
}

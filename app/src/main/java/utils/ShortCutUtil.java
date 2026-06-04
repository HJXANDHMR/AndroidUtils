package utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import com.example.hjx.androidutils.R;

/**
 * Create and delete shortcut icons
 *
 * Required permissions: com.android.launcher.permission.INSTALL_SHORTCUT
 *          com.android.launcher.permission.UNINSTALL_SHORTCUT
 */
public final class ShortCutUtil {

    /**
     * Don't let anyone instantiate this class.
     */
    private ShortCutUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * Check if a shortcut exists
     *
     * @param activity Activity
     * @return whether a desktop icon exists
     */
    public static boolean hasShortcut(Activity activity) {
        boolean isInstallShortcut = false;
        final ContentResolver cr = activity.getContentResolver();
        final String AUTHORITY = "com.android.launcher.settings";
        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI,
                new String[]{"title", "iconResource"}, "title=?",
                new String[]{activity.getString(R.string.app_name).trim()},
                null);
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }
        return isInstallShortcut;
    }

    /**
     * Create a desktop shortcut for the app
     *
     * @param activity Activity
     * @param res     res
     */
    public static void addShortcut(Activity activity,int res) {

        Intent shortcut = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // Shortcut name
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                activity.getString(R.string.app_name));
        // Do not allow duplicate creation
        shortcut.putExtra("duplicate", false);
        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
        shortcutIntent.setClassName(activity, activity.getClass().getName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // Shortcut icon
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(
                activity, res);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        activity.sendBroadcast(shortcut);
    }

    /**
     * Delete the app's shortcut
     *
     * @param activity Activity
     */
    public static void delShortcut(Activity activity) {

        Intent shortcut = new Intent(
                "com.android.launcher.action.UNINSTALL_SHORTCUT");
        // Shortcut name
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                activity.getString(R.string.app_name));
        String appClass = activity.getPackageName() + "."
                + activity.getLocalClassName();
        ComponentName comp = new ComponentName(activity.getPackageName(),
                appClass);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(
                Intent.ACTION_MAIN).setComponent(comp));
        activity.sendBroadcast(shortcut);
    }
}


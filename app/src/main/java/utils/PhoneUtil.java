package utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.File;

/**
 * Phone component utility class
 *
 * @author
 */
public final class PhoneUtil {
    private static long lastClickTime;
    /**
     * Don't let anyone instantiate this class.
     */
    private PhoneUtil() {
        throw new Error("Do not need instantiate!");
    }


    /**
     * Launch the system SMS interface
     *
     * @param activity    Activity
     * @param phoneNumber phone number
     * @param smsContent  SMS content
     */
    public static void sendMessage(Context activity, String phoneNumber, String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsContent);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(it);
    }


    /**
     * Check if it is a double-click
     *
     * @return  boolean
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * Get phone model
     *
     * @param context  context
     * @return   String
     */
    public static String getMobileModel(Context context) {
        try {
            // Phone model
            String model = android.os.Build.MODEL; 
            return model;
        } catch (Exception e) {
            return "Unknown";
        }
    }

    /**
     * Get phone brand
     *
     * @param context  context
     * @return  String
     */
    public static String getMobileBrand(Context context) {
        try {
            // Android system version
            String brand = android.os.Build.BRAND; 
            return brand;
        } catch (Exception e) {
            return "Unknown";
        }
    }


    /**
     * Take a photo and open the camera
     * @param requestcode   request code
     * @param activity   context
     * @param fileName    path of the generated image file
     */
    public static void toTakePhoto(int requestcode, Activity activity,String fileName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("camerasensortype", 2);// Use front camera
        intent.putExtra("autofocus", true);// Auto focus
        intent.putExtra("fullScreen", false);// Full screen
        intent.putExtra("showActionIcons", false);
        try {// Create a file with the current task ID to store the photo path! The file name uses UUID and the task ID will be used later to query the path!
            File file = new File(fileName);
            if(!file.exists()){// Create the directory if it does not exist
                file.mkdirs();
            }
            Uri uri = Uri.fromFile(new File(fileName));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(intent, requestcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Open photo gallery
     * @param requestcode  response code
     * @param activity  context
     */
    public static void toTakePicture(int requestcode, Activity activity){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        activity.startActivityForResult(intent, requestcode);
    }
}

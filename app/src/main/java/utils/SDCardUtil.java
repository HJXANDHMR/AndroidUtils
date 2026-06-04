package utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * SD card helper class
 */
public class SDCardUtil {

    private SDCardUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Check if SDCard is available
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    /**
     * Get SD card path
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * Get remaining capacity of SD card in bytes
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // Get the number of available data blocks
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // Get the size of a single data block (bytes)
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * Get remaining available capacity in bytes at the specified path
     *
     * @param filePath
     * @return capacity in bytes, SDCard available space, internal storage available space
     */
    public static long getFreeBytes(String filePath)
    {
        // If the path is under SD card, get SD card available capacity
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {
        // If the path is internal storage, get internal storage available capacity
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * Get system storage path
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

}

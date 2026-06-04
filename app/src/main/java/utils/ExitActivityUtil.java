package utils;


import android.app.Activity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * detailed information http://hjxandhmr.github.io/2016/06/29/Android-Elegance-ExitActivity/
 */
public class ExitActivityUtil extends Activity {

    private long exitTime = 0;

    //Override onKeyDown method
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //Press back again within two seconds to exit
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

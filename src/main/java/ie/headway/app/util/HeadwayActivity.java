package ie.headway.app.util;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

/**
 * Base activity for all activities in a Headway app. Every activity in every Headway project should
 * extend this class.
 * */
public class HeadwayActivity extends Activity {

  private static final String TAG = "HeadwayActivity";

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    emptyTmpDir();
  }

  /**
   * Empties the tmp directory for all Headway apps as defined by {@link AppDir#TMP}.
   * */
  private void emptyTmpDir() {
    try {
      AppDir.TMP.empty();
    } catch (IOException e) {
      Log.w(TAG, "couldn't empty tmp dir at " + AppDir.TMP.getPath());
    }
  }

}

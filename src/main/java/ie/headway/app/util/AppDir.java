package ie.headway.app.util;

import android.os.Environment;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public enum AppDir {

  ROOT(Environment.getExternalStorageDirectory() + File.separator + "Headway") {
    @Override
    public void empty() throws IOException {
      throw new UnsupportedOperationException("cannot empty ROOT");
    }
  },

  TMP(ROOT.getPath(".tmp")) {
    @Override
    public void empty() throws IOException {
      FileUtils.cleanDirectory(getFile());
    }
  };

  private final String mPath;

  AppDir(String path) {
    mPath = path;
  }

  public abstract void empty() throws IOException;

  /**
   * Create any directories required by the app which do not currently exist.
   */
  public static void makeAppDirs() {
    for (final AppDir appDir : values()) {
      if (!appDir.getFile().exists()) {
        if (!appDir.getFile().mkdirs()) {    //mkdirs doesn't throw anything if it fails.
          throw new RuntimeException("Couldn't create " + appDir.getFile());
        }
      }
    }
  }

  /**
   * Returns a path with this AppDir as the parent to all the specified {@code children},
   * e.g ROOT.getPath("tmp", "64") would return a path ROOT/tmp/64, where ROOT is itself a path.
   *
   * @param children The child directories of the current AppDir.
   * @return The path.
   */
  public String getPath(CharSequence... children) {
    final StringBuilder pathBuilder = new StringBuilder(mPath);
    for (CharSequence child : children) {
      pathBuilder.append(File.separator + child);
    }
    return pathBuilder.toString();
  }

  /**
   * Returns a file with this AppDir as the parent to all the specified {@code children},
   * e.g ROOT.getFile("tmp", "64") would return a file representing the path ROOT/tmp/64,
   * where ROOT does itself represent a path.
   *
   * @param children The child directories of the current AppDir.
   * @return The file representing the new path.
   */
  public File getFile(CharSequence... children) {
    return new File(getPath(children));
  }

}

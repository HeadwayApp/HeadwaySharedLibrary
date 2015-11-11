package ie.headway.app.xml;

import android.os.Environment;

import java.io.File;

public class PortableStep extends Step {

  /**
   * This constant represents the system dependant path element which PortableStep will resolve.
   */
  public static final String PATH_ARTIFACT = "EXTERNAL_STORAGE_DIRECTORY";

  public PortableStep() {
  }

  public PortableStep(String text, String imagePath, String audioPath) {
    super(text, artifisePath(imagePath), artifisePath(audioPath));
  }

  @Override
  public String getImagePath() {
    final String artifisedPath = super.getImagePath();
    return normalisePath(artifisedPath);
  }

  @Override
  public String getAudioPath() {
    final String artifisedPath = super.getAudioPath();
    return normalisePath(artifisedPath);
  }

  private static String artifisePath(final String normalisedPath) {
    final File extStorage = Environment.getExternalStorageDirectory();
    final String extStoragePath = extStorage.getAbsolutePath();
    return normalisedPath.replace(extStoragePath, PATH_ARTIFACT);
  }

  private static String normalisePath(final String artifisedPath) {
    final File extStorage = Environment.getExternalStorageDirectory();
    final String extStoragePath = extStorage.getAbsolutePath();
    return artifisedPath.replace(PATH_ARTIFACT, extStoragePath);
  }

}

package ie.headway.app.xml.step;

import android.os.Environment;

import java.io.File;

/**
 * Enum used to define all the artefacts used by {@link PortableStep}.
 * */
enum Artefact {

  /**
   * This constant represents the value returned by {@link Environment#getExternalStorageDirectory()}
   */
  EXT_STRG("EXTERNAL_STORAGE_DIRECTORY") {
    @Override
    public String artefisePath(final String path) {
      final File extStorage = Environment.getExternalStorageDirectory();
      final String extStoragePath = extStorage.getAbsolutePath();
      return path.replace(extStoragePath, getArtefactStr());
    }

    @Override
    public String normalisePath(String path) {
      final File extStorage = Environment.getExternalStorageDirectory();
      final String extStoragePath = extStorage.getAbsolutePath();
      return path.replace(getArtefactStr(), extStoragePath);
    }
  };

  private final String artefactStr;

  Artefact(final String stringValue) {
    artefactStr = stringValue;
  }

  String getArtefactStr() {
    return artefactStr;
  }

  public abstract String artefisePath(final String path);

  public abstract String normalisePath(final String path);

}

package ie.headway.app.xml;

import android.os.Environment;

/**
 * A {@link Step} which can be serialised/deserialised across different Android devices by use of
 * "artefacts", whereby certain non portable components of the data contained within the step is automatically
 * replaced with an artefact before serialization and resolved at runtime on the user device after deserialization.
 *
 * e.g. The root external storage directory of a device which is retrieved by calling {@link Environment#getExternalStorageDirectory()}
 * differs between Android devices, therefore the literal value returned by this method will not always
 * work if written to XML and used by another device, therefore when a portable step is serialized to XML
 * the portion of any path which corresponds to {@link Environment#getExternalStorageDirectory()} is automatically replaced
 * with the string defined in {@link Artefact#EXT_STRG}, and equally so when the step is deserialised on
 * another device {@link Artefact#EXT_STRG} is replaced with the value returned from {@link Environment#getExternalStorageDirectory()}
 * when it is being used at runtime.
 * */
public class PortableStep extends Step {

  public PortableStep() {
  }

  public PortableStep(String text, String imagePath, String audioPath) {
    super(text, artefisePath(imagePath), artefisePath(audioPath));
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

  /**
   * Replace any part of the path which corresponds to an {@link Artefact} with that artefact.
   * */
  private static String artefisePath(final String normalisedPath) {
    String arteficationBuffer = normalisedPath;
    for(Artefact artefact: Artefact.values()) {
      arteficationBuffer = artefact.artefisePath(arteficationBuffer);
    }

    return arteficationBuffer;
  }

  /**
   * Replace any artefact in the path with the appropriate value.
   * */
  private static String normalisePath(final String artifisedPath) {
    String normalisationBuffer = artifisedPath;
    for(Artefact artefact: Artefact.values()) {
      normalisationBuffer = artefact.normalisePath(normalisationBuffer);
    }

    return normalisationBuffer;
  }

}

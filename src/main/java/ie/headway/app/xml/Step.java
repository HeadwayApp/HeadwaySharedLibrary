package ie.headway.app.xml;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;

import java.io.File;

public class Step implements Parcelable {

  /**
   * TODO Does SimpleXML need these to be non-final?
   */
  @Attribute private String text;
  @Attribute private String imagePath;
  @Attribute private String audioPath;

  /**
   * TODO Does SimpleXML need this constructor to be public? Can it be private?
   */
  public Step() {
  }

  public Step(String text, String imagePath, String audioPath) {
    this.text = text;
    this.imagePath = imagePath;
    this.audioPath = audioPath;
  }

  /**
   * TODO Can't be private because compiler error occurs. I don't remember this happening before,
   * this should be investigated further. The API example code has the ctor as private aswell.
   */
  Step(Parcel in) {
    this(in.readString(),
        in.readString(),
        in.readString());
  }

  public String getText() {
    return text;
  }

  public String getImagePath() {
    return imagePath;
  }

  public String getAudioPath() {
    return audioPath;
  }

  @Override
  public String toString() {
    return text + " : " + new File(imagePath).getName() + " : " + new File(audioPath).getName();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(text);
    dest.writeString(imagePath);
    dest.writeString(audioPath);
  }

  public static final Creator<Step> CREATOR = new Creator<Step>() {
    public Step createFromParcel(Parcel source) {
      return new Step(source);
    }

    public Step[] newArray(int size) {
      return new Step[size];
    }
  };

}

package ie.headway.app.xml.task;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ie.headway.app.util.AppDir;
import ie.headway.app.xml.step.PortableStep;
import ie.headway.app.util.RequiresDirs;
import ie.headway.app.xml.step.Step;

@Root
public class Task implements Parcelable, RequiresDirs {

  @Attribute
  private String name;
  /**
   * TODO: This madness completely violates all kinds of OOP. This source must be modified in order for
   * anyone to subclass {@link Step} or {@link PortableStep}. Just make the list only of type Step and cast
   * any deserialised Step to whatever it's meant to be after deserialisation, you swine!
   * */
  @ElementListUnion({
      @ElementList(entry = "step", inline = true, type = Step.class),
      @ElementList(entry = "portable_step", inline = true, type = PortableStep.class)
  })
  private List<Step> steps;

  /**
   * TODO: Does this constructor have to be {@code public},
   * set it to the most restricted possible access modifier that still works with Simple.
   */
  public Task() {
  }

  public Task(final String name, final List<Step> steps) {
    this.name = name;
    this.steps = steps;
  }

  public Task(final Parcel in) {
    name = in.readString();
    steps = retrieveStepsFromParcel(in);
  }

  public String getName() {
    return name;
  }

  public void addStep(final Step step) {
    steps.add(step);
  }

  public Step getStep(final int index) {
    return steps.get(index);
  }

  public List<Step> getSteps() {
    return steps;
  }

  public int getStepCount() {
    return steps.size();
  }

  public File getTaskXmlFile() {
    final File taskFile = AppDir.ROOT.getFile(name, "task.xml");
    return taskFile;
  }

  public boolean doesTaskExist() {
    final File taskXmlFile = getTaskXmlFile();
    return taskXmlFile.exists();
  }

  public static Task newInstance(final String taskName) throws TaskAlreadyExistsException {
    final List<Step> stepsLst = new ArrayList<>(10);
    final Task task = new Task(taskName, stepsLst);
    final boolean taskExists = task.doesTaskExist();
    if(taskExists) throw new TaskAlreadyExistsException("task " + taskName + "already exists");
    return task;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(final Parcel dest, final int flags) {
    dest.writeString(name);
    dest.writeList(steps);
  }

  public static final Creator<Task> CREATOR = new Creator<Task>() {
    public Task createFromParcel(final Parcel source) {
      return new Task(source);
    }

    @Override
    public Task[] newArray(final int size) {
      return new Task[size];
    }
  };

  @Override
  public String toString() {
    return name;
  }

  @Override
  public void makeRequiredDirs() {
    final String taskName = getName();
    final File taskImgDirectory = AppDir.ROOT.getFile(taskName, "imgs");
    final boolean dirsAlreadyExist = taskImgDirectory.exists();
    final boolean wasSuccessful = taskImgDirectory.mkdirs();
    if (!dirsAlreadyExist && !wasSuccessful) {
      throw new RuntimeException("Couldn't make task directory: " + taskImgDirectory + " for task: " + this);
    }
  }

  private static List<Step> retrieveStepsFromParcel(final Parcel in) {
    final List<Step> unmarshalledSteps = new ArrayList<>(10);
    final ClassLoader classLoader = Task.class.getClassLoader();
    in.readList(unmarshalledSteps, classLoader);
    return unmarshalledSteps;
  }

}

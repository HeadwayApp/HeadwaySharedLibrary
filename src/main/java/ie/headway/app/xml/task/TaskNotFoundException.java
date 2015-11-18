package ie.headway.app.xml.task;

public class TaskNotFoundException extends TaskException {

  public TaskNotFoundException() {

  }

  public TaskNotFoundException(final String detailMessage) {
    super(detailMessage);
  }

  public TaskNotFoundException(final String detailMessage, final Throwable throwable) {
    super(detailMessage, throwable);
  }

  public TaskNotFoundException(final Throwable throwable) {
    super(throwable);
  }

}

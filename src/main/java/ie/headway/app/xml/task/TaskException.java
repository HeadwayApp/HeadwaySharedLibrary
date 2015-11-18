package ie.headway.app.xml.task;

public class TaskException extends Exception {

  public TaskException() {

  }

  public TaskException(final String detailMessage) {
    super(detailMessage);
  }

  public TaskException(final String detailMessage, final Throwable throwable) {
    super(detailMessage, throwable);
  }

  public TaskException(final Throwable throwable) {
    super(throwable);
  }

}

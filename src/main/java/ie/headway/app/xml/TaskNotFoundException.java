package ie.headway.app.xml;

public class TaskNotFoundException extends Exception {

  public TaskNotFoundException() {

  }

  public TaskNotFoundException(String detailMessage) {
    super(detailMessage);
  }

  public TaskNotFoundException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public TaskNotFoundException(Throwable throwable) {
    super(throwable);
  }

}

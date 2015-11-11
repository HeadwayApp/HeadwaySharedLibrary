package ie.headway.app.xml;

import java.io.File;
import java.util.concurrent.TimeoutException;

public class LatentStep extends PortableStep {

  public LatentStep() {

  }

  public LatentStep(String text, String imagePath, String audioPath) {
    super(text, imagePath, audioPath);
    final File imgFile = new File(imagePath);
//    final File audFile = new File(audioPath);
    final int loopTimOut = 4;
    int loopCnt = 0;
    while(!imgFile.exists()) { //&& audFile.exists()
      try {
        Thread.sleep(1000);
        loopCnt++;
      } catch (InterruptedException ie) {
        loopCnt--;
      }
      if(loopCnt == loopTimOut) {
        throw new RuntimeException(
            new TimeoutException("latent step timed out waiting for " + imagePath + " to exist"));
      }
    }
  }

}

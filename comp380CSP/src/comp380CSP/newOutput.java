package comp380CSP;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

//to redirect console output to JTextArea
class newOutput extends OutputStream {
  private JTextArea textArea;

  public newOutput(JTextArea textArea) {
      this.textArea = textArea;
  }

  @Override
  public void write(int b) throws IOException {
      // Redirect data to the text area
      textArea.append(String.valueOf((char) b));
      textArea.setCaretPosition(textArea.getDocument().getLength());
  }
}


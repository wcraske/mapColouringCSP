package comp380CSP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class gui extends JFrame implements ActionListener {
    JLabel label;
    JButton button;
    JTextArea logArea;
    JScrollPane scrollPane;
    graph canadaGraph;

    public gui() {
        //set up JFrame
        setTitle("gui");
        setSize(800, 800); //window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        label = new JLabel("Which country?");
        add(label);

        button = new JButton("Canada");
        button.addActionListener(this);
        add(button);

        logArea = new JTextArea(30, 60);
        logArea.setEditable(false);
        scrollPane = new JScrollPane(logArea);
        add(scrollPane);

        //initialize graph object to run code in graph.java
        int numProvinces = 13;
        canadaGraph = new graph(numProvinces);
        graph.setData(canadaGraph);
        
    }

    public void actionPerformed(ActionEvent e) {//button for canada
        if (e.getSource() == button) {
            //clear log area
            logArea.setText(""); //set text area to nothin

            //define available colors for provinces
            String[] availableColors = { "Red", "Green", "Blue" };

            //capture console output 
            PrintStream printStream = new PrintStream(new newOutput(logArea));
            System.setOut(printStream);
            System.setErr(printStream);

       
            canadaGraph.colorProvinces(availableColors);
        }
    }

    public static void main(String[] args) {
        //shows the gui
        SwingUtilities.invokeLater(() -> {
            gui visualizer = new gui();
            visualizer.setVisible(true);
        });
    }
}


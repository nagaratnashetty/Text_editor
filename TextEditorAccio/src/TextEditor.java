import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring the properties of text editor;
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    //File menu items
    JMenuItem newFile, openFile, saveFile;
    //Edit items
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor()
    {// Initialize frame
        frame=new JFrame();

        //Initialize member
        menuBar=new JMenuBar();
         //Initialize text Area
        textArea=new JTextArea();
        // Initialize menus
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //Initialize file menu items
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("save File");
        //add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize edit menu
        cut=new JMenuItem("cut");
        copy=new JMenuItem("copy");
        paste=new JMenuItem("paste");
        selectAll=new JMenuItem("select All");
        close=new JMenuItem("close");

        //adding action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        //add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menus to menubar(that is file and edit)
        menuBar.add(file);
        menuBar.add(edit);

        //set members to frame
        frame.setJMenuBar(menuBar);
         //create Content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add the text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create a scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scrll pane to panel
            panel.add(scrollPane);
            //Add panel to frame
        frame.add(panel);
//        //Add text Area to frame
//        frame.add(textArea);

        //set dimention
        frame.setBounds(100,100,400,400);// x and y denotes the location on screen
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
   public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource()==cut)
        {
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy)
        {
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste)
        {
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll)
        {
            //perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close)
        {
            //perform close
            System.exit(0);//it exits from the console window,0 eans we have just closed the code
        }
        if(actionEvent.getSource()==openFile)
        {
            //open a file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                //getting selected file
                File file= fileChooser.getSelectedFile();
                //Get the path of selected file
                String filepath=file.getPath();
                try
                {
                    //Initialize file reader
                    FileReader fileReader=new FileReader(filepath);
                    //initialize buffer reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";
                    //read contents of file line by line

                    while((intermediate= bufferedReader.readLine())!=null)
                    {
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile)
        {//initialize file picker
            JFileChooser fileChooser=new JFileChooser("c:");
            //get choose option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);
            //Check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                //Creat a new file with choosen directory path and file name
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+"txt");
                try {
                    //initialize file writer
                    FileWriter fileWriter=new FileWriter(file);
                    //initialize buffer reader
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    //write contents of text area
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();

                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile)
        {
            TextEditor newTextEditor=new TextEditor();
        }
    }
    public static void main(String[] args)
    {
      TextEditor textEditor = new TextEditor();

    }
}
package super_simple_calculator;

//import awt packages
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import swing pacakges
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

//GUI CLASS.. this is the user interface that the user will see. Implements ActionListener to listen for events.
public class gui implements ActionListener{
    
    //instance variables
    private final JFrame CalFrame;
    private final JPanel CalPanel;
    private final JTextArea CalText;
    
    //button instance variables.. we give each element a name so we can find it easier
    private final JButton CalBtn[],btnAdd, btnMinus, btnMultiply, btnDivide, btnEquals, btnClear,
                    btnSR, btnS, BtnODB, btnCos, btnSin, btnTan;
    
    //instance variable for our cal object.
    private final Cal cal;
    
    //array to hold the values of our buttons
    private final String[] btnValue = {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };
    
    //GUI CONSTRUCTOR.. intialize instance variables and get gui ready
    public gui(){
        CalFrame = new JFrame("Simple Calculator Project For Fun");
        CalFrame.setResizable(false); //Calculator size cannot be changed.
        
        CalPanel = new JPanel(new FlowLayout()); //using FlowLayout layout manager to set where components are placed
        
        CalText = new JTextArea(2, 25); //2 rows 25 colums
        
        CalBtn = new JButton[10];
        
        //Loop through and create buttons giving each button a name
        for(int x = 0; x < 10; x++){
            CalBtn[x] = new JButton(String.valueOf(x));
        }
        
        //Instantiate operator buttons
        btnAdd = new JButton("+");
        btnMinus = new JButton("-");
        btnMultiply = new JButton("x");
        btnDivide = new JButton("/");
        btnEquals = new JButton("=");
        btnSR = new JButton("√"); //square root button
        btnS = new JButton("x2");
        BtnODB = new JButton("1/x");
        btnSin = new JButton("sin");
        btnCos = new JButton("cos");
        btnTan = new JButton("tan");
        btnClear = new JButton("C");
        
        //Create new cal object
        cal = new Cal();
    }
    
    //RUN METHOD.. set up GUI.. add buttons panels etc
    public void run(){
        CalFrame.setVisible(true);
        CalFrame.setSize(350, 280);
        CalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CalFrame.add(CalPanel); //Add panel to frame
        
        //Add text to panel
        CalPanel.add(CalText);
        
        //Add number buttons to panel and attach an ActionListener;
        for(int x = 0; x < 10; x++){
            CalPanel.add(CalBtn[x]);
            CalBtn[x].addActionListener(this);
        }
        
        
        //Add all operator buttons to panel
        CalPanel.add(btnAdd);
        CalPanel.add(btnMinus);
        CalPanel.add(btnMultiply);
        CalPanel.add(btnDivide);
        CalPanel.add(btnS);
        CalPanel.add(btnSR);
        CalPanel.add(BtnODB);
        CalPanel.add(btnSin);
        CalPanel.add(btnCos);
        CalPanel.add(btnTan);
        CalPanel.add(btnEquals);
        CalPanel.add(btnClear);
        
        //Add action listeners.. we will use the event that happens on the JFrame
        btnAdd.addActionListener(this);
        btnMinus.addActionListener(this);
        btnMultiply.addActionListener(this);
        btnDivide.addActionListener(this);
        btnS.addActionListener(this);
        btnSR.addActionListener(this);
        BtnODB.addActionListener(this);
        btnSin.addActionListener(this);
        btnCos.addActionListener(this);
        btnTan.addActionListener(this);
        btnEquals.addActionListener(this);
        btnClear.addActionListener(this);
        
        
    }
    
    
    /*
    Override actionPerformed and use the event 
    to get it's source and do differant actions 
    based on where the event happened.
    */
    @Override
    public void actionPerformed(ActionEvent event){
        final Object eventSource = event.getSource(); //gets the location of the event
        
        for(int x = 0; x < 10; x++){
            if(eventSource == CalBtn[x]){
                CalText.replaceSelection(btnValue[x]); //replaces current content with the value from the btnValue array
                return; //Leave the loop
            }
        }
        
        //check if event happend at add button
        if(eventSource == btnAdd){
            calWriter(cal.calculateBasic(Cal.BasicMathOperators.ADD, calReader()));
        }
        
        //check if event happend at mins button
        if(eventSource == btnMinus){
            calWriter(cal.calculateBasic(Cal.BasicMathOperators.MINUS, calReader()));
        }
        
        //check if event happend at multiply button
        if(eventSource == btnMultiply){
            calWriter(cal.calculateBasic(Cal.BasicMathOperators.MULTIPLY, calReader()));
        }
        
        //check if event happend at divide button
        if(eventSource == btnDivide){
            calWriter(cal.calculateBasic(Cal.BasicMathOperators.DIVIDE, calReader()));
        }
        
        //check if event happend at square button
        if(eventSource == btnS){
            calWriter(cal.MoreCalculate(Cal.MoreMathOperators.SQUARE, calReader()));
        }
        
        //check if event happend at square root button
        if(eventSource == btnSR){
                calWriter(cal.MoreCalculate(Cal.MoreMathOperators.SQUAREROOT, calReader()));
        }
        
        //check if event happend at one divde by button
        if(eventSource == BtnODB){
            calWriter(cal.MoreCalculate(Cal.MoreMathOperators.ONEDIVIDEBY, calReader()));
        }
        
        //check if event happend at sin button
        if(eventSource == btnSin){
            calWriter(cal.MoreCalculate(Cal.MoreMathOperators.SIN, calReader()));
        }
        
        //check if event happend at cos button
        if(eventSource == btnCos){
            calWriter(cal.MoreCalculate(Cal.MoreMathOperators.COS, calReader()));
        }
        
        //check if event happend at tan button
        if(eventSource == btnTan){
            calWriter(cal.MoreCalculate(Cal.MoreMathOperators.TAN, calReader()));
        }
        
        //check if event happened on equals button
        if(eventSource == btnEquals){
            calWriter(cal.EqualsCalculate(calReader()));
        }
        
        //check if event happened on clear button
        if(eventSource == btnClear){
            calWriter(cal.reset());
        }
        
        CalText.selectAll(); //selects all text in the CalText JTextArea
    }
    
    //READER METHOD.. gets value in CalText and returns it as double value
    public Double calReader(){
        Double num;
        String s;
        s = CalText.getText(); //gets text in the CalText JTextArea
        num = Double.valueOf(s); //gets double value from the string s and stores it as num
        
        return num;
    }
    
    //CALWRITER METHOD.. gets a number and checks if it is a number and sets the text in CalText.
    public void calWriter(final Double num){
        if(Double.isNaN(num)){
            CalText.setText("");
        }else{
            CalText.setText(Double.toString(num));
        }
    }
}

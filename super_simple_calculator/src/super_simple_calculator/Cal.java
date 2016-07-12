package super_simple_calculator;

import java.text.Normalizer;


public class Cal {
    //instance variables
    private Double num1, num2;
    
    //BASICMATHOPERATORS INNER CLASS.. enum is used as the math operators are constants
    public enum BasicMathOperators{
        NORMAL, ADD, MINUS, MULTIPLY, DIVIDE
    }
    
    //MOREMATHOPERATORS INNER CLASS.. set as type enum as they are constants
    public enum MoreMathOperators{
        SQUARE, SQUAREROOT,ONEDIVIDEBY, COS, TAN, SIN
    }
    
    private BasicMathOperators method = BasicMathOperators.NORMAL;
    
    //BASICCALCULATE METHOD.. cancluates using the basic math operator constants
    private Double BasicCalculate(){
        if(method == BasicMathOperators.NORMAL){
            return num2;
        }else if(method == BasicMathOperators.MULTIPLY){
            return num1 * num2;
        }else if(method == BasicMathOperators.DIVIDE){
            return num1/ num2;
        }else if(method == BasicMathOperators.MINUS){
            return num1 - num2;
        }else if(method == BasicMathOperators.ADD){
            return num1 + num2;
        }else if(method == BasicMathOperators.MINUS){
            return num1 - num2;
        }
        
        //Hopefully will never be reached.
        throw new Error();
    }
    
    //CALCULATEBASIC METHOD.. checks the action the user wants to do and either returns not a number or passes to the BasicCaculate method.
    public Double calculateBasic(BasicMathOperators newMethod, Double num){
        if(method == BasicMathOperators.NORMAL){
            num2 = 0.0;
            num1 = num;
            method = newMethod;
            return Double.NaN; //not a number.. NAN is something that cannot be represented as a number
        }else{
            num2 = num;
            
            num1 = BasicCalculate();
            method = newMethod;
            return num1;
        }
    }
    
    //EQUALSCALCULATE METHOD.. if number is equal
    public Double EqualsCalculate(Double num){
        return calculateBasic(BasicMathOperators.NORMAL, num);
    }
    
    //RESET METHOD.. reset calculator
    public Double reset(){
        num1 = 0.0;
        num2 = 0.0;
        method = BasicMathOperators.NORMAL;
        
        return Double.NaN; //not a number
    }
    
    //MORECALCULATE METHOD.. calculates using the more operator constants.
    public Double MoreCalculate(MoreMathOperators newMethod, Double num){
        if(newMethod == MoreMathOperators.SQUARE){
            return num * num;
        }else if(newMethod == MoreMathOperators.COS){
            return Math.cos(num);
        }else if(newMethod == MoreMathOperators.ONEDIVIDEBY){
            return 1/num;
        }else if(newMethod == MoreMathOperators.SIN){
            return Math.sin(num);
        }else if(newMethod == MoreMathOperators.TAN){
            return Math.tan(num);
        }else if(newMethod == MoreMathOperators.SQUAREROOT){
            return Math.sqrt(num);
        }
        
        //Hopefully will never be reached.
        throw new Error();
    }
}

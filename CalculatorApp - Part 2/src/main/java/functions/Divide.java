package functions;

import interfaces.EvaluateExpression;

public class Divide implements EvaluateExpression {
    
    @Override
    public int evaluateExpression(int firstValue, int secondValue) {
        if (secondValue == 0) {
            return 0;
        }
        
        return firstValue/secondValue;
    }
}

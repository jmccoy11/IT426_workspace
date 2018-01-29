package functions;

import interfaces.EvaluateExpression;

public class Add implements EvaluateExpression {
    
    @Override
    public int evaluateExpression(int firstValue, int secondValue) {
        return firstValue + secondValue;
    }
}

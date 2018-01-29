package functions;

import interfaces.EvaluateExpression;

public class Multiply implements EvaluateExpression {
    
    @Override
    public int evaluateExpression(int firstValue, int secondValue) {
        return firstValue * secondValue;
    }
}

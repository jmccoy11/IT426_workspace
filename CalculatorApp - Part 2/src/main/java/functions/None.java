package functions;

import interfaces.EvaluateExpression;

public class None implements EvaluateExpression {
    
    @Override
    public int evaluateExpression(int firstValue, int secondValue) {
        return 0;
    }
}

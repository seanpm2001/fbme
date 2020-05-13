package smvDebugger.panel.condition;

/*Generated by MPS */


public class ImpossibleComparsionException extends ConditionParseException {
  public ImpossibleComparsionException(final String argument, final String expression) {
    super(String.format("Argument '%s' can't be compared with complex expression '%s'", argument, expression));
  }
}

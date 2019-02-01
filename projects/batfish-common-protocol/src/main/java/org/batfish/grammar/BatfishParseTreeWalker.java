package org.batfish.grammar;

import com.google.common.annotations.VisibleForTesting;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.RuleNode;

public class BatfishParseTreeWalker extends ParseTreeWalker {

  BatfishCombinedParser<? extends BatfishParser, ? extends BatfishLexer> _parser;

  public BatfishParseTreeWalker(
      BatfishCombinedParser<? extends BatfishParser, ? extends BatfishLexer> parser) {
    super();
    _parser = parser;
  }

  public BatfishParseTreeWalker() {
    super();
  }

  @Override
  protected void enterRule(ParseTreeListener listener, RuleNode r) {
    ParserRuleContext ctx = (ParserRuleContext) r.getRuleContext();
    try {
      listener.enterEveryRule(ctx);
      ctx.enterRule(listener);
    } catch (Exception e) {
      throwException(e, ctx);
    }
  }

  @Override
  protected void exitRule(ParseTreeListener listener, RuleNode r) {
    ParserRuleContext ctx = (ParserRuleContext) r.getRuleContext();
    try {
      ctx.exitRule(listener);
      listener.exitEveryRule(ctx);
    } catch (Exception e) {
      throwException(e, ctx);
    }
  }

  @VisibleForTesting
  void throwException(Exception e, ParserRuleContext ctx) {
    int line = ctx.start.getLine();
    // Handle applying line translation if applicable (for flattned files)
    if (_parser != null) {
      line = _parser.getLine(ctx.start);
    }
    throw new BatfishParseException(
        String.format("Exception parsing line %s: %s", line, e.getMessage()),
        e.getCause(),
        line,
        ctx.getText());
  }
}

// Parser rule:
// expression :
//   ...
//   | 'this' {
//       $n = new This($ctx);
//   }

package edu.westminstercollege.cmpt355.minijava.node;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.List;


public record This(ParserRuleContext ctx) implements Expression {

    @Override
    public List<Node> children() {
        return List.of();
    }
}
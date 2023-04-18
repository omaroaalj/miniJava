// Generated from java-escape by ANTLR 4.11.1
package edu.westminstercollege.cmpt355.minijava;

import edu.westminstercollege.cmpt355.minijava.node.*;
import edu.westminstercollege.cmpt355.minijava.*;
import java.util.Optional;
import java.util.ArrayList;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MiniJavaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, RESERVED_WORD=27, WHITESPACE=28, INT=29, DOUBLE=30, 
		BOOLEAN=31, NAME=32, STRING=33, LINE_COMMENT=34, BLOCK_COMMENT=35;
	public static final int
		RULE_goal = 0, RULE_classNode = 1, RULE_imp = 2, RULE_field = 3, RULE_method = 4, 
		RULE_main = 5, RULE_methodBody = 6, RULE_parameter = 7, RULE_statement = 8, 
		RULE_declaration = 9, RULE_decItem = 10, RULE_expression = 11, RULE_type = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"goal", "classNode", "imp", "field", "method", "main", "methodBody", 
			"parameter", "statement", "declaration", "decItem", "expression", "type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'import'", "'.'", "';'", "'*'", "'='", "'('", "','", "')'", "'{'", 
			"'}'", "'void'", "'main'", "'return'", "'while'", "'_print'", "'this'", 
			"'new'", "'++'", "'--'", "'+'", "'-'", "'/'", "'%'", "'int'", "'double'", 
			"'boolean'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "RESERVED_WORD", "WHITESPACE", "INT", "DOUBLE", "BOOLEAN", 
			"NAME", "STRING", "LINE_COMMENT", "BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniJavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GoalContext extends ParserRuleContext {
		public Node n;
		public ClassNodeContext classNode;
		public ClassNodeContext classNode() {
			return getRuleContext(ClassNodeContext.class,0);
		}
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_goal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			((GoalContext)_localctx).classNode = classNode();

			        ((GoalContext)_localctx).n =  ((GoalContext)_localctx).classNode.n;
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassNodeContext extends ParserRuleContext {
		public ClassNode n;
		public ImpContext imp;
		public List<ImpContext> imports = new ArrayList<ImpContext>();
		public FieldContext field;
		public List<FieldContext> fields = new ArrayList<FieldContext>();
		public MethodContext method;
		public List<MethodContext> methods = new ArrayList<MethodContext>();
		public MainContext main;
		public TerminalNode EOF() { return getToken(MiniJavaParser.EOF, 0); }
		public MainContext main() {
			return getRuleContext(MainContext.class,0);
		}
		public List<ImpContext> imp() {
			return getRuleContexts(ImpContext.class);
		}
		public ImpContext imp(int i) {
			return getRuleContext(ImpContext.class,i);
		}
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public ClassNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classNode; }
	}

	public final ClassNodeContext classNode() throws RecognitionException {
		ClassNodeContext _localctx = new ClassNodeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classNode);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(29);
				((ClassNodeContext)_localctx).imp = imp();
				((ClassNodeContext)_localctx).imports.add(((ClassNodeContext)_localctx).imp);
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(35);
					((ClassNodeContext)_localctx).field = field();
					((ClassNodeContext)_localctx).fields.add(((ClassNodeContext)_localctx).field);
					}
					} 
				}
				setState(40);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 4412409856L) != 0) {
				{
				{
				setState(41);
				((ClassNodeContext)_localctx).method = method();
				((ClassNodeContext)_localctx).methods.add(((ClassNodeContext)_localctx).method);
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(47);
				((ClassNodeContext)_localctx).main = main();
				}
			}

			setState(50);
			match(EOF);

			        var classElements = new ArrayList<Node>();
			        for (var classImport : ((ClassNodeContext)_localctx).imports)
			            classElements.add(classImport.n);

			        for (var classField : ((ClassNodeContext)_localctx).fields)
			            classElements.add(classField.n);

			        for (var classMethod : ((ClassNodeContext)_localctx).methods)
			            classElements.add(classMethod.n);

			        if (((ClassNodeContext)_localctx).main != null)
			            classElements.add(((ClassNodeContext)_localctx).main.n);

			        ((ClassNodeContext)_localctx).n =  new ClassNode(_localctx, classElements);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImpContext extends ParserRuleContext {
		public Import n;
		public Token NAME;
		public List<Token> importNames = new ArrayList<Token>();
		public List<TerminalNode> NAME() { return getTokens(MiniJavaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(MiniJavaParser.NAME, i);
		}
		public ImpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imp; }
	}

	public final ImpContext imp() throws RecognitionException {
		ImpContext _localctx = new ImpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_imp);
		int _la;
		try {
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(T__0);
				setState(54);
				((ImpContext)_localctx).NAME = match(NAME);
				((ImpContext)_localctx).importNames.add(((ImpContext)_localctx).NAME);
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(55);
					match(T__1);
					setState(56);
					((ImpContext)_localctx).NAME = match(NAME);
					((ImpContext)_localctx).importNames.add(((ImpContext)_localctx).NAME);
					}
					}
					setState(61);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(62);
				match(T__2);

				        var importParts = new ArrayList<String>();
				        for (var importName : ((ImpContext)_localctx).importNames)
				            importParts.add(importName.getText());
				        ((ImpContext)_localctx).n =  new ClassImport(_localctx, importParts);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				match(T__0);
				setState(65);
				((ImpContext)_localctx).NAME = match(NAME);
				((ImpContext)_localctx).importNames.add(((ImpContext)_localctx).NAME);
				setState(66);
				match(T__1);
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NAME) {
					{
					{
					setState(67);
					((ImpContext)_localctx).NAME = match(NAME);
					((ImpContext)_localctx).importNames.add(((ImpContext)_localctx).NAME);
					setState(68);
					match(T__1);
					}
					}
					setState(73);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(74);
				match(T__3);
				setState(75);
				match(T__2);

				        var importParts = new ArrayList<String>();
				        for (var importName : ((ImpContext)_localctx).importNames)
				            importParts.add(importName.getText());
				        ((ImpContext)_localctx).n =  new PackageImport(_localctx, importParts);
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldContext extends ParserRuleContext {
		public FieldDefinition n;
		public TypeContext type;
		public Token NAME;
		public ExpressionContext e;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(MiniJavaParser.NAME, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_field);
		try {
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				((FieldContext)_localctx).type = type();
				setState(80);
				((FieldContext)_localctx).NAME = match(NAME);
				setState(81);
				match(T__2);

				        ((FieldContext)_localctx).n =  new FieldDefinition(_localctx, ((FieldContext)_localctx).type.n, (((FieldContext)_localctx).NAME!=null?((FieldContext)_localctx).NAME.getText():null), Optional.empty());
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				((FieldContext)_localctx).type = type();
				setState(85);
				((FieldContext)_localctx).NAME = match(NAME);
				setState(86);
				match(T__4);
				setState(87);
				((FieldContext)_localctx).e = expression(0);
				setState(88);
				match(T__2);

				        ((FieldContext)_localctx).n =  new FieldDefinition(_localctx, ((FieldContext)_localctx).type.n, (((FieldContext)_localctx).NAME!=null?((FieldContext)_localctx).NAME.getText():null), Optional.of(((FieldContext)_localctx).e.n));
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodContext extends ParserRuleContext {
		public MethodDefinition n;
		public TypeContext type;
		public Token NAME;
		public ParameterContext parameter;
		public List<ParameterContext> parameters = new ArrayList<ParameterContext>();
		public MethodBodyContext methodBody;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(MiniJavaParser.NAME, 0); }
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_method);
		int _la;
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
			case T__24:
			case T__25:
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				((MethodContext)_localctx).type = type();
				setState(94);
				((MethodContext)_localctx).NAME = match(NAME);
				setState(95);
				match(T__5);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 4412407808L) != 0) {
					{
					setState(96);
					((MethodContext)_localctx).parameter = parameter();
					((MethodContext)_localctx).parameters.add(((MethodContext)_localctx).parameter);
					setState(101);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(97);
						match(T__6);
						setState(98);
						((MethodContext)_localctx).parameter = parameter();
						((MethodContext)_localctx).parameters.add(((MethodContext)_localctx).parameter);
						}
						}
						setState(103);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(106);
				match(T__7);
				setState(107);
				match(T__8);
				setState(108);
				((MethodContext)_localctx).methodBody = methodBody();
				setState(109);
				match(T__9);

				        var parameterList = new ArrayList<Parameter>();
				        for (var p : ((MethodContext)_localctx).parameters)
				            parameterList.add(p.n);

				        ((MethodContext)_localctx).n =  new MethodDefinition(_localctx, ((MethodContext)_localctx).type.n, (((MethodContext)_localctx).NAME!=null?((MethodContext)_localctx).NAME.getText():null), parameterList, ((MethodContext)_localctx).methodBody.n, new SymbolTable(SymbolTable.Level.Method));
				        // DID NOT USE OPTIONALS
				    
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				match(T__10);
				setState(113);
				((MethodContext)_localctx).NAME = match(NAME);
				setState(114);
				match(T__5);
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 4412407808L) != 0) {
					{
					setState(115);
					((MethodContext)_localctx).parameter = parameter();
					((MethodContext)_localctx).parameters.add(((MethodContext)_localctx).parameter);
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(116);
						match(T__6);
						setState(117);
						((MethodContext)_localctx).parameter = parameter();
						((MethodContext)_localctx).parameters.add(((MethodContext)_localctx).parameter);
						}
						}
						setState(122);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(125);
				match(T__7);
				setState(126);
				match(T__8);
				setState(127);
				((MethodContext)_localctx).methodBody = methodBody();
				setState(128);
				match(T__9);

				        var parameterList = new ArrayList<Parameter>();
				        for (var p : ((MethodContext)_localctx).parameters)
				            parameterList.add(p.n);

				        ((MethodContext)_localctx).n =  new MethodDefinition(_localctx, new TypeNode(_localctx, VoidType.Instance), (((MethodContext)_localctx).NAME!=null?((MethodContext)_localctx).NAME.getText():null), parameterList, ((MethodContext)_localctx).methodBody.n, new SymbolTable(SymbolTable.Level.Method));
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MainContext extends ParserRuleContext {
		public MainMethod n;
		public MethodBodyContext methodBody;
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_main);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__11);
			setState(134);
			match(T__5);
			setState(135);
			match(T__7);
			setState(136);
			match(T__8);
			setState(137);
			((MainContext)_localctx).methodBody = methodBody();
			setState(138);
			match(T__9);

			        ((MainContext)_localctx).n =  new MainMethod(_localctx, ((MainContext)_localctx).methodBody.n, new SymbolTable(SymbolTable.Level.Method));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodBodyContext extends ParserRuleContext {
		public Block n;
		public StatementContext statement;
		public List<StatementContext> stmts = new ArrayList<StatementContext>();
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_methodBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 16764625480L) != 0) {
				{
				{
				setState(141);
				((MethodBodyContext)_localctx).statement = statement();
				((MethodBodyContext)_localctx).stmts.add(((MethodBodyContext)_localctx).statement);
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			        var statements = new ArrayList<Statement>();
			        for(var stmt : ((MethodBodyContext)_localctx).stmts)
			            statements.add(stmt.n);
			        ((MethodBodyContext)_localctx).n =  new Block(_localctx, statements, new SymbolTable(SymbolTable.Level.Block));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public Parameter n;
		public TypeContext type;
		public Token NAME;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(MiniJavaParser.NAME, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			((ParameterContext)_localctx).type = type();
			setState(150);
			((ParameterContext)_localctx).NAME = match(NAME);

			        ((ParameterContext)_localctx).n =  new Parameter(_localctx, ((ParameterContext)_localctx).type.n, (((ParameterContext)_localctx).NAME!=null?((ParameterContext)_localctx).NAME.getText():null));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public Statement n;
		public StatementContext statement;
		public List<StatementContext> stmts = new ArrayList<StatementContext>();
		public DeclarationContext declaration;
		public ExpressionContext expression;
		public ExpressionContext e;
		public ExpressionContext cond;
		public StatementContext body;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		int _la;
		try {
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				match(T__2);

				        ((StatementContext)_localctx).n =  new EmptyStatement(_localctx);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
				match(T__8);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 16764625480L) != 0) {
					{
					{
					setState(156);
					((StatementContext)_localctx).statement = statement();
					((StatementContext)_localctx).stmts.add(((StatementContext)_localctx).statement);
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(162);
				match(T__9);

				        // is there a statment? if not, empty statement
				            var stmtList = new ArrayList<Statement>();
				            for(var stmt : ((StatementContext)_localctx).stmts){
				                stmtList.add(stmt.n);
				            }
				            ((StatementContext)_localctx).n =  new Block(_localctx, stmtList, new SymbolTable(SymbolTable.Level.Block));
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				((StatementContext)_localctx).declaration = declaration();

				        ((StatementContext)_localctx).n =  ((StatementContext)_localctx).declaration.n;
				    
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(167);
				((StatementContext)_localctx).expression = expression(0);
				setState(168);
				match(T__2);

				        ((StatementContext)_localctx).n =  new ExpressionStatement(_localctx, ((StatementContext)_localctx).expression.n);
				    
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(171);
				match(T__12);
				setState(172);
				((StatementContext)_localctx).e = expression(0);
				setState(173);
				match(T__2);

				        ((StatementContext)_localctx).n =  new Return(_localctx, Optional.of(((StatementContext)_localctx).e.n));
				    
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(176);
				match(T__12);
				setState(177);
				match(T__2);

				        ((StatementContext)_localctx).n =  new Return(_localctx, Optional.empty());
				    
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(179);
				match(T__13);
				setState(180);
				match(T__5);
				setState(181);
				((StatementContext)_localctx).cond = expression(0);
				setState(182);
				match(T__7);
				setState(183);
				((StatementContext)_localctx).body = statement();

				        ((StatementContext)_localctx).n =  new While(_localctx, ((StatementContext)_localctx).cond.n, ((StatementContext)_localctx).body.n);
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public Declarations n;
		public TypeContext type;
		public DecItemContext decItem;
		public List<DecItemContext> items = new ArrayList<DecItemContext>();
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<DecItemContext> decItem() {
			return getRuleContexts(DecItemContext.class);
		}
		public DecItemContext decItem(int i) {
			return getRuleContext(DecItemContext.class,i);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			((DeclarationContext)_localctx).type = type();
			setState(189);
			((DeclarationContext)_localctx).decItem = decItem();
			((DeclarationContext)_localctx).items.add(((DeclarationContext)_localctx).decItem);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(190);
				match(T__6);
				setState(191);
				((DeclarationContext)_localctx).decItem = decItem();
				((DeclarationContext)_localctx).items.add(((DeclarationContext)_localctx).decItem);
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(197);
			match(T__2);

			        //Declarations parameters: TypeNode, list of Declaration
			        //Declaration parameters: String name, Optional<Expression>

			        var itemList = new ArrayList<Declaration>();
			        for(var item : ((DeclarationContext)_localctx).items)
			            itemList.add(item.n);

			        ((DeclarationContext)_localctx).n =  new Declarations(_localctx, ((DeclarationContext)_localctx).type.n, itemList);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecItemContext extends ParserRuleContext {
		public Declaration n;
		public Token NAME;
		public ExpressionContext e;
		public TerminalNode NAME() { return getToken(MiniJavaParser.NAME, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DecItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decItem; }
	}

	public final DecItemContext decItem() throws RecognitionException {
		DecItemContext _localctx = new DecItemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_decItem);
		try {
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				((DecItemContext)_localctx).NAME = match(NAME);

				        ((DecItemContext)_localctx).n =  new Declaration(_localctx, (((DecItemContext)_localctx).NAME!=null?((DecItemContext)_localctx).NAME.getText():null), Optional.empty());
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				((DecItemContext)_localctx).NAME = match(NAME);
				setState(203);
				match(T__4);
				setState(204);
				((DecItemContext)_localctx).e = expression(0);

				        ((DecItemContext)_localctx).n =  new Declaration(_localctx, (((DecItemContext)_localctx).NAME!=null?((DecItemContext)_localctx).NAME.getText():null), Optional.of(((DecItemContext)_localctx).e.n));
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public Expression n;
		public ExpressionContext e;
		public ExpressionContext l;
		public ExpressionContext expression;
		public List<ExpressionContext> args = new ArrayList<ExpressionContext>();
		public Token INT;
		public Token DOUBLE;
		public Token BOOLEAN;
		public Token STRING;
		public Token NAME;
		public Token op;
		public TypeContext type;
		public ExpressionContext r;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode INT() { return getToken(MiniJavaParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(MiniJavaParser.DOUBLE, 0); }
		public TerminalNode BOOLEAN() { return getToken(MiniJavaParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(MiniJavaParser.STRING, 0); }
		public TerminalNode NAME() { return getToken(MiniJavaParser.NAME, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(210);
				match(T__14);
				setState(211);
				match(T__5);
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 16647159872L) != 0) {
					{
					setState(212);
					((ExpressionContext)_localctx).expression = expression(0);
					((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
					setState(217);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(213);
						match(T__6);
						setState(214);
						((ExpressionContext)_localctx).expression = expression(0);
						((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
						}
						}
						setState(219);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(222);
				match(T__7);

				        var prints = new ArrayList<Expression>();
				        for(var arg : ((ExpressionContext)_localctx).args)
				            prints.add(arg.n);

				        ((ExpressionContext)_localctx).n =  new Print(_localctx, prints);
				    
				}
				break;
			case 2:
				{
				setState(224);
				((ExpressionContext)_localctx).INT = match(INT);

				        ((ExpressionContext)_localctx).n =  new IntLiteral(_localctx, (((ExpressionContext)_localctx).INT!=null?((ExpressionContext)_localctx).INT.getText():null));
				    
				}
				break;
			case 3:
				{
				setState(226);
				((ExpressionContext)_localctx).DOUBLE = match(DOUBLE);

				        ((ExpressionContext)_localctx).n =  new DoubleLiteral(_localctx, (((ExpressionContext)_localctx).DOUBLE!=null?((ExpressionContext)_localctx).DOUBLE.getText():null));
				    
				}
				break;
			case 4:
				{
				setState(228);
				((ExpressionContext)_localctx).BOOLEAN = match(BOOLEAN);

				        ((ExpressionContext)_localctx).n =  new BooleanLiteral(_localctx, (((ExpressionContext)_localctx).BOOLEAN!=null?((ExpressionContext)_localctx).BOOLEAN.getText():null));
				    
				}
				break;
			case 5:
				{
				setState(230);
				match(T__15);

				        ((ExpressionContext)_localctx).n =  new This(_localctx);
				    
				}
				break;
			case 6:
				{
				setState(232);
				((ExpressionContext)_localctx).STRING = match(STRING);

				        ((ExpressionContext)_localctx).n =  new StringLiteral(_localctx, (((ExpressionContext)_localctx).STRING!=null?((ExpressionContext)_localctx).STRING.getText():null));
				    
				}
				break;
			case 7:
				{
				setState(234);
				((ExpressionContext)_localctx).NAME = match(NAME);

				        ((ExpressionContext)_localctx).n =  new VariableAccess(_localctx, (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null));
				    
				}
				break;
			case 8:
				{
				setState(236);
				match(T__16);
				setState(237);
				((ExpressionContext)_localctx).NAME = match(NAME);
				setState(238);
				match(T__5);
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 16647159872L) != 0) {
					{
					setState(239);
					((ExpressionContext)_localctx).expression = expression(0);
					((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(240);
						match(T__6);
						setState(241);
						((ExpressionContext)_localctx).expression = expression(0);
						((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
						}
						}
						setState(246);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(249);
				match(T__7);

				        var constructorArgs = new ArrayList<Expression>();
				        for (var arg : ((ExpressionContext)_localctx).args)
				            constructorArgs.add(arg.n);
				        ((ExpressionContext)_localctx).n =  new ConstructorCall(_localctx, (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null), constructorArgs);
				    
				}
				break;
			case 9:
				{
				setState(251);
				((ExpressionContext)_localctx).NAME = match(NAME);
				setState(252);
				match(T__5);
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 16647159872L) != 0) {
					{
					setState(253);
					((ExpressionContext)_localctx).expression = expression(0);
					((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
					setState(258);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(254);
						match(T__6);
						setState(255);
						((ExpressionContext)_localctx).expression = expression(0);
						((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
						}
						}
						setState(260);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(263);
				match(T__7);

				        var methodArgs = new ArrayList<Expression>();
				        for (var arg : ((ExpressionContext)_localctx).args)
				            methodArgs.add(arg.n);
				        ((ExpressionContext)_localctx).n =  new MethodCall(_localctx, Optional.empty(), (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null), methodArgs);
				    
				}
				break;
			case 10:
				{
				setState(265);
				match(T__5);
				setState(266);
				((ExpressionContext)_localctx).e = expression(0);
				setState(267);
				match(T__7);

				        ((ExpressionContext)_localctx).n =  ((ExpressionContext)_localctx).e.n;
				    
				}
				break;
			case 11:
				{
				setState(270);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 3932160L) != 0) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(271);
				((ExpressionContext)_localctx).e = expression(5);

				        if((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null).equals("++") || (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null).equals("--")){
				            ((ExpressionContext)_localctx).n =  new PreIncrement(_localctx, ((ExpressionContext)_localctx).e.n, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null));
				        }
				        else if((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null).equals("-")) {
				            ((ExpressionContext)_localctx).n =  new Negate(_localctx, ((ExpressionContext)_localctx).e.n);
				        }
				        else {
				            ((ExpressionContext)_localctx).n =  ((ExpressionContext)_localctx).e.n;
				        }
				    
				}
				break;
			case 12:
				{
				setState(274);
				match(T__5);
				setState(275);
				((ExpressionContext)_localctx).type = type();
				setState(276);
				match(T__7);
				setState(277);
				((ExpressionContext)_localctx).e = expression(4);

				        ((ExpressionContext)_localctx).n =  new Cast(_localctx, ((ExpressionContext)_localctx).type.n, ((ExpressionContext)_localctx).e.n);
				    
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(324);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(322);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(282);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(283);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 12582928L) != 0) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(284);
						((ExpressionContext)_localctx).r = expression(4);

						                  ((ExpressionContext)_localctx).n =  new BinaryOp(_localctx, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null), ((ExpressionContext)_localctx).l.n, ((ExpressionContext)_localctx).r.n);
						              
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(287);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(288);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(289);
						((ExpressionContext)_localctx).r = expression(3);

						                  ((ExpressionContext)_localctx).n =  new BinaryOp(_localctx, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null), ((ExpressionContext)_localctx).l.n, ((ExpressionContext)_localctx).r.n);
						              
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(292);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(293);
						match(T__4);
						setState(294);
						((ExpressionContext)_localctx).r = expression(1);

						                  ((ExpressionContext)_localctx).n =  new Assignment(_localctx, ((ExpressionContext)_localctx).l.n, ((ExpressionContext)_localctx).r.n);
						              
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(297);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(298);
						match(T__1);
						setState(299);
						((ExpressionContext)_localctx).NAME = match(NAME);

						                  ((ExpressionContext)_localctx).n =  new FieldAccess(_localctx, ((ExpressionContext)_localctx).e.n, (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null));
						              
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(301);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(303);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__1) {
							{
							setState(302);
							match(T__1);
							}
						}

						setState(305);
						((ExpressionContext)_localctx).NAME = match(NAME);
						setState(306);
						match(T__5);
						setState(315);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((_la) & ~0x3f) == 0 && ((1L << _la) & 16647159872L) != 0) {
							{
							setState(307);
							((ExpressionContext)_localctx).expression = expression(0);
							((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
							setState(312);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__6) {
								{
								{
								setState(308);
								match(T__6);
								setState(309);
								((ExpressionContext)_localctx).expression = expression(0);
								((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
								}
								}
								setState(314);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(317);
						match(T__7);

						                  var methodArgs = new ArrayList<Expression>();
						                  for (var arg : ((ExpressionContext)_localctx).args)
						                      methodArgs.add(arg.n);
						                      ((ExpressionContext)_localctx).n =  new MethodCall(_localctx, Optional.of(((ExpressionContext)_localctx).e.n), (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null), methodArgs);
						              
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(319);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(320);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__17 || _la==T__18) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}

						                      ((ExpressionContext)_localctx).n =  new PostIncrement(_localctx, ((ExpressionContext)_localctx).e.n, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)); // (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null) may be ++ or --
						              
						}
						break;
					}
					} 
				}
				setState(326);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeNode n;
		public Token NAME;
		public TerminalNode NAME() { return getToken(MiniJavaParser.NAME, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_type);
		try {
			setState(335);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
				enterOuterAlt(_localctx, 1);
				{
				setState(327);
				match(T__23);

				        ((TypeContext)_localctx).n =  new TypeNode(_localctx, PrimitiveType.Int);
				    
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				match(T__24);

				        ((TypeContext)_localctx).n =  new TypeNode(_localctx, PrimitiveType.Double);
				    
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(331);
				match(T__25);

				        ((TypeContext)_localctx).n =  new TypeNode(_localctx, PrimitiveType.Boolean);
				    
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(333);
				((TypeContext)_localctx).NAME = match(NAME);

				        ((TypeContext)_localctx).n =  new TypeNode(_localctx, new ClassType((((TypeContext)_localctx).NAME!=null?((TypeContext)_localctx).NAME.getText():null)));
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 1);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001#\u0152\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0005\u0001"+
		"\u001f\b\u0001\n\u0001\f\u0001\"\t\u0001\u0001\u0001\u0005\u0001%\b\u0001"+
		"\n\u0001\f\u0001(\t\u0001\u0001\u0001\u0005\u0001+\b\u0001\n\u0001\f\u0001"+
		".\t\u0001\u0001\u0001\u0003\u00011\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002:\b"+
		"\u0002\n\u0002\f\u0002=\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002F\b\u0002\n\u0002"+
		"\f\u0002I\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002N\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003\\\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004d\b\u0004\n\u0004\f\u0004g\t\u0004"+
		"\u0003\u0004i\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004w\b\u0004\n\u0004\f\u0004z\t\u0004"+
		"\u0003\u0004|\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004\u0084\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0005\u0006\u008f\b\u0006\n\u0006\f\u0006\u0092\t\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0005\b\u009e\b\b\n\b\f\b\u00a1\t\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00bb\b\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0005\t\u00c1\b\t\n\t\f\t\u00c4\t\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u00d0\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0005\u000b\u00d8\b\u000b\n\u000b\f\u000b\u00db\t\u000b\u0003"+
		"\u000b\u00dd\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00f3\b\u000b\n"+
		"\u000b\f\u000b\u00f6\t\u000b\u0003\u000b\u00f8\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u0101\b\u000b\n\u000b\f\u000b\u0104\t\u000b\u0003\u000b\u0106\b"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u0119\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0130"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u0137\b\u000b\n\u000b\f\u000b\u013a\t\u000b\u0003\u000b\u013c\b"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u0143\b\u000b\n\u000b\f\u000b\u0146\t\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0150\b\f\u0001\f\u0000"+
		"\u0001\u0016\r\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u0000\u0004\u0001\u0000\u0012\u0015\u0002\u0000\u0004\u0004\u0016"+
		"\u0017\u0001\u0000\u0014\u0015\u0001\u0000\u0012\u0013\u0178\u0000\u001a"+
		"\u0001\u0000\u0000\u0000\u0002 \u0001\u0000\u0000\u0000\u0004M\u0001\u0000"+
		"\u0000\u0000\u0006[\u0001\u0000\u0000\u0000\b\u0083\u0001\u0000\u0000"+
		"\u0000\n\u0085\u0001\u0000\u0000\u0000\f\u0090\u0001\u0000\u0000\u0000"+
		"\u000e\u0095\u0001\u0000\u0000\u0000\u0010\u00ba\u0001\u0000\u0000\u0000"+
		"\u0012\u00bc\u0001\u0000\u0000\u0000\u0014\u00cf\u0001\u0000\u0000\u0000"+
		"\u0016\u0118\u0001\u0000\u0000\u0000\u0018\u014f\u0001\u0000\u0000\u0000"+
		"\u001a\u001b\u0003\u0002\u0001\u0000\u001b\u001c\u0006\u0000\uffff\uffff"+
		"\u0000\u001c\u0001\u0001\u0000\u0000\u0000\u001d\u001f\u0003\u0004\u0002"+
		"\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f\"\u0001\u0000\u0000\u0000"+
		" \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!&\u0001\u0000"+
		"\u0000\u0000\" \u0001\u0000\u0000\u0000#%\u0003\u0006\u0003\u0000$#\u0001"+
		"\u0000\u0000\u0000%(\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000"+
		"&\'\u0001\u0000\u0000\u0000\',\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000"+
		"\u0000)+\u0003\b\u0004\u0000*)\u0001\u0000\u0000\u0000+.\u0001\u0000\u0000"+
		"\u0000,*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-0\u0001\u0000"+
		"\u0000\u0000.,\u0001\u0000\u0000\u0000/1\u0003\n\u0005\u00000/\u0001\u0000"+
		"\u0000\u000001\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000023\u0005"+
		"\u0000\u0000\u000134\u0006\u0001\uffff\uffff\u00004\u0003\u0001\u0000"+
		"\u0000\u000056\u0005\u0001\u0000\u00006;\u0005 \u0000\u000078\u0005\u0002"+
		"\u0000\u00008:\u0005 \u0000\u000097\u0001\u0000\u0000\u0000:=\u0001\u0000"+
		"\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<>\u0001"+
		"\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>?\u0005\u0003\u0000\u0000"+
		"?N\u0006\u0002\uffff\uffff\u0000@A\u0005\u0001\u0000\u0000AB\u0005 \u0000"+
		"\u0000BG\u0005\u0002\u0000\u0000CD\u0005 \u0000\u0000DF\u0005\u0002\u0000"+
		"\u0000EC\u0001\u0000\u0000\u0000FI\u0001\u0000\u0000\u0000GE\u0001\u0000"+
		"\u0000\u0000GH\u0001\u0000\u0000\u0000HJ\u0001\u0000\u0000\u0000IG\u0001"+
		"\u0000\u0000\u0000JK\u0005\u0004\u0000\u0000KL\u0005\u0003\u0000\u0000"+
		"LN\u0006\u0002\uffff\uffff\u0000M5\u0001\u0000\u0000\u0000M@\u0001\u0000"+
		"\u0000\u0000N\u0005\u0001\u0000\u0000\u0000OP\u0003\u0018\f\u0000PQ\u0005"+
		" \u0000\u0000QR\u0005\u0003\u0000\u0000RS\u0006\u0003\uffff\uffff\u0000"+
		"S\\\u0001\u0000\u0000\u0000TU\u0003\u0018\f\u0000UV\u0005 \u0000\u0000"+
		"VW\u0005\u0005\u0000\u0000WX\u0003\u0016\u000b\u0000XY\u0005\u0003\u0000"+
		"\u0000YZ\u0006\u0003\uffff\uffff\u0000Z\\\u0001\u0000\u0000\u0000[O\u0001"+
		"\u0000\u0000\u0000[T\u0001\u0000\u0000\u0000\\\u0007\u0001\u0000\u0000"+
		"\u0000]^\u0003\u0018\f\u0000^_\u0005 \u0000\u0000_h\u0005\u0006\u0000"+
		"\u0000`e\u0003\u000e\u0007\u0000ab\u0005\u0007\u0000\u0000bd\u0003\u000e"+
		"\u0007\u0000ca\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001"+
		"\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fi\u0001\u0000\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000h`\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000"+
		"\u0000ij\u0001\u0000\u0000\u0000jk\u0005\b\u0000\u0000kl\u0005\t\u0000"+
		"\u0000lm\u0003\f\u0006\u0000mn\u0005\n\u0000\u0000no\u0006\u0004\uffff"+
		"\uffff\u0000o\u0084\u0001\u0000\u0000\u0000pq\u0005\u000b\u0000\u0000"+
		"qr\u0005 \u0000\u0000r{\u0005\u0006\u0000\u0000sx\u0003\u000e\u0007\u0000"+
		"tu\u0005\u0007\u0000\u0000uw\u0003\u000e\u0007\u0000vt\u0001\u0000\u0000"+
		"\u0000wz\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000"+
		"\u0000\u0000y|\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000{s\u0001"+
		"\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000"+
		"}~\u0005\b\u0000\u0000~\u007f\u0005\t\u0000\u0000\u007f\u0080\u0003\f"+
		"\u0006\u0000\u0080\u0081\u0005\n\u0000\u0000\u0081\u0082\u0006\u0004\uffff"+
		"\uffff\u0000\u0082\u0084\u0001\u0000\u0000\u0000\u0083]\u0001\u0000\u0000"+
		"\u0000\u0083p\u0001\u0000\u0000\u0000\u0084\t\u0001\u0000\u0000\u0000"+
		"\u0085\u0086\u0005\f\u0000\u0000\u0086\u0087\u0005\u0006\u0000\u0000\u0087"+
		"\u0088\u0005\b\u0000\u0000\u0088\u0089\u0005\t\u0000\u0000\u0089\u008a"+
		"\u0003\f\u0006\u0000\u008a\u008b\u0005\n\u0000\u0000\u008b\u008c\u0006"+
		"\u0005\uffff\uffff\u0000\u008c\u000b\u0001\u0000\u0000\u0000\u008d\u008f"+
		"\u0003\u0010\b\u0000\u008e\u008d\u0001\u0000\u0000\u0000\u008f\u0092\u0001"+
		"\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001"+
		"\u0000\u0000\u0000\u0091\u0093\u0001\u0000\u0000\u0000\u0092\u0090\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0006\u0006\uffff\uffff\u0000\u0094\r\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0003\u0018\f\u0000\u0096\u0097\u0005 "+
		"\u0000\u0000\u0097\u0098\u0006\u0007\uffff\uffff\u0000\u0098\u000f\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0005\u0003\u0000\u0000\u009a\u00bb\u0006"+
		"\b\uffff\uffff\u0000\u009b\u009f\u0005\t\u0000\u0000\u009c\u009e\u0003"+
		"\u0010\b\u0000\u009d\u009c\u0001\u0000\u0000\u0000\u009e\u00a1\u0001\u0000"+
		"\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a3\u0005\n\u0000\u0000\u00a3\u00bb\u0006\b\uffff"+
		"\uffff\u0000\u00a4\u00a5\u0003\u0012\t\u0000\u00a5\u00a6\u0006\b\uffff"+
		"\uffff\u0000\u00a6\u00bb\u0001\u0000\u0000\u0000\u00a7\u00a8\u0003\u0016"+
		"\u000b\u0000\u00a8\u00a9\u0005\u0003\u0000\u0000\u00a9\u00aa\u0006\b\uffff"+
		"\uffff\u0000\u00aa\u00bb\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005\r\u0000"+
		"\u0000\u00ac\u00ad\u0003\u0016\u000b\u0000\u00ad\u00ae\u0005\u0003\u0000"+
		"\u0000\u00ae\u00af\u0006\b\uffff\uffff\u0000\u00af\u00bb\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b1\u0005\r\u0000\u0000\u00b1\u00b2\u0005\u0003\u0000\u0000"+
		"\u00b2\u00bb\u0006\b\uffff\uffff\u0000\u00b3\u00b4\u0005\u000e\u0000\u0000"+
		"\u00b4\u00b5\u0005\u0006\u0000\u0000\u00b5\u00b6\u0003\u0016\u000b\u0000"+
		"\u00b6\u00b7\u0005\b\u0000\u0000\u00b7\u00b8\u0003\u0010\b\u0000\u00b8"+
		"\u00b9\u0006\b\uffff\uffff\u0000\u00b9\u00bb\u0001\u0000\u0000\u0000\u00ba"+
		"\u0099\u0001\u0000\u0000\u0000\u00ba\u009b\u0001\u0000\u0000\u0000\u00ba"+
		"\u00a4\u0001\u0000\u0000\u0000\u00ba\u00a7\u0001\u0000\u0000\u0000\u00ba"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ba\u00b0\u0001\u0000\u0000\u0000\u00ba"+
		"\u00b3\u0001\u0000\u0000\u0000\u00bb\u0011\u0001\u0000\u0000\u0000\u00bc"+
		"\u00bd\u0003\u0018\f\u0000\u00bd\u00c2\u0003\u0014\n\u0000\u00be\u00bf"+
		"\u0005\u0007\u0000\u0000\u00bf\u00c1\u0003\u0014\n\u0000\u00c0\u00be\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c4\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005"+
		"\u0003\u0000\u0000\u00c6\u00c7\u0006\t\uffff\uffff\u0000\u00c7\u0013\u0001"+
		"\u0000\u0000\u0000\u00c8\u00c9\u0005 \u0000\u0000\u00c9\u00d0\u0006\n"+
		"\uffff\uffff\u0000\u00ca\u00cb\u0005 \u0000\u0000\u00cb\u00cc\u0005\u0005"+
		"\u0000\u0000\u00cc\u00cd\u0003\u0016\u000b\u0000\u00cd\u00ce\u0006\n\uffff"+
		"\uffff\u0000\u00ce\u00d0\u0001\u0000\u0000\u0000\u00cf\u00c8\u0001\u0000"+
		"\u0000\u0000\u00cf\u00ca\u0001\u0000\u0000\u0000\u00d0\u0015\u0001\u0000"+
		"\u0000\u0000\u00d1\u00d2\u0006\u000b\uffff\uffff\u0000\u00d2\u00d3\u0005"+
		"\u000f\u0000\u0000\u00d3\u00dc\u0005\u0006\u0000\u0000\u00d4\u00d9\u0003"+
		"\u0016\u000b\u0000\u00d5\u00d6\u0005\u0007\u0000\u0000\u00d6\u00d8\u0003"+
		"\u0016\u000b\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d8\u00db\u0001"+
		"\u0000\u0000\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00d9\u00da\u0001"+
		"\u0000\u0000\u0000\u00da\u00dd\u0001\u0000\u0000\u0000\u00db\u00d9\u0001"+
		"\u0000\u0000\u0000\u00dc\u00d4\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001"+
		"\u0000\u0000\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u00df\u0005"+
		"\b\u0000\u0000\u00df\u0119\u0006\u000b\uffff\uffff\u0000\u00e0\u00e1\u0005"+
		"\u001d\u0000\u0000\u00e1\u0119\u0006\u000b\uffff\uffff\u0000\u00e2\u00e3"+
		"\u0005\u001e\u0000\u0000\u00e3\u0119\u0006\u000b\uffff\uffff\u0000\u00e4"+
		"\u00e5\u0005\u001f\u0000\u0000\u00e5\u0119\u0006\u000b\uffff\uffff\u0000"+
		"\u00e6\u00e7\u0005\u0010\u0000\u0000\u00e7\u0119\u0006\u000b\uffff\uffff"+
		"\u0000\u00e8\u00e9\u0005!\u0000\u0000\u00e9\u0119\u0006\u000b\uffff\uffff"+
		"\u0000\u00ea\u00eb\u0005 \u0000\u0000\u00eb\u0119\u0006\u000b\uffff\uffff"+
		"\u0000\u00ec\u00ed\u0005\u0011\u0000\u0000\u00ed\u00ee\u0005 \u0000\u0000"+
		"\u00ee\u00f7\u0005\u0006\u0000\u0000\u00ef\u00f4\u0003\u0016\u000b\u0000"+
		"\u00f0\u00f1\u0005\u0007\u0000\u0000\u00f1\u00f3\u0003\u0016\u000b\u0000"+
		"\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f3\u00f6\u0001\u0000\u0000\u0000"+
		"\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000"+
		"\u00f5\u00f8\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000"+
		"\u00f7\u00ef\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000"+
		"\u00f8\u00f9\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005\b\u0000\u0000\u00fa"+
		"\u0119\u0006\u000b\uffff\uffff\u0000\u00fb\u00fc\u0005 \u0000\u0000\u00fc"+
		"\u0105\u0005\u0006\u0000\u0000\u00fd\u0102\u0003\u0016\u000b\u0000\u00fe"+
		"\u00ff\u0005\u0007\u0000\u0000\u00ff\u0101\u0003\u0016\u000b\u0000\u0100"+
		"\u00fe\u0001\u0000\u0000\u0000\u0101\u0104\u0001\u0000\u0000\u0000\u0102"+
		"\u0100\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103"+
		"\u0106\u0001\u0000\u0000\u0000\u0104\u0102\u0001\u0000\u0000\u0000\u0105"+
		"\u00fd\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000\u0106"+
		"\u0107\u0001\u0000\u0000\u0000\u0107\u0108\u0005\b\u0000\u0000\u0108\u0119"+
		"\u0006\u000b\uffff\uffff\u0000\u0109\u010a\u0005\u0006\u0000\u0000\u010a"+
		"\u010b\u0003\u0016\u000b\u0000\u010b\u010c\u0005\b\u0000\u0000\u010c\u010d"+
		"\u0006\u000b\uffff\uffff\u0000\u010d\u0119\u0001\u0000\u0000\u0000\u010e"+
		"\u010f\u0007\u0000\u0000\u0000\u010f\u0110\u0003\u0016\u000b\u0005\u0110"+
		"\u0111\u0006\u000b\uffff\uffff\u0000\u0111\u0119\u0001\u0000\u0000\u0000"+
		"\u0112\u0113\u0005\u0006\u0000\u0000\u0113\u0114\u0003\u0018\f\u0000\u0114"+
		"\u0115\u0005\b\u0000\u0000\u0115\u0116\u0003\u0016\u000b\u0004\u0116\u0117"+
		"\u0006\u000b\uffff\uffff\u0000\u0117\u0119\u0001\u0000\u0000\u0000\u0118"+
		"\u00d1\u0001\u0000\u0000\u0000\u0118\u00e0\u0001\u0000\u0000\u0000\u0118"+
		"\u00e2\u0001\u0000\u0000\u0000\u0118\u00e4\u0001\u0000\u0000\u0000\u0118"+
		"\u00e6\u0001\u0000\u0000\u0000\u0118\u00e8\u0001\u0000\u0000\u0000\u0118"+
		"\u00ea\u0001\u0000\u0000\u0000\u0118\u00ec\u0001\u0000\u0000\u0000\u0118"+
		"\u00fb\u0001\u0000\u0000\u0000\u0118\u0109\u0001\u0000\u0000\u0000\u0118"+
		"\u010e\u0001\u0000\u0000\u0000\u0118\u0112\u0001\u0000\u0000\u0000\u0119"+
		"\u0144\u0001\u0000\u0000\u0000\u011a\u011b\n\u0003\u0000\u0000\u011b\u011c"+
		"\u0007\u0001\u0000\u0000\u011c\u011d\u0003\u0016\u000b\u0004\u011d\u011e"+
		"\u0006\u000b\uffff\uffff\u0000\u011e\u0143\u0001\u0000\u0000\u0000\u011f"+
		"\u0120\n\u0002\u0000\u0000\u0120\u0121\u0007\u0002\u0000\u0000\u0121\u0122"+
		"\u0003\u0016\u000b\u0003\u0122\u0123\u0006\u000b\uffff\uffff\u0000\u0123"+
		"\u0143\u0001\u0000\u0000\u0000\u0124\u0125\n\u0001\u0000\u0000\u0125\u0126"+
		"\u0005\u0005\u0000\u0000\u0126\u0127\u0003\u0016\u000b\u0001\u0127\u0128"+
		"\u0006\u000b\uffff\uffff\u0000\u0128\u0143\u0001\u0000\u0000\u0000\u0129"+
		"\u012a\n\u000b\u0000\u0000\u012a\u012b\u0005\u0002\u0000\u0000\u012b\u012c"+
		"\u0005 \u0000\u0000\u012c\u0143\u0006\u000b\uffff\uffff\u0000\u012d\u012f"+
		"\n\t\u0000\u0000\u012e\u0130\u0005\u0002\u0000\u0000\u012f\u012e\u0001"+
		"\u0000\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0131\u0001"+
		"\u0000\u0000\u0000\u0131\u0132\u0005 \u0000\u0000\u0132\u013b\u0005\u0006"+
		"\u0000\u0000\u0133\u0138\u0003\u0016\u000b\u0000\u0134\u0135\u0005\u0007"+
		"\u0000\u0000\u0135\u0137\u0003\u0016\u000b\u0000\u0136\u0134\u0001\u0000"+
		"\u0000\u0000\u0137\u013a\u0001\u0000\u0000\u0000\u0138\u0136\u0001\u0000"+
		"\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u013c\u0001\u0000"+
		"\u0000\u0000\u013a\u0138\u0001\u0000\u0000\u0000\u013b\u0133\u0001\u0000"+
		"\u0000\u0000\u013b\u013c\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000"+
		"\u0000\u0000\u013d\u013e\u0005\b\u0000\u0000\u013e\u0143\u0006\u000b\uffff"+
		"\uffff\u0000\u013f\u0140\n\u0006\u0000\u0000\u0140\u0141\u0007\u0003\u0000"+
		"\u0000\u0141\u0143\u0006\u000b\uffff\uffff\u0000\u0142\u011a\u0001\u0000"+
		"\u0000\u0000\u0142\u011f\u0001\u0000\u0000\u0000\u0142\u0124\u0001\u0000"+
		"\u0000\u0000\u0142\u0129\u0001\u0000\u0000\u0000\u0142\u012d\u0001\u0000"+
		"\u0000\u0000\u0142\u013f\u0001\u0000\u0000\u0000\u0143\u0146\u0001\u0000"+
		"\u0000\u0000\u0144\u0142\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000"+
		"\u0000\u0000\u0145\u0017\u0001\u0000\u0000\u0000\u0146\u0144\u0001\u0000"+
		"\u0000\u0000\u0147\u0148\u0005\u0018\u0000\u0000\u0148\u0150\u0006\f\uffff"+
		"\uffff\u0000\u0149\u014a\u0005\u0019\u0000\u0000\u014a\u0150\u0006\f\uffff"+
		"\uffff\u0000\u014b\u014c\u0005\u001a\u0000\u0000\u014c\u0150\u0006\f\uffff"+
		"\uffff\u0000\u014d\u014e\u0005 \u0000\u0000\u014e\u0150\u0006\f\uffff"+
		"\uffff\u0000\u014f\u0147\u0001\u0000\u0000\u0000\u014f\u0149\u0001\u0000"+
		"\u0000\u0000\u014f\u014b\u0001\u0000\u0000\u0000\u014f\u014d\u0001\u0000"+
		"\u0000\u0000\u0150\u0019\u0001\u0000\u0000\u0000\u001f &,0;GM[ehx{\u0083"+
		"\u0090\u009f\u00ba\u00c2\u00cf\u00d9\u00dc\u00f4\u00f7\u0102\u0105\u0118"+
		"\u012f\u0138\u013b\u0142\u0144\u014f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
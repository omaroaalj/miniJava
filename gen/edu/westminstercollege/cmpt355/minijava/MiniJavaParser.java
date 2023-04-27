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
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, RESERVED_WORD=35, WHITESPACE=36, INT=37, 
		DOUBLE=38, BOOLEAN=39, NAME=40, STRING=41, LINE_COMMENT=42, BLOCK_COMMENT=43;
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
			"'}'", "'void'", "'main'", "'return'", "'while'", "'if'", "'else'", "'_print'", 
			"'this'", "'new'", "'++'", "'--'", "'+'", "'-'", "'/'", "'%'", "'<'", 
			"'<='", "'>'", "'>='", "'=='", "'!='", "'int'", "'double'", "'boolean'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "RESERVED_WORD", 
			"WHITESPACE", "INT", "DOUBLE", "BOOLEAN", "NAME", "STRING", "LINE_COMMENT", 
			"BLOCK_COMMENT"
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
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 1129576400896L) != 0) {
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
			case T__31:
			case T__32:
			case T__33:
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
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 1129576398848L) != 0) {
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
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 1129576398848L) != 0) {
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
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 4290689032776L) != 0) {
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
		public StatementContext elseBody;
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
			setState(202);
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
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 4290689032776L) != 0) {
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
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(186);
				match(T__14);
				setState(187);
				match(T__5);
				setState(188);
				((StatementContext)_localctx).cond = expression(0);
				setState(189);
				match(T__7);
				setState(190);
				((StatementContext)_localctx).body = statement();

				        ((StatementContext)_localctx).n =  new If(_localctx, ((StatementContext)_localctx).cond.n, ((StatementContext)_localctx).body.n);
				    
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(193);
				match(T__14);
				setState(194);
				match(T__5);
				setState(195);
				((StatementContext)_localctx).cond = expression(0);
				setState(196);
				match(T__7);
				setState(197);
				((StatementContext)_localctx).body = statement();
				setState(198);
				match(T__15);
				setState(199);
				((StatementContext)_localctx).elseBody = statement();

				        ((StatementContext)_localctx).n =  new IfElse(_localctx, ((StatementContext)_localctx).cond.n, ((StatementContext)_localctx).body.n, ((StatementContext)_localctx).elseBody.n);
				    
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
			setState(204);
			((DeclarationContext)_localctx).type = type();
			setState(205);
			((DeclarationContext)_localctx).decItem = decItem();
			((DeclarationContext)_localctx).items.add(((DeclarationContext)_localctx).decItem);
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(206);
				match(T__6);
				setState(207);
				((DeclarationContext)_localctx).decItem = decItem();
				((DeclarationContext)_localctx).items.add(((DeclarationContext)_localctx).decItem);
				}
				}
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(213);
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
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				((DecItemContext)_localctx).NAME = match(NAME);

				        ((DecItemContext)_localctx).n =  new Declaration(_localctx, (((DecItemContext)_localctx).NAME!=null?((DecItemContext)_localctx).NAME.getText():null), Optional.empty());
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				((DecItemContext)_localctx).NAME = match(NAME);
				setState(219);
				match(T__4);
				setState(220);
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
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(226);
				match(T__16);
				setState(227);
				match(T__5);
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 4260624203840L) != 0) {
					{
					setState(228);
					((ExpressionContext)_localctx).expression = expression(0);
					((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
					setState(233);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(229);
						match(T__6);
						setState(230);
						((ExpressionContext)_localctx).expression = expression(0);
						((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
						}
						}
						setState(235);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(238);
				match(T__7);

				        var prints = new ArrayList<Expression>();
				        for(var arg : ((ExpressionContext)_localctx).args)
				            prints.add(arg.n);

				        ((ExpressionContext)_localctx).n =  new Print(_localctx, prints);
				    
				}
				break;
			case 2:
				{
				setState(240);
				((ExpressionContext)_localctx).INT = match(INT);

				        ((ExpressionContext)_localctx).n =  new IntLiteral(_localctx, (((ExpressionContext)_localctx).INT!=null?((ExpressionContext)_localctx).INT.getText():null));
				    
				}
				break;
			case 3:
				{
				setState(242);
				((ExpressionContext)_localctx).DOUBLE = match(DOUBLE);

				        ((ExpressionContext)_localctx).n =  new DoubleLiteral(_localctx, (((ExpressionContext)_localctx).DOUBLE!=null?((ExpressionContext)_localctx).DOUBLE.getText():null));
				    
				}
				break;
			case 4:
				{
				setState(244);
				((ExpressionContext)_localctx).BOOLEAN = match(BOOLEAN);

				        ((ExpressionContext)_localctx).n =  new BooleanLiteral(_localctx, (((ExpressionContext)_localctx).BOOLEAN!=null?((ExpressionContext)_localctx).BOOLEAN.getText():null));
				    
				}
				break;
			case 5:
				{
				setState(246);
				match(T__17);

				        ((ExpressionContext)_localctx).n =  new This(_localctx);
				    
				}
				break;
			case 6:
				{
				setState(248);
				((ExpressionContext)_localctx).STRING = match(STRING);

				        ((ExpressionContext)_localctx).n =  new StringLiteral(_localctx, (((ExpressionContext)_localctx).STRING!=null?((ExpressionContext)_localctx).STRING.getText():null));
				    
				}
				break;
			case 7:
				{
				setState(250);
				((ExpressionContext)_localctx).NAME = match(NAME);

				        ((ExpressionContext)_localctx).n =  new VariableAccess(_localctx, (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null));
				    
				}
				break;
			case 8:
				{
				setState(252);
				match(T__18);
				setState(253);
				((ExpressionContext)_localctx).NAME = match(NAME);
				setState(254);
				match(T__5);
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 4260624203840L) != 0) {
					{
					setState(255);
					((ExpressionContext)_localctx).expression = expression(0);
					((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
					setState(260);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(256);
						match(T__6);
						setState(257);
						((ExpressionContext)_localctx).expression = expression(0);
						((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
						}
						}
						setState(262);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(265);
				match(T__7);

				        var constructorArgs = new ArrayList<Expression>();
				        for (var arg : ((ExpressionContext)_localctx).args)
				            constructorArgs.add(arg.n);
				        ((ExpressionContext)_localctx).n =  new ConstructorCall(_localctx, (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null), constructorArgs);
				    
				}
				break;
			case 9:
				{
				setState(267);
				((ExpressionContext)_localctx).NAME = match(NAME);
				setState(268);
				match(T__5);
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 4260624203840L) != 0) {
					{
					setState(269);
					((ExpressionContext)_localctx).expression = expression(0);
					((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
					setState(274);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__6) {
						{
						{
						setState(270);
						match(T__6);
						setState(271);
						((ExpressionContext)_localctx).expression = expression(0);
						((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
						}
						}
						setState(276);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(279);
				match(T__7);

				        var methodArgs = new ArrayList<Expression>();
				        for (var arg : ((ExpressionContext)_localctx).args)
				            methodArgs.add(arg.n);
				        ((ExpressionContext)_localctx).n =  new MethodCall(_localctx, Optional.empty(), (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null), methodArgs);
				    
				}
				break;
			case 10:
				{
				setState(281);
				match(T__5);
				setState(282);
				((ExpressionContext)_localctx).e = expression(0);
				setState(283);
				match(T__7);

				        ((ExpressionContext)_localctx).n =  ((ExpressionContext)_localctx).e.n;
				    
				}
				break;
			case 11:
				{
				setState(286);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 15728640L) != 0) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(287);
				((ExpressionContext)_localctx).e = expression(7);

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
				setState(290);
				match(T__5);
				setState(291);
				((ExpressionContext)_localctx).type = type();
				setState(292);
				match(T__7);
				setState(293);
				((ExpressionContext)_localctx).e = expression(6);

				        ((ExpressionContext)_localctx).n =  new Cast(_localctx, ((ExpressionContext)_localctx).type.n, ((ExpressionContext)_localctx).e.n);
				    
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(350);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(348);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(298);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(299);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 50331664L) != 0) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(300);
						((ExpressionContext)_localctx).r = expression(6);

						                  ((ExpressionContext)_localctx).n =  new BinaryOp(_localctx, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null), ((ExpressionContext)_localctx).l.n, ((ExpressionContext)_localctx).r.n);
						              
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(303);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(304);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__21 || _la==T__22) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(305);
						((ExpressionContext)_localctx).r = expression(5);

						                  ((ExpressionContext)_localctx).n =  new BinaryOp(_localctx, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null), ((ExpressionContext)_localctx).l.n, ((ExpressionContext)_localctx).r.n);
						              
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(308);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(309);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 1006632960L) != 0) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(310);
						((ExpressionContext)_localctx).r = expression(4);

						                  ((ExpressionContext)_localctx).n =  new RelationalOp(_localctx, ((ExpressionContext)_localctx).l.n, ((ExpressionContext)_localctx).r.n, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null));
						              
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(313);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(314);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__29 || _la==T__30) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(315);
						((ExpressionContext)_localctx).r = expression(3);

						                  ((ExpressionContext)_localctx).n =  new RelationalOp(_localctx, ((ExpressionContext)_localctx).l.n, ((ExpressionContext)_localctx).r.n, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null));
						              
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.l = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(318);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(319);
						match(T__4);
						setState(320);
						((ExpressionContext)_localctx).r = expression(1);

						                  ((ExpressionContext)_localctx).n =  new Assignment(_localctx, ((ExpressionContext)_localctx).l.n, ((ExpressionContext)_localctx).r.n);
						              
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(323);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(324);
						match(T__1);
						setState(325);
						((ExpressionContext)_localctx).NAME = match(NAME);

						                  ((ExpressionContext)_localctx).n =  new FieldAccess(_localctx, ((ExpressionContext)_localctx).e.n, (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null));
						              
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(327);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(329);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__1) {
							{
							setState(328);
							match(T__1);
							}
						}

						setState(331);
						((ExpressionContext)_localctx).NAME = match(NAME);
						setState(332);
						match(T__5);
						setState(341);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((_la) & ~0x3f) == 0 && ((1L << _la) & 4260624203840L) != 0) {
							{
							setState(333);
							((ExpressionContext)_localctx).expression = expression(0);
							((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
							setState(338);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__6) {
								{
								{
								setState(334);
								match(T__6);
								setState(335);
								((ExpressionContext)_localctx).expression = expression(0);
								((ExpressionContext)_localctx).args.add(((ExpressionContext)_localctx).expression);
								}
								}
								setState(340);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(343);
						match(T__7);

						                  var methodArgs = new ArrayList<Expression>();
						                  for (var arg : ((ExpressionContext)_localctx).args)
						                      methodArgs.add(arg.n);
						                      ((ExpressionContext)_localctx).n =  new MethodCall(_localctx, Optional.of(((ExpressionContext)_localctx).e.n), (((ExpressionContext)_localctx).NAME!=null?((ExpressionContext)_localctx).NAME.getText():null), methodArgs);
						              
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(345);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(346);
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

						                      ((ExpressionContext)_localctx).n =  new PostIncrement(_localctx, ((ExpressionContext)_localctx).e.n, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)); // (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null) may be ++ or --
						              
						}
						break;
					}
					} 
				}
				setState(352);
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
			setState(361);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__31:
				enterOuterAlt(_localctx, 1);
				{
				setState(353);
				match(T__31);

				        ((TypeContext)_localctx).n =  new TypeNode(_localctx, PrimitiveType.Int);
				    
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 2);
				{
				setState(355);
				match(T__32);

				        ((TypeContext)_localctx).n =  new TypeNode(_localctx, PrimitiveType.Double);
				    
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 3);
				{
				setState(357);
				match(T__33);

				        ((TypeContext)_localctx).n =  new TypeNode(_localctx, PrimitiveType.Boolean);
				    
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(359);
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
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		case 4:
			return precpred(_ctx, 1);
		case 5:
			return precpred(_ctx, 13);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001+\u016c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b\u00cb\b\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0005\t\u00d1\b\t\n\t\f\t\u00d4\t\t\u0001\t\u0001\t\u0001\t\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00e0\b\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u00e8\b\u000b\n\u000b\f\u000b\u00eb\t\u000b\u0003\u000b\u00ed\b"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0103\b\u000b\n\u000b\f\u000b"+
		"\u0106\t\u000b\u0003\u000b\u0108\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0111"+
		"\b\u000b\n\u000b\f\u000b\u0114\t\u000b\u0003\u000b\u0116\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0129"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u014a\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0151\b\u000b\n\u000b\f\u000b"+
		"\u0154\t\u000b\u0003\u000b\u0156\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u015d\b\u000b\n\u000b\f\u000b"+
		"\u0160\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0003\f\u016a\b\f\u0001\f\u0000\u0001\u0016\r\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0006\u0001\u0000"+
		"\u0014\u0017\u0002\u0000\u0004\u0004\u0018\u0019\u0001\u0000\u0016\u0017"+
		"\u0001\u0000\u001a\u001d\u0001\u0000\u001e\u001f\u0001\u0000\u0014\u0015"+
		"\u0196\u0000\u001a\u0001\u0000\u0000\u0000\u0002 \u0001\u0000\u0000\u0000"+
		"\u0004M\u0001\u0000\u0000\u0000\u0006[\u0001\u0000\u0000\u0000\b\u0083"+
		"\u0001\u0000\u0000\u0000\n\u0085\u0001\u0000\u0000\u0000\f\u0090\u0001"+
		"\u0000\u0000\u0000\u000e\u0095\u0001\u0000\u0000\u0000\u0010\u00ca\u0001"+
		"\u0000\u0000\u0000\u0012\u00cc\u0001\u0000\u0000\u0000\u0014\u00df\u0001"+
		"\u0000\u0000\u0000\u0016\u0128\u0001\u0000\u0000\u0000\u0018\u0169\u0001"+
		"\u0000\u0000\u0000\u001a\u001b\u0003\u0002\u0001\u0000\u001b\u001c\u0006"+
		"\u0000\uffff\uffff\u0000\u001c\u0001\u0001\u0000\u0000\u0000\u001d\u001f"+
		"\u0003\u0004\u0002\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f\"\u0001"+
		"\u0000\u0000\u0000 \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000"+
		"\u0000!&\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000\u0000#%\u0003\u0006"+
		"\u0003\u0000$#\u0001\u0000\u0000\u0000%(\u0001\u0000\u0000\u0000&$\u0001"+
		"\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000\',\u0001\u0000\u0000\u0000"+
		"(&\u0001\u0000\u0000\u0000)+\u0003\b\u0004\u0000*)\u0001\u0000\u0000\u0000"+
		"+.\u0001\u0000\u0000\u0000,*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000"+
		"\u0000-0\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000/1\u0003\n\u0005"+
		"\u00000/\u0001\u0000\u0000\u000001\u0001\u0000\u0000\u000012\u0001\u0000"+
		"\u0000\u000023\u0005\u0000\u0000\u000134\u0006\u0001\uffff\uffff\u0000"+
		"4\u0003\u0001\u0000\u0000\u000056\u0005\u0001\u0000\u00006;\u0005(\u0000"+
		"\u000078\u0005\u0002\u0000\u00008:\u0005(\u0000\u000097\u0001\u0000\u0000"+
		"\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000"+
		"\u0000\u0000<>\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>?\u0005"+
		"\u0003\u0000\u0000?N\u0006\u0002\uffff\uffff\u0000@A\u0005\u0001\u0000"+
		"\u0000AB\u0005(\u0000\u0000BG\u0005\u0002\u0000\u0000CD\u0005(\u0000\u0000"+
		"DF\u0005\u0002\u0000\u0000EC\u0001\u0000\u0000\u0000FI\u0001\u0000\u0000"+
		"\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000HJ\u0001\u0000"+
		"\u0000\u0000IG\u0001\u0000\u0000\u0000JK\u0005\u0004\u0000\u0000KL\u0005"+
		"\u0003\u0000\u0000LN\u0006\u0002\uffff\uffff\u0000M5\u0001\u0000\u0000"+
		"\u0000M@\u0001\u0000\u0000\u0000N\u0005\u0001\u0000\u0000\u0000OP\u0003"+
		"\u0018\f\u0000PQ\u0005(\u0000\u0000QR\u0005\u0003\u0000\u0000RS\u0006"+
		"\u0003\uffff\uffff\u0000S\\\u0001\u0000\u0000\u0000TU\u0003\u0018\f\u0000"+
		"UV\u0005(\u0000\u0000VW\u0005\u0005\u0000\u0000WX\u0003\u0016\u000b\u0000"+
		"XY\u0005\u0003\u0000\u0000YZ\u0006\u0003\uffff\uffff\u0000Z\\\u0001\u0000"+
		"\u0000\u0000[O\u0001\u0000\u0000\u0000[T\u0001\u0000\u0000\u0000\\\u0007"+
		"\u0001\u0000\u0000\u0000]^\u0003\u0018\f\u0000^_\u0005(\u0000\u0000_h"+
		"\u0005\u0006\u0000\u0000`e\u0003\u000e\u0007\u0000ab\u0005\u0007\u0000"+
		"\u0000bd\u0003\u000e\u0007\u0000ca\u0001\u0000\u0000\u0000dg\u0001\u0000"+
		"\u0000\u0000ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fi\u0001"+
		"\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000h`\u0001\u0000\u0000\u0000"+
		"hi\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0005\b\u0000\u0000"+
		"kl\u0005\t\u0000\u0000lm\u0003\f\u0006\u0000mn\u0005\n\u0000\u0000no\u0006"+
		"\u0004\uffff\uffff\u0000o\u0084\u0001\u0000\u0000\u0000pq\u0005\u000b"+
		"\u0000\u0000qr\u0005(\u0000\u0000r{\u0005\u0006\u0000\u0000sx\u0003\u000e"+
		"\u0007\u0000tu\u0005\u0007\u0000\u0000uw\u0003\u000e\u0007\u0000vt\u0001"+
		"\u0000\u0000\u0000wz\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000"+
		"xy\u0001\u0000\u0000\u0000y|\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000"+
		"\u0000{s\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|}\u0001\u0000"+
		"\u0000\u0000}~\u0005\b\u0000\u0000~\u007f\u0005\t\u0000\u0000\u007f\u0080"+
		"\u0003\f\u0006\u0000\u0080\u0081\u0005\n\u0000\u0000\u0081\u0082\u0006"+
		"\u0004\uffff\uffff\u0000\u0082\u0084\u0001\u0000\u0000\u0000\u0083]\u0001"+
		"\u0000\u0000\u0000\u0083p\u0001\u0000\u0000\u0000\u0084\t\u0001\u0000"+
		"\u0000\u0000\u0085\u0086\u0005\f\u0000\u0000\u0086\u0087\u0005\u0006\u0000"+
		"\u0000\u0087\u0088\u0005\b\u0000\u0000\u0088\u0089\u0005\t\u0000\u0000"+
		"\u0089\u008a\u0003\f\u0006\u0000\u008a\u008b\u0005\n\u0000\u0000\u008b"+
		"\u008c\u0006\u0005\uffff\uffff\u0000\u008c\u000b\u0001\u0000\u0000\u0000"+
		"\u008d\u008f\u0003\u0010\b\u0000\u008e\u008d\u0001\u0000\u0000\u0000\u008f"+
		"\u0092\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090"+
		"\u0091\u0001\u0000\u0000\u0000\u0091\u0093\u0001\u0000\u0000\u0000\u0092"+
		"\u0090\u0001\u0000\u0000\u0000\u0093\u0094\u0006\u0006\uffff\uffff\u0000"+
		"\u0094\r\u0001\u0000\u0000\u0000\u0095\u0096\u0003\u0018\f\u0000\u0096"+
		"\u0097\u0005(\u0000\u0000\u0097\u0098\u0006\u0007\uffff\uffff\u0000\u0098"+
		"\u000f\u0001\u0000\u0000\u0000\u0099\u009a\u0005\u0003\u0000\u0000\u009a"+
		"\u00cb\u0006\b\uffff\uffff\u0000\u009b\u009f\u0005\t\u0000\u0000\u009c"+
		"\u009e\u0003\u0010\b\u0000\u009d\u009c\u0001\u0000\u0000\u0000\u009e\u00a1"+
		"\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u009f"+
		"\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005\n\u0000\u0000\u00a3\u00cb\u0006"+
		"\b\uffff\uffff\u0000\u00a4\u00a5\u0003\u0012\t\u0000\u00a5\u00a6\u0006"+
		"\b\uffff\uffff\u0000\u00a6\u00cb\u0001\u0000\u0000\u0000\u00a7\u00a8\u0003"+
		"\u0016\u000b\u0000\u00a8\u00a9\u0005\u0003\u0000\u0000\u00a9\u00aa\u0006"+
		"\b\uffff\uffff\u0000\u00aa\u00cb\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005"+
		"\r\u0000\u0000\u00ac\u00ad\u0003\u0016\u000b\u0000\u00ad\u00ae\u0005\u0003"+
		"\u0000\u0000\u00ae\u00af\u0006\b\uffff\uffff\u0000\u00af\u00cb\u0001\u0000"+
		"\u0000\u0000\u00b0\u00b1\u0005\r\u0000\u0000\u00b1\u00b2\u0005\u0003\u0000"+
		"\u0000\u00b2\u00cb\u0006\b\uffff\uffff\u0000\u00b3\u00b4\u0005\u000e\u0000"+
		"\u0000\u00b4\u00b5\u0005\u0006\u0000\u0000\u00b5\u00b6\u0003\u0016\u000b"+
		"\u0000\u00b6\u00b7\u0005\b\u0000\u0000\u00b7\u00b8\u0003\u0010\b\u0000"+
		"\u00b8\u00b9\u0006\b\uffff\uffff\u0000\u00b9\u00cb\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bb\u0005\u000f\u0000\u0000\u00bb\u00bc\u0005\u0006\u0000\u0000"+
		"\u00bc\u00bd\u0003\u0016\u000b\u0000\u00bd\u00be\u0005\b\u0000\u0000\u00be"+
		"\u00bf\u0003\u0010\b\u0000\u00bf\u00c0\u0006\b\uffff\uffff\u0000\u00c0"+
		"\u00cb\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005\u000f\u0000\u0000\u00c2"+
		"\u00c3\u0005\u0006\u0000\u0000\u00c3\u00c4\u0003\u0016\u000b\u0000\u00c4"+
		"\u00c5\u0005\b\u0000\u0000\u00c5\u00c6\u0003\u0010\b\u0000\u00c6\u00c7"+
		"\u0005\u0010\u0000\u0000\u00c7\u00c8\u0003\u0010\b\u0000\u00c8\u00c9\u0006"+
		"\b\uffff\uffff\u0000\u00c9\u00cb\u0001\u0000\u0000\u0000\u00ca\u0099\u0001"+
		"\u0000\u0000\u0000\u00ca\u009b\u0001\u0000\u0000\u0000\u00ca\u00a4\u0001"+
		"\u0000\u0000\u0000\u00ca\u00a7\u0001\u0000\u0000\u0000\u00ca\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ca\u00b0\u0001\u0000\u0000\u0000\u00ca\u00b3\u0001"+
		"\u0000\u0000\u0000\u00ca\u00ba\u0001\u0000\u0000\u0000\u00ca\u00c1\u0001"+
		"\u0000\u0000\u0000\u00cb\u0011\u0001\u0000\u0000\u0000\u00cc\u00cd\u0003"+
		"\u0018\f\u0000\u00cd\u00d2\u0003\u0014\n\u0000\u00ce\u00cf\u0005\u0007"+
		"\u0000\u0000\u00cf\u00d1\u0003\u0014\n\u0000\u00d0\u00ce\u0001\u0000\u0000"+
		"\u0000\u00d1\u00d4\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000"+
		"\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005\u0003\u0000"+
		"\u0000\u00d6\u00d7\u0006\t\uffff\uffff\u0000\u00d7\u0013\u0001\u0000\u0000"+
		"\u0000\u00d8\u00d9\u0005(\u0000\u0000\u00d9\u00e0\u0006\n\uffff\uffff"+
		"\u0000\u00da\u00db\u0005(\u0000\u0000\u00db\u00dc\u0005\u0005\u0000\u0000"+
		"\u00dc\u00dd\u0003\u0016\u000b\u0000\u00dd\u00de\u0006\n\uffff\uffff\u0000"+
		"\u00de\u00e0\u0001\u0000\u0000\u0000\u00df\u00d8\u0001\u0000\u0000\u0000"+
		"\u00df\u00da\u0001\u0000\u0000\u0000\u00e0\u0015\u0001\u0000\u0000\u0000"+
		"\u00e1\u00e2\u0006\u000b\uffff\uffff\u0000\u00e2\u00e3\u0005\u0011\u0000"+
		"\u0000\u00e3\u00ec\u0005\u0006\u0000\u0000\u00e4\u00e9\u0003\u0016\u000b"+
		"\u0000\u00e5\u00e6\u0005\u0007\u0000\u0000\u00e6\u00e8\u0003\u0016\u000b"+
		"\u0000\u00e7\u00e5\u0001\u0000\u0000\u0000\u00e8\u00eb\u0001\u0000\u0000"+
		"\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000"+
		"\u0000\u00ea\u00ed\u0001\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000"+
		"\u0000\u00ec\u00e4\u0001\u0000\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000"+
		"\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005\b\u0000\u0000"+
		"\u00ef\u0129\u0006\u000b\uffff\uffff\u0000\u00f0\u00f1\u0005%\u0000\u0000"+
		"\u00f1\u0129\u0006\u000b\uffff\uffff\u0000\u00f2\u00f3\u0005&\u0000\u0000"+
		"\u00f3\u0129\u0006\u000b\uffff\uffff\u0000\u00f4\u00f5\u0005\'\u0000\u0000"+
		"\u00f5\u0129\u0006\u000b\uffff\uffff\u0000\u00f6\u00f7\u0005\u0012\u0000"+
		"\u0000\u00f7\u0129\u0006\u000b\uffff\uffff\u0000\u00f8\u00f9\u0005)\u0000"+
		"\u0000\u00f9\u0129\u0006\u000b\uffff\uffff\u0000\u00fa\u00fb\u0005(\u0000"+
		"\u0000\u00fb\u0129\u0006\u000b\uffff\uffff\u0000\u00fc\u00fd\u0005\u0013"+
		"\u0000\u0000\u00fd\u00fe\u0005(\u0000\u0000\u00fe\u0107\u0005\u0006\u0000"+
		"\u0000\u00ff\u0104\u0003\u0016\u000b\u0000\u0100\u0101\u0005\u0007\u0000"+
		"\u0000\u0101\u0103\u0003\u0016\u000b\u0000\u0102\u0100\u0001\u0000\u0000"+
		"\u0000\u0103\u0106\u0001\u0000\u0000\u0000\u0104\u0102\u0001\u0000\u0000"+
		"\u0000\u0104\u0105\u0001\u0000\u0000\u0000\u0105\u0108\u0001\u0000\u0000"+
		"\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0107\u00ff\u0001\u0000\u0000"+
		"\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000"+
		"\u0000\u0109\u010a\u0005\b\u0000\u0000\u010a\u0129\u0006\u000b\uffff\uffff"+
		"\u0000\u010b\u010c\u0005(\u0000\u0000\u010c\u0115\u0005\u0006\u0000\u0000"+
		"\u010d\u0112\u0003\u0016\u000b\u0000\u010e\u010f\u0005\u0007\u0000\u0000"+
		"\u010f\u0111\u0003\u0016\u000b\u0000\u0110\u010e\u0001\u0000\u0000\u0000"+
		"\u0111\u0114\u0001\u0000\u0000\u0000\u0112\u0110\u0001\u0000\u0000\u0000"+
		"\u0112\u0113\u0001\u0000\u0000\u0000\u0113\u0116\u0001\u0000\u0000\u0000"+
		"\u0114\u0112\u0001\u0000\u0000\u0000\u0115\u010d\u0001\u0000\u0000\u0000"+
		"\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0005\b\u0000\u0000\u0118\u0129\u0006\u000b\uffff\uffff\u0000"+
		"\u0119\u011a\u0005\u0006\u0000\u0000\u011a\u011b\u0003\u0016\u000b\u0000"+
		"\u011b\u011c\u0005\b\u0000\u0000\u011c\u011d\u0006\u000b\uffff\uffff\u0000"+
		"\u011d\u0129\u0001\u0000\u0000\u0000\u011e\u011f\u0007\u0000\u0000\u0000"+
		"\u011f\u0120\u0003\u0016\u000b\u0007\u0120\u0121\u0006\u000b\uffff\uffff"+
		"\u0000\u0121\u0129\u0001\u0000\u0000\u0000\u0122\u0123\u0005\u0006\u0000"+
		"\u0000\u0123\u0124\u0003\u0018\f\u0000\u0124\u0125\u0005\b\u0000\u0000"+
		"\u0125\u0126\u0003\u0016\u000b\u0006\u0126\u0127\u0006\u000b\uffff\uffff"+
		"\u0000\u0127\u0129\u0001\u0000\u0000\u0000\u0128\u00e1\u0001\u0000\u0000"+
		"\u0000\u0128\u00f0\u0001\u0000\u0000\u0000\u0128\u00f2\u0001\u0000\u0000"+
		"\u0000\u0128\u00f4\u0001\u0000\u0000\u0000\u0128\u00f6\u0001\u0000\u0000"+
		"\u0000\u0128\u00f8\u0001\u0000\u0000\u0000\u0128\u00fa\u0001\u0000\u0000"+
		"\u0000\u0128\u00fc\u0001\u0000\u0000\u0000\u0128\u010b\u0001\u0000\u0000"+
		"\u0000\u0128\u0119\u0001\u0000\u0000\u0000\u0128\u011e\u0001\u0000\u0000"+
		"\u0000\u0128\u0122\u0001\u0000\u0000\u0000\u0129\u015e\u0001\u0000\u0000"+
		"\u0000\u012a\u012b\n\u0005\u0000\u0000\u012b\u012c\u0007\u0001\u0000\u0000"+
		"\u012c\u012d\u0003\u0016\u000b\u0006\u012d\u012e\u0006\u000b\uffff\uffff"+
		"\u0000\u012e\u015d\u0001\u0000\u0000\u0000\u012f\u0130\n\u0004\u0000\u0000"+
		"\u0130\u0131\u0007\u0002\u0000\u0000\u0131\u0132\u0003\u0016\u000b\u0005"+
		"\u0132\u0133\u0006\u000b\uffff\uffff\u0000\u0133\u015d\u0001\u0000\u0000"+
		"\u0000\u0134\u0135\n\u0003\u0000\u0000\u0135\u0136\u0007\u0003\u0000\u0000"+
		"\u0136\u0137\u0003\u0016\u000b\u0004\u0137\u0138\u0006\u000b\uffff\uffff"+
		"\u0000\u0138\u015d\u0001\u0000\u0000\u0000\u0139\u013a\n\u0002\u0000\u0000"+
		"\u013a\u013b\u0007\u0004\u0000\u0000\u013b\u013c\u0003\u0016\u000b\u0003"+
		"\u013c\u013d\u0006\u000b\uffff\uffff\u0000\u013d\u015d\u0001\u0000\u0000"+
		"\u0000\u013e\u013f\n\u0001\u0000\u0000\u013f\u0140\u0005\u0005\u0000\u0000"+
		"\u0140\u0141\u0003\u0016\u000b\u0001\u0141\u0142\u0006\u000b\uffff\uffff"+
		"\u0000\u0142\u015d\u0001\u0000\u0000\u0000\u0143\u0144\n\r\u0000\u0000"+
		"\u0144\u0145\u0005\u0002\u0000\u0000\u0145\u0146\u0005(\u0000\u0000\u0146"+
		"\u015d\u0006\u000b\uffff\uffff\u0000\u0147\u0149\n\u000b\u0000\u0000\u0148"+
		"\u014a\u0005\u0002\u0000\u0000\u0149\u0148\u0001\u0000\u0000\u0000\u0149"+
		"\u014a\u0001\u0000\u0000\u0000\u014a\u014b\u0001\u0000\u0000\u0000\u014b"+
		"\u014c\u0005(\u0000\u0000\u014c\u0155\u0005\u0006\u0000\u0000\u014d\u0152"+
		"\u0003\u0016\u000b\u0000\u014e\u014f\u0005\u0007\u0000\u0000\u014f\u0151"+
		"\u0003\u0016\u000b\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0151\u0154"+
		"\u0001\u0000\u0000\u0000\u0152\u0150\u0001\u0000\u0000\u0000\u0152\u0153"+
		"\u0001\u0000\u0000\u0000\u0153\u0156\u0001\u0000\u0000\u0000\u0154\u0152"+
		"\u0001\u0000\u0000\u0000\u0155\u014d\u0001\u0000\u0000\u0000\u0155\u0156"+
		"\u0001\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000\u0157\u0158"+
		"\u0005\b\u0000\u0000\u0158\u015d\u0006\u000b\uffff\uffff\u0000\u0159\u015a"+
		"\n\b\u0000\u0000\u015a\u015b\u0007\u0005\u0000\u0000\u015b\u015d\u0006"+
		"\u000b\uffff\uffff\u0000\u015c\u012a\u0001\u0000\u0000\u0000\u015c\u012f"+
		"\u0001\u0000\u0000\u0000\u015c\u0134\u0001\u0000\u0000\u0000\u015c\u0139"+
		"\u0001\u0000\u0000\u0000\u015c\u013e\u0001\u0000\u0000\u0000\u015c\u0143"+
		"\u0001\u0000\u0000\u0000\u015c\u0147\u0001\u0000\u0000\u0000\u015c\u0159"+
		"\u0001\u0000\u0000\u0000\u015d\u0160\u0001\u0000\u0000\u0000\u015e\u015c"+
		"\u0001\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015f\u0017"+
		"\u0001\u0000\u0000\u0000\u0160\u015e\u0001\u0000\u0000\u0000\u0161\u0162"+
		"\u0005 \u0000\u0000\u0162\u016a\u0006\f\uffff\uffff\u0000\u0163\u0164"+
		"\u0005!\u0000\u0000\u0164\u016a\u0006\f\uffff\uffff\u0000\u0165\u0166"+
		"\u0005\"\u0000\u0000\u0166\u016a\u0006\f\uffff\uffff\u0000\u0167\u0168"+
		"\u0005(\u0000\u0000\u0168\u016a\u0006\f\uffff\uffff\u0000\u0169\u0161"+
		"\u0001\u0000\u0000\u0000\u0169\u0163\u0001\u0000\u0000\u0000\u0169\u0165"+
		"\u0001\u0000\u0000\u0000\u0169\u0167\u0001\u0000\u0000\u0000\u016a\u0019"+
		"\u0001\u0000\u0000\u0000\u001f &,0;GM[ehx{\u0083\u0090\u009f\u00ca\u00d2"+
		"\u00df\u00e9\u00ec\u0104\u0107\u0112\u0115\u0128\u0149\u0152\u0155\u015c"+
		"\u015e\u0169";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
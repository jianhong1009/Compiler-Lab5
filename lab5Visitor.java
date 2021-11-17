// Generated from lab5.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link lab5Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface lab5Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link lab5Parser#compUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompUnit(lab5Parser.CompUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(lab5Parser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#funcType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncType(lab5Parser.FuncTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(lab5Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(lab5Parser.BlockItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(lab5Parser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#return_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_(lab5Parser.Return_Context ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#lVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLVal(lab5Parser.LValContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(lab5Parser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#constDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(lab5Parser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#bType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBType(lab5Parser.BTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#constDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDef(lab5Parser.ConstDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#constInitVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstInitVal(lab5Parser.ConstInitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#constExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExp(lab5Parser.ConstExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(lab5Parser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(lab5Parser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#initVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitVal(lab5Parser.InitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(lab5Parser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(lab5Parser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#lOrExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOrExp(lab5Parser.LOrExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#lAndExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLAndExp(lab5Parser.LAndExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#eqExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExp(lab5Parser.EqExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#eqNeq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqNeq(lab5Parser.EqNeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#relExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExp(lab5Parser.RelExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(lab5Parser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#addExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(lab5Parser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#addSub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(lab5Parser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#mulExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExp(lab5Parser.MulExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#mulDiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(lab5Parser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(lab5Parser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#funcRParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncRParams(lab5Parser.FuncRParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(lab5Parser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(lab5Parser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(lab5Parser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab5Parser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(lab5Parser.IdentContext ctx);
}
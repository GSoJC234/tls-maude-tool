// Generated from /Users/gsojc234/git/mta.maude-tls-attacker/src/mta.scenario/antlr/Scenario.g4 by ANTLR 4.13.2
package mta.scenario.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ScenarioParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ScenarioVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ScenarioParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ScenarioParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(ScenarioParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(ScenarioParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(ScenarioParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(ScenarioParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(ScenarioParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ScenarioParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#nonce}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonce(ScenarioParser.NonceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(ScenarioParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#function_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_name(ScenarioParser.Function_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#maude_constant_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaude_constant_list(ScenarioParser.Maude_constant_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#maude_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaude_constant(ScenarioParser.Maude_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#alert_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlert_constant(ScenarioParser.Alert_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#alert_level}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlert_level(ScenarioParser.Alert_levelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#alert_description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlert_description(ScenarioParser.Alert_descriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#protocol_type_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocol_type_constant(ScenarioParser.Protocol_type_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#protocol_version_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocol_version_constant(ScenarioParser.Protocol_version_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#handshake_type_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandshake_type_constant(ScenarioParser.Handshake_type_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#ciphersuite_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCiphersuite_constant(ScenarioParser.Ciphersuite_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#compression_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompression_constant(ScenarioParser.Compression_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#signature_and_hash_algorithm_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature_and_hash_algorithm_constant(ScenarioParser.Signature_and_hash_algorithm_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#signature_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature_constant(ScenarioParser.Signature_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#hash_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHash_constant(ScenarioParser.Hash_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#named_group_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamed_group_constant(ScenarioParser.Named_group_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#psk_key_exchange_mode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPsk_key_exchange_mode(ScenarioParser.Psk_key_exchange_modeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#msg_size_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMsg_size_constant(ScenarioParser.Msg_size_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#curve_type_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurve_type_constant(ScenarioParser.Curve_type_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#certificate_type_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCertificate_type_constant(ScenarioParser.Certificate_type_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#other_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOther_constant(ScenarioParser.Other_constantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioParser#long_constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLong_constant(ScenarioParser.Long_constantContext ctx);
}
// Generated from Scenario.g4 by ANTLR 4.13.2

package mta.scenario.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ScenarioParser}.
 */
public interface ScenarioListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ScenarioParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ScenarioParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ScenarioParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ScenarioParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(ScenarioParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(ScenarioParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(ScenarioParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(ScenarioParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(ScenarioParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(ScenarioParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(ScenarioParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(ScenarioParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(ScenarioParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(ScenarioParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ScenarioParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ScenarioParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#nonce}.
	 * @param ctx the parse tree
	 */
	void enterNonce(ScenarioParser.NonceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#nonce}.
	 * @param ctx the parse tree
	 */
	void exitNonce(ScenarioParser.NonceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ScenarioParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ScenarioParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(ScenarioParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(ScenarioParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#maude_constant_list}.
	 * @param ctx the parse tree
	 */
	void enterMaude_constant_list(ScenarioParser.Maude_constant_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#maude_constant_list}.
	 * @param ctx the parse tree
	 */
	void exitMaude_constant_list(ScenarioParser.Maude_constant_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#maude_constant}.
	 * @param ctx the parse tree
	 */
	void enterMaude_constant(ScenarioParser.Maude_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#maude_constant}.
	 * @param ctx the parse tree
	 */
	void exitMaude_constant(ScenarioParser.Maude_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#alert_constant}.
	 * @param ctx the parse tree
	 */
	void enterAlert_constant(ScenarioParser.Alert_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#alert_constant}.
	 * @param ctx the parse tree
	 */
	void exitAlert_constant(ScenarioParser.Alert_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#alert_level}.
	 * @param ctx the parse tree
	 */
	void enterAlert_level(ScenarioParser.Alert_levelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#alert_level}.
	 * @param ctx the parse tree
	 */
	void exitAlert_level(ScenarioParser.Alert_levelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#alert_description}.
	 * @param ctx the parse tree
	 */
	void enterAlert_description(ScenarioParser.Alert_descriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#alert_description}.
	 * @param ctx the parse tree
	 */
	void exitAlert_description(ScenarioParser.Alert_descriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#protocol_type_constant}.
	 * @param ctx the parse tree
	 */
	void enterProtocol_type_constant(ScenarioParser.Protocol_type_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#protocol_type_constant}.
	 * @param ctx the parse tree
	 */
	void exitProtocol_type_constant(ScenarioParser.Protocol_type_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#protocol_version_constant}.
	 * @param ctx the parse tree
	 */
	void enterProtocol_version_constant(ScenarioParser.Protocol_version_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#protocol_version_constant}.
	 * @param ctx the parse tree
	 */
	void exitProtocol_version_constant(ScenarioParser.Protocol_version_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#handshake_type_constant}.
	 * @param ctx the parse tree
	 */
	void enterHandshake_type_constant(ScenarioParser.Handshake_type_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#handshake_type_constant}.
	 * @param ctx the parse tree
	 */
	void exitHandshake_type_constant(ScenarioParser.Handshake_type_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#ciphersuite_constant}.
	 * @param ctx the parse tree
	 */
	void enterCiphersuite_constant(ScenarioParser.Ciphersuite_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#ciphersuite_constant}.
	 * @param ctx the parse tree
	 */
	void exitCiphersuite_constant(ScenarioParser.Ciphersuite_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#compression_constant}.
	 * @param ctx the parse tree
	 */
	void enterCompression_constant(ScenarioParser.Compression_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#compression_constant}.
	 * @param ctx the parse tree
	 */
	void exitCompression_constant(ScenarioParser.Compression_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#signature_and_hash_algorithm_constant}.
	 * @param ctx the parse tree
	 */
	void enterSignature_and_hash_algorithm_constant(ScenarioParser.Signature_and_hash_algorithm_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#signature_and_hash_algorithm_constant}.
	 * @param ctx the parse tree
	 */
	void exitSignature_and_hash_algorithm_constant(ScenarioParser.Signature_and_hash_algorithm_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#signature_constant}.
	 * @param ctx the parse tree
	 */
	void enterSignature_constant(ScenarioParser.Signature_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#signature_constant}.
	 * @param ctx the parse tree
	 */
	void exitSignature_constant(ScenarioParser.Signature_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#hash_constant}.
	 * @param ctx the parse tree
	 */
	void enterHash_constant(ScenarioParser.Hash_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#hash_constant}.
	 * @param ctx the parse tree
	 */
	void exitHash_constant(ScenarioParser.Hash_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#named_group_constant}.
	 * @param ctx the parse tree
	 */
	void enterNamed_group_constant(ScenarioParser.Named_group_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#named_group_constant}.
	 * @param ctx the parse tree
	 */
	void exitNamed_group_constant(ScenarioParser.Named_group_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#psk_key_exchange_mode}.
	 * @param ctx the parse tree
	 */
	void enterPsk_key_exchange_mode(ScenarioParser.Psk_key_exchange_modeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#psk_key_exchange_mode}.
	 * @param ctx the parse tree
	 */
	void exitPsk_key_exchange_mode(ScenarioParser.Psk_key_exchange_modeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#msg_size_constant}.
	 * @param ctx the parse tree
	 */
	void enterMsg_size_constant(ScenarioParser.Msg_size_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#msg_size_constant}.
	 * @param ctx the parse tree
	 */
	void exitMsg_size_constant(ScenarioParser.Msg_size_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#curve_type_constant}.
	 * @param ctx the parse tree
	 */
	void enterCurve_type_constant(ScenarioParser.Curve_type_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#curve_type_constant}.
	 * @param ctx the parse tree
	 */
	void exitCurve_type_constant(ScenarioParser.Curve_type_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#certificate_type_constant}.
	 * @param ctx the parse tree
	 */
	void enterCertificate_type_constant(ScenarioParser.Certificate_type_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#certificate_type_constant}.
	 * @param ctx the parse tree
	 */
	void exitCertificate_type_constant(ScenarioParser.Certificate_type_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#other_constant}.
	 * @param ctx the parse tree
	 */
	void enterOther_constant(ScenarioParser.Other_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#other_constant}.
	 * @param ctx the parse tree
	 */
	void exitOther_constant(ScenarioParser.Other_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#number_constant}.
	 * @param ctx the parse tree
	 */
	void enterNumber_constant(ScenarioParser.Number_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#number_constant}.
	 * @param ctx the parse tree
	 */
	void exitNumber_constant(ScenarioParser.Number_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioParser#long_constant}.
	 * @param ctx the parse tree
	 */
	void enterLong_constant(ScenarioParser.Long_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioParser#long_constant}.
	 * @param ctx the parse tree
	 */
	void exitLong_constant(ScenarioParser.Long_constantContext ctx);
}
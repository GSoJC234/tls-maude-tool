// Generated from /Users/gsojc234/git/maude-tls-attacker/src/mta/user/profile/antlr/TLSProfile.g4 by ANTLR 4.13.2
package mta.user.profile.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TLSProfileParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TLSProfileVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#profile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProfile(TLSProfileParser.ProfileContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#profileEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProfileEntry(TLSProfileParser.ProfileEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#testRoleEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestRoleEntry(TLSProfileParser.TestRoleEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#tlsRoleEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTlsRoleEntry(TLSProfileParser.TlsRoleEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#versionEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersionEntry(TLSProfileParser.VersionEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#cipherSuitesEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCipherSuitesEntry(TLSProfileParser.CipherSuitesEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#compressionsEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompressionsEntry(TLSProfileParser.CompressionsEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#certificateTypesEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCertificateTypesEntry(TLSProfileParser.CertificateTypesEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#certificateAlgosEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCertificateAlgosEntry(TLSProfileParser.CertificateAlgosEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#certificateEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCertificateEntry(TLSProfileParser.CertificateEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#privateKeyEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrivateKeyEntry(TLSProfileParser.PrivateKeyEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#caCertificateEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaCertificateEntry(TLSProfileParser.CaCertificateEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#supportedGroupsEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportedGroupsEntry(TLSProfileParser.SupportedGroupsEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#signatureAlgorithmsEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignatureAlgorithmsEntry(TLSProfileParser.SignatureAlgorithmsEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#keySharesEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeySharesEntry(TLSProfileParser.KeySharesEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#supportedVersionsEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportedVersionsEntry(TLSProfileParser.SupportedVersionsEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#pskKeyExchangeModesEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPskKeyExchangeModesEntry(TLSProfileParser.PskKeyExchangeModesEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#newSessionTicketReqEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewSessionTicketReqEntry(TLSProfileParser.NewSessionTicketReqEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#newSessionTicketWaitEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewSessionTicketWaitEntry(TLSProfileParser.NewSessionTicketWaitEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#earlyDataReqEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEarlyDataReqEntry(TLSProfileParser.EarlyDataReqEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#postClientAuthReqEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostClientAuthReqEntry(TLSProfileParser.PostClientAuthReqEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#keyUpdateReqEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyUpdateReqEntry(TLSProfileParser.KeyUpdateReqEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#keyUpdateWaitEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyUpdateWaitEntry(TLSProfileParser.KeyUpdateWaitEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#certificateRequestEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCertificateRequestEntry(TLSProfileParser.CertificateRequestEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#executionConfigurationEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutionConfigurationEntry(TLSProfileParser.ExecutionConfigurationEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#executionConfigurationField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutionConfigurationField(TLSProfileParser.ExecutionConfigurationFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#executionConfigurationName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutionConfigurationName(TLSProfileParser.ExecutionConfigurationNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#executionConfigurationPath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutionConfigurationPath(TLSProfileParser.ExecutionConfigurationPathContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#valueList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueList(TLSProfileParser.ValueListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#booleanValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValue(TLSProfileParser.BooleanValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#pathValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathValue(TLSProfileParser.PathValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#scalarValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarValue(TLSProfileParser.ScalarValueContext ctx);
}
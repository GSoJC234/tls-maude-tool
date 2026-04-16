// Generated from /Users/gsojc234/git/maude-tls-attacker/src/mta/user/profile/antlr/TLSProfile.g4 by ANTLR 4.13.2
package mta.user.profile.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TLSProfileParser}.
 */
public interface TLSProfileListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#profile}.
	 * @param ctx the parse tree
	 */
	void enterProfile(TLSProfileParser.ProfileContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#profile}.
	 * @param ctx the parse tree
	 */
	void exitProfile(TLSProfileParser.ProfileContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#profileEntry}.
	 * @param ctx the parse tree
	 */
	void enterProfileEntry(TLSProfileParser.ProfileEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#profileEntry}.
	 * @param ctx the parse tree
	 */
	void exitProfileEntry(TLSProfileParser.ProfileEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#testRoleEntry}.
	 * @param ctx the parse tree
	 */
	void enterTestRoleEntry(TLSProfileParser.TestRoleEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#testRoleEntry}.
	 * @param ctx the parse tree
	 */
	void exitTestRoleEntry(TLSProfileParser.TestRoleEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#tlsRoleEntry}.
	 * @param ctx the parse tree
	 */
	void enterTlsRoleEntry(TLSProfileParser.TlsRoleEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#tlsRoleEntry}.
	 * @param ctx the parse tree
	 */
	void exitTlsRoleEntry(TLSProfileParser.TlsRoleEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#versionEntry}.
	 * @param ctx the parse tree
	 */
	void enterVersionEntry(TLSProfileParser.VersionEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#versionEntry}.
	 * @param ctx the parse tree
	 */
	void exitVersionEntry(TLSProfileParser.VersionEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#cipherSuitesEntry}.
	 * @param ctx the parse tree
	 */
	void enterCipherSuitesEntry(TLSProfileParser.CipherSuitesEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#cipherSuitesEntry}.
	 * @param ctx the parse tree
	 */
	void exitCipherSuitesEntry(TLSProfileParser.CipherSuitesEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#compressionsEntry}.
	 * @param ctx the parse tree
	 */
	void enterCompressionsEntry(TLSProfileParser.CompressionsEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#compressionsEntry}.
	 * @param ctx the parse tree
	 */
	void exitCompressionsEntry(TLSProfileParser.CompressionsEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#certificateTypesEntry}.
	 * @param ctx the parse tree
	 */
	void enterCertificateTypesEntry(TLSProfileParser.CertificateTypesEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#certificateTypesEntry}.
	 * @param ctx the parse tree
	 */
	void exitCertificateTypesEntry(TLSProfileParser.CertificateTypesEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#certificateAlgosEntry}.
	 * @param ctx the parse tree
	 */
	void enterCertificateAlgosEntry(TLSProfileParser.CertificateAlgosEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#certificateAlgosEntry}.
	 * @param ctx the parse tree
	 */
	void exitCertificateAlgosEntry(TLSProfileParser.CertificateAlgosEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#certificateEntry}.
	 * @param ctx the parse tree
	 */
	void enterCertificateEntry(TLSProfileParser.CertificateEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#certificateEntry}.
	 * @param ctx the parse tree
	 */
	void exitCertificateEntry(TLSProfileParser.CertificateEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#privateKeyEntry}.
	 * @param ctx the parse tree
	 */
	void enterPrivateKeyEntry(TLSProfileParser.PrivateKeyEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#privateKeyEntry}.
	 * @param ctx the parse tree
	 */
	void exitPrivateKeyEntry(TLSProfileParser.PrivateKeyEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#caCertificateEntry}.
	 * @param ctx the parse tree
	 */
	void enterCaCertificateEntry(TLSProfileParser.CaCertificateEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#caCertificateEntry}.
	 * @param ctx the parse tree
	 */
	void exitCaCertificateEntry(TLSProfileParser.CaCertificateEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#supportedGroupsEntry}.
	 * @param ctx the parse tree
	 */
	void enterSupportedGroupsEntry(TLSProfileParser.SupportedGroupsEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#supportedGroupsEntry}.
	 * @param ctx the parse tree
	 */
	void exitSupportedGroupsEntry(TLSProfileParser.SupportedGroupsEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#signatureAlgorithmsEntry}.
	 * @param ctx the parse tree
	 */
	void enterSignatureAlgorithmsEntry(TLSProfileParser.SignatureAlgorithmsEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#signatureAlgorithmsEntry}.
	 * @param ctx the parse tree
	 */
	void exitSignatureAlgorithmsEntry(TLSProfileParser.SignatureAlgorithmsEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#keySharesEntry}.
	 * @param ctx the parse tree
	 */
	void enterKeySharesEntry(TLSProfileParser.KeySharesEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#keySharesEntry}.
	 * @param ctx the parse tree
	 */
	void exitKeySharesEntry(TLSProfileParser.KeySharesEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#supportedVersionsEntry}.
	 * @param ctx the parse tree
	 */
	void enterSupportedVersionsEntry(TLSProfileParser.SupportedVersionsEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#supportedVersionsEntry}.
	 * @param ctx the parse tree
	 */
	void exitSupportedVersionsEntry(TLSProfileParser.SupportedVersionsEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#pskKeyExchangeModesEntry}.
	 * @param ctx the parse tree
	 */
	void enterPskKeyExchangeModesEntry(TLSProfileParser.PskKeyExchangeModesEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#pskKeyExchangeModesEntry}.
	 * @param ctx the parse tree
	 */
	void exitPskKeyExchangeModesEntry(TLSProfileParser.PskKeyExchangeModesEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#newSessionTicketReqEntry}.
	 * @param ctx the parse tree
	 */
	void enterNewSessionTicketReqEntry(TLSProfileParser.NewSessionTicketReqEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#newSessionTicketReqEntry}.
	 * @param ctx the parse tree
	 */
	void exitNewSessionTicketReqEntry(TLSProfileParser.NewSessionTicketReqEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#newSessionTicketWaitEntry}.
	 * @param ctx the parse tree
	 */
	void enterNewSessionTicketWaitEntry(TLSProfileParser.NewSessionTicketWaitEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#newSessionTicketWaitEntry}.
	 * @param ctx the parse tree
	 */
	void exitNewSessionTicketWaitEntry(TLSProfileParser.NewSessionTicketWaitEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#earlyDataReqEntry}.
	 * @param ctx the parse tree
	 */
	void enterEarlyDataReqEntry(TLSProfileParser.EarlyDataReqEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#earlyDataReqEntry}.
	 * @param ctx the parse tree
	 */
	void exitEarlyDataReqEntry(TLSProfileParser.EarlyDataReqEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#postClientAuthReqEntry}.
	 * @param ctx the parse tree
	 */
	void enterPostClientAuthReqEntry(TLSProfileParser.PostClientAuthReqEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#postClientAuthReqEntry}.
	 * @param ctx the parse tree
	 */
	void exitPostClientAuthReqEntry(TLSProfileParser.PostClientAuthReqEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#keyUpdateReqEntry}.
	 * @param ctx the parse tree
	 */
	void enterKeyUpdateReqEntry(TLSProfileParser.KeyUpdateReqEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#keyUpdateReqEntry}.
	 * @param ctx the parse tree
	 */
	void exitKeyUpdateReqEntry(TLSProfileParser.KeyUpdateReqEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#keyUpdateWaitEntry}.
	 * @param ctx the parse tree
	 */
	void enterKeyUpdateWaitEntry(TLSProfileParser.KeyUpdateWaitEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#keyUpdateWaitEntry}.
	 * @param ctx the parse tree
	 */
	void exitKeyUpdateWaitEntry(TLSProfileParser.KeyUpdateWaitEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#certificateRequestEntry}.
	 * @param ctx the parse tree
	 */
	void enterCertificateRequestEntry(TLSProfileParser.CertificateRequestEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#certificateRequestEntry}.
	 * @param ctx the parse tree
	 */
	void exitCertificateRequestEntry(TLSProfileParser.CertificateRequestEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#executionConfigurationEntry}.
	 * @param ctx the parse tree
	 */
	void enterExecutionConfigurationEntry(TLSProfileParser.ExecutionConfigurationEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#executionConfigurationEntry}.
	 * @param ctx the parse tree
	 */
	void exitExecutionConfigurationEntry(TLSProfileParser.ExecutionConfigurationEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#executionConfigurationField}.
	 * @param ctx the parse tree
	 */
	void enterExecutionConfigurationField(TLSProfileParser.ExecutionConfigurationFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#executionConfigurationField}.
	 * @param ctx the parse tree
	 */
	void exitExecutionConfigurationField(TLSProfileParser.ExecutionConfigurationFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#executionConfigurationName}.
	 * @param ctx the parse tree
	 */
	void enterExecutionConfigurationName(TLSProfileParser.ExecutionConfigurationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#executionConfigurationName}.
	 * @param ctx the parse tree
	 */
	void exitExecutionConfigurationName(TLSProfileParser.ExecutionConfigurationNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#executionConfigurationPath}.
	 * @param ctx the parse tree
	 */
	void enterExecutionConfigurationPath(TLSProfileParser.ExecutionConfigurationPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#executionConfigurationPath}.
	 * @param ctx the parse tree
	 */
	void exitExecutionConfigurationPath(TLSProfileParser.ExecutionConfigurationPathContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#valueList}.
	 * @param ctx the parse tree
	 */
	void enterValueList(TLSProfileParser.ValueListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#valueList}.
	 * @param ctx the parse tree
	 */
	void exitValueList(TLSProfileParser.ValueListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanValue(TLSProfileParser.BooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanValue(TLSProfileParser.BooleanValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#pathValue}.
	 * @param ctx the parse tree
	 */
	void enterPathValue(TLSProfileParser.PathValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#pathValue}.
	 * @param ctx the parse tree
	 */
	void exitPathValue(TLSProfileParser.PathValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#scalarValue}.
	 * @param ctx the parse tree
	 */
	void enterScalarValue(TLSProfileParser.ScalarValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#scalarValue}.
	 * @param ctx the parse tree
	 */
	void exitScalarValue(TLSProfileParser.ScalarValueContext ctx);
}
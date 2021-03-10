package com.github.danieldeng2.waccplugin.language

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.TokenIElementType
import com.github.danieldeng2.waccplugin.language.parser.*

class WaccSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
        WaccSyntaxHighlighter()
}

class WaccSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer =
        ANTLRLexerAdaptor(WaccLanguage, WACCLexer(null))

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey?> {
        if (tokenType !is TokenIElementType) return EMPTY_KEYS

        return when (tokenType.antlrTokenType) {
            WACCLexer.BEGIN,
            WACCLexer.END,
            WACCLexer.IS,
            WACCLexer.SKIP_,
            WACCLexer.READ,
            WACCLexer.FREE,
            WACCLexer.RETURN,
            WACCLexer.EXIT,
            WACCLexer.PRINT,
            WACCLexer.PRINTLN,
            WACCLexer.IF,
            WACCLexer.THEN,
            WACCLexer.ELSE,
            WACCLexer.FI,
            WACCLexer.WHILE,
            WACCLexer.DO,
            WACCLexer.DONE,
            WACCLexer.CALL ->
                KEYWORD_KEYS
            WACCLexer.COMMENT ->
                COMMENT_KEYS
            WACCLexer.ERRCHAR ->
                BAD_CHAR_KEYS
            else -> {
                EMPTY_KEYS
            }
        }


    }

    companion object {
        private val KEYWORD =
            TextAttributesKey.createTextAttributesKey("WACC_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        private val STRING = TextAttributesKey.createTextAttributesKey("WACC_STRING", DefaultLanguageHighlighterColors.STRING)

        private val COMMENT =
            TextAttributesKey.createTextAttributesKey("WACC_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)

        private val BAD_CHARACTER =
            TextAttributesKey.createTextAttributesKey("WACC_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val STRING_KEYS = arrayOf(STRING)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOfNulls<TextAttributesKey>(0)
    }
}

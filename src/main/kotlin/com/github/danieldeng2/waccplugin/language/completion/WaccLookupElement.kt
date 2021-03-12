package com.github.danieldeng2.waccplugin.language.completion

import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorModificationUtil

class WaccLookupElement(title: String) {
    private var element = LookupElementBuilder.create(title)
    private val lines: MutableList<String> = mutableListOf()
    private var directlyAfter = ""

    var insertColon = true
    var typeText = "Statement"

    fun text(text: String) {
        directlyAfter = text
    }

    fun line(lineText: String) {
        lines.add(lineText)
    }

    fun assemble(): LookupElementBuilder =
        element
            .withTypeText(typeText)
            .withInsertHandler { ctx, _ ->
                val editor = ctx.editor
                val indentedLine = indentedNewLine(ctx.editor)
                val addColon = insertColon && toInsertColon(editor)

                EditorModificationUtil.insertStringAtCaret(editor, directlyAfter)

                lines.forEach {
                    EditorModificationUtil.insertStringAtCaret(editor, "$indentedLine$it")
                }

                if (addColon) EditorModificationUtil.insertStringAtCaret(
                    editor,
                    " ;"
                )
            }

    private fun indentedNewLine(editor: Editor): String {
        val offset = editor.caretModel.offset
        val lineNumber = editor.document.getLineNumber(offset)
        val lineEndOffset = editor.document.getLineEndOffset(lineNumber)

        val lineStartOffset: Int =
            editor.document.getLineStartOffset(lineNumber)
        val lineContent: String =
            editor.document.text.substring(lineStartOffset, lineEndOffset)
        return "\n" + lineContent.substring(0, lineContent.length - lineContent.trim().length)
    }

    private fun toInsertColon(editor: Editor): Boolean {
        val lineNumber = editor.document.getLineNumber(editor.caretModel.offset)
        val nextLine = editor.document.text.substring(
            editor.document.getLineStartOffset(lineNumber + 1),
            editor.document.getLineEndOffset(lineNumber + 1)
        ).trim()

        return !nextLine.startsWith("end") &&
            !nextLine.startsWith("else") &&
            !nextLine.startsWith("done") &&
            !nextLine.startsWith("fi")
    }
}

fun CompletionResultSet.addWaccElement(
    title: String,
    builder: WaccLookupElement.() -> Unit = {}
) {
    val helper = WaccLookupElement(title)
    helper.apply { builder() }

    this.addElement(helper.assemble())
}

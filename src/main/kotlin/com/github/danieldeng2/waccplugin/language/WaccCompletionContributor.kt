package com.github.danieldeng2.waccplugin.language

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext

class WaccCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC, PlatformPatterns.psiElement(),
            object : CompletionProvider<CompletionParameters?>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    result.apply {
                        addWaccElement("begin") {
                            line("\tskip")
                            line("end")
                        }

                        addWaccElement("while") {
                            text(" true do")
                            line("\tskip")
                            line("done")
                        }

                        addWaccElement("if") {
                            text(" true then")
                            line("\t#true")
                            line("else")
                            line("\t#false")
                            line("fi")
                        }

                        addWaccElement("skip")

                        addWaccElement("println") {
                            text(" \"\"")
                        }

                        addWaccElement("print") {
                            text(" \"\"")
                        }

                        addWaccElement("free") {
                            text(" value")
                        }

                        addWaccElement("return") {
                            text(" 0")
                        }

                        addWaccElement("call") {
                            typeText = "Assignment"
                            text(" func()")
                        }

                        addWaccElement("newpair") {
                            typeText = "Pair"
                            text("(0,0)")
                        }

                        addWaccElement("is") {
                            typeText = "Function"
                            line("\tskip")
                            line("\tend")
                        }

                        addWaccElement("int ") {
                            typeText = "Type"
                            insertColon = false
                        }

                        addWaccElement("bool ") {
                            typeText = "Type"
                            insertColon = false
                        }

                        addWaccElement("char ") {
                            typeText = "Type"
                            insertColon = false
                        }

                        addWaccElement("string ") {
                            typeText = "Type"
                            insertColon = false
                        }

                        addWaccElement("len ") {
                            typeText = "Operator"
                            insertColon = false
                        }

                        addWaccElement("ord ") {
                            typeText = "Operator"
                            insertColon = false
                        }

                        addWaccElement("chr ") {
                            typeText = "Operator"
                            insertColon = false
                        }

                        addWaccElement("true") {
                            typeText = "Literal"
                            insertColon = false
                        }

                        addWaccElement("false") {
                            typeText = "Literal"
                            insertColon = false
                        }

                        addWaccElement("null") {
                            typeText = "Literal"
                            insertColon = false
                        }
                    }
                }
            }
        )
    }
}

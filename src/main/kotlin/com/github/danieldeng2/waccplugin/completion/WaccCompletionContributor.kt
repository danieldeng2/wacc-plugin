package com.github.danieldeng2.waccplugin.completion

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
                        addStatements()
                        addOperators()
                        addLiterals()
                        addTypes()

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
                            insertColon = false
                            line("\tskip")
                            line("end")
                        }
                    }
                }
            }
        )
    }
}

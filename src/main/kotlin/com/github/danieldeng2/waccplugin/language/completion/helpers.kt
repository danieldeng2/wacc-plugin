package com.github.danieldeng2.waccplugin.language.completion

import com.intellij.codeInsight.completion.CompletionResultSet

fun CompletionResultSet.addStatements() {
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
}

fun CompletionResultSet.addTypes() {
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
}

fun CompletionResultSet.addOperators() {
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
}

fun CompletionResultSet.addLiterals() {
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

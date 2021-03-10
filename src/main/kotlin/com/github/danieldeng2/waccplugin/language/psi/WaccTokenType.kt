package com.github.danieldeng2.waccplugin.language.psi

import com.github.danieldeng2.waccplugin.language.WaccLanguage
import com.intellij.psi.tree.IElementType

class WaccTokenType(debugName: String?) :
    IElementType(debugName!!, WaccLanguage) {
    override fun toString(): String {
        return "WaccTokenType." + super.toString()
    }
}

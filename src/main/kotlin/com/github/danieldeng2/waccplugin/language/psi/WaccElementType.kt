package com.github.danieldeng2.waccplugin.language.psi

import com.github.danieldeng2.waccplugin.language.WaccLanguage
import com.intellij.psi.tree.IElementType

class WaccElementType(debugName: String?) :
    IElementType(debugName!!, WaccLanguage)

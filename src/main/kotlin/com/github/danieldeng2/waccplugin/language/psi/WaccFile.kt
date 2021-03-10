package com.github.danieldeng2.waccplugin.language.psi

import com.github.danieldeng2.waccplugin.language.WaccFileType
import com.github.danieldeng2.waccplugin.language.WaccLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class WaccFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, WaccLanguage) {
    override fun getFileType(): FileType {
        return WaccFileType
    }
    override fun toString(): String {
        return "WACC File"
    }
}

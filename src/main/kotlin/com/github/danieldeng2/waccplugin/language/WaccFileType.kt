package com.github.danieldeng2.waccplugin.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon
import com.intellij.lang.Language
import com.intellij.openapi.util.IconLoader

object WaccLanguage : Language("WACC")

object WaccIcons {
    val FILE = IconLoader.getIcon("/icons/wacc-solid.svg")
}

object WaccFileType : LanguageFileType(WaccLanguage) {

    override fun getName(): String =
        "Wacc File"

    override fun getDescription(): String =
        "A wacc source file"

    override fun getDefaultExtension(): String =
        "wacc"

    override fun getIcon(): Icon =
        WaccIcons.FILE
}
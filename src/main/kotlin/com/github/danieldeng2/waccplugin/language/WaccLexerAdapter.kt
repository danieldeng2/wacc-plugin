package com.github.danieldeng2.waccplugin.language

import com.intellij.lexer.FlexAdapter

class WaccLexerAdapter : FlexAdapter(WaccLexer(null))

package com.github.danieldeng2.waccplugin.language

import com.intellij.lang.Commenter

class WaccCommenter : Commenter {
    override fun getLineCommentPrefix(): String =
        "#"

    override fun getBlockCommentPrefix(): String =
        ""

    override fun getBlockCommentSuffix(): String? {
        return null
    }

    override fun getCommentedBlockCommentPrefix(): String? {
        return null
    }

    override fun getCommentedBlockCommentSuffix(): String? {
        return null
    }
}

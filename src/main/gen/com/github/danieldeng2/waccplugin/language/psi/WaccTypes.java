// This is a generated file. Not intended for manual editing.
package com.github.danieldeng2.waccplugin.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.danieldeng2.waccplugin.language.psi.impl.*;

public interface WaccTypes {

  IElementType PROPERTY = new WaccElementType("PROPERTY");

  IElementType COMMENT = new WaccTokenType("COMMENT");
  IElementType CRLF = new WaccTokenType("CRLF");
  IElementType KEY = new WaccTokenType("KEY");
  IElementType SEPARATOR = new WaccTokenType("SEPARATOR");
  IElementType VALUE = new WaccTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new SimplePropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

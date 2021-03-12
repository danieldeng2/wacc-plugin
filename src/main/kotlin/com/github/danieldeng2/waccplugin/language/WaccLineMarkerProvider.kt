package com.github.danieldeng2.waccplugin.language

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.psi.PsiElement

class WaccLineMarkerProvider : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>
    ) {
//        if (element !is PsiFile) {
//            return
//        }
//
//        GutterActionRenderer()
//        // Add the property to a collection of line marker info
//        val builder = NavigationGutterIconBuilder.create(SimpleIcons.FILE)
//            .setTargets(properties)
//            .setTooltipText("Run")
//        result.add(builder.createLineMarkerInfo(element))
    }
}

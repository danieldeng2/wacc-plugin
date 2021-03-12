package com.github.danieldeng2.waccplugin.runConfiguration

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import javax.swing.JComponent
import javax.swing.JPanel

class WaccSettingsEditor : SettingsEditor<WaccRunConfiguration?>() {
    private lateinit var myPanel: JPanel
    private lateinit var waccFileName: LabeledComponent<TextFieldWithBrowseButton>

    override fun resetEditorFrom(s: WaccRunConfiguration) {
        waccFileName.component.text = s.waccFileName ?: ""
    }

    override fun applyEditorTo(s: WaccRunConfiguration) {
        s.waccFileName = waccFileName.component.text
    }

    override fun createEditor(): JComponent {
        return myPanel
    }

    private fun createUIComponents() {
        waccFileName = LabeledComponent<TextFieldWithBrowseButton>()
        waccFileName.component = TextFieldWithBrowseButton()

        waccFileName.component.addBrowseFolderListener(
            "Choose a wacc file",
            null,
            null,
            FileChooserDescriptorFactory.createSingleFileDescriptor("wacc")
        )
    }
}

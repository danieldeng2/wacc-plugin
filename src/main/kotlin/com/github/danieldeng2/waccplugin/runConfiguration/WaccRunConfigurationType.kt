package com.github.danieldeng2.waccplugin.runConfiguration

import com.github.danieldeng2.waccplugin.language.WaccIcons
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import javax.swing.Icon

object WaccRunConfigurationType : ConfigurationType {
    override fun getDisplayName(): String {
        return "WACC Application"
    }

    override fun getConfigurationTypeDescription(): String {
        return "Wacc Run Configuration Type"
    }

    override fun getIcon(): Icon {
        return WaccIcons.FILE
    }

    override fun getId(): String {
        return "WaccRunConfiguration"
    }

    override fun getConfigurationFactories(): Array<ConfigurationFactory> {
        return arrayOf(WaccConfigurationFactory(this))
    }
}

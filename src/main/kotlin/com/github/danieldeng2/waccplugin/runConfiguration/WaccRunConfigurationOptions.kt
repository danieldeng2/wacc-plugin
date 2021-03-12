package com.github.danieldeng2.waccplugin.runConfiguration

import com.intellij.execution.configurations.RunConfigurationOptions
import com.intellij.openapi.components.StoredProperty

class WaccRunConfigurationOptions : RunConfigurationOptions() {

    private val waccFileNameStore: StoredProperty<String?> = string("").provideDelegate(this, "waccFileName")

    var waccFileName: String?
        get() = waccFileNameStore.getValue(this)
        set(value) = waccFileNameStore.setValue(this, value)
}

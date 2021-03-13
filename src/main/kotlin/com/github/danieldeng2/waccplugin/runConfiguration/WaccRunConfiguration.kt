package com.github.danieldeng2.waccplugin.runConfiguration

import com.intellij.execution.Executor
import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.LocatableConfigurationBase
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import java.nio.charset.Charset

class WaccRunConfiguration constructor(project: Project, factory: ConfigurationFactory, name: String) :
    LocatableConfigurationBase<WaccRunConfigurationOptions>(project, factory, name) {

    var waccFileName: String? = null
        get() = options.waccFileName
        set(value) {
            options.waccFileName = value
            field = value
        }

    override fun getOptions(): WaccRunConfigurationOptions {
        return super.getOptions() as WaccRunConfigurationOptions
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration?> {
        return WaccSettingsEditor()
    }

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return object : CommandLineState(environment) {

            override fun startProcess(): ProcessHandler {
                val commandLine =
                    GeneralCommandLine("cat", waccFileName).withWorkDirectory(project.basePath)
                commandLine.preparedCommandLine
                commandLine.charset = Charset.forName("UTF-8")

                val processHandler = ProcessHandlerFactory.getInstance().createColoredProcessHandler(commandLine)
                ProcessTerminatedListener.attach(processHandler)
                return processHandler
            }
        }
    }
}

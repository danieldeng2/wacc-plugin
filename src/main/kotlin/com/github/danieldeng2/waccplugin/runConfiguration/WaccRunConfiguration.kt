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
import org.antlr.v4.runtime.CharStreams
import wacc48.analyser.exceptions.Issue
import wacc48.generator.architecture.I386Architecture
import wacc48.runAnalyser
import wacc48.tree.nodes.ASTNode
import wacc48.writeToFile
import java.io.File
import java.nio.charset.Charset

class WaccRunConfiguration constructor(project: Project, factory: ConfigurationFactory, name: String) :
    LocatableConfigurationBase<WaccRunConfigurationOptions>(project, factory, name) {

    var waccFileName: String? = null
        get() = options.waccFileName
        set(value) {
            options.waccFileName = value
            field = value
        }

    override fun getOptions(): WaccRunConfigurationOptions =
        super.getOptions() as WaccRunConfigurationOptions

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration?> =
        WaccSettingsEditor()

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState? {
        val waccFileName = waccFileName
        if (waccFileName == null || !File(waccFileName).exists()) return null

        val waccFile = File(waccFileName)

        File("${project.basePath}/out").mkdir()
        val execName = "${project.basePath}/out/${waccFile.name.removeSuffix(".wacc")}"
        val asmFileName = "$execName.s"

        val programNode: ASTNode
        val issues: MutableList<Issue> = mutableListOf()

        // TODO show errors
        programNode = runAnalyser(CharStreams.fromPath(waccFile.toPath()), issues)
        val instructions = I386Architecture.compile(programNode)

        writeToFile(instructions, asmFileName)
        I386Architecture.createExecutable(asmFileName, execName)

        return object : CommandLineState(environment) {
            override fun startProcess(): ProcessHandler {
                val commandLine =
                    GeneralCommandLine(execName).withWorkDirectory(project.basePath)
                commandLine.charset = Charset.forName("UTF-8")

                val processHandler = ProcessHandlerFactory.getInstance().createColoredProcessHandler(commandLine)
                ProcessTerminatedListener.attach(processHandler)
                return processHandler
            }
        }
    }
}

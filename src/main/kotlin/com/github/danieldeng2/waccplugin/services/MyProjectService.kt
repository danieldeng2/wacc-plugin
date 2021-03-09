package com.github.danieldeng2.waccplugin.services

import com.github.danieldeng2.waccplugin.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

package com.jetpackduba.gitnuro.ui.log

import com.jetpackduba.gitnuro.git.graph.GraphNode

sealed class LogDialog {
    object None : LogDialog()
    data class NewBranch(val graphNode: GraphNode) : LogDialog()
    data class NewTag(val graphNode: GraphNode) : LogDialog()
    data class ResetBranch(val graphNode: GraphNode) : LogDialog()
}
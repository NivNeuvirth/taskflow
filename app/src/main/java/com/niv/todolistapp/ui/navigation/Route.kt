package com.niv.todolistapp.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object TaskList : Route

    @Serializable
    data object AddTask : Route

}
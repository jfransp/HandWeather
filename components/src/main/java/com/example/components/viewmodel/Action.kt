package com.example.components.viewmodel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class Action<Action: ComposeAction> {

    private val _action = Channel<Action>(Channel.BUFFERED)
    val action = _action.receiveAsFlow()

    suspend fun sendAction(action: () -> Action) {
        _action.send(action())
    }

}
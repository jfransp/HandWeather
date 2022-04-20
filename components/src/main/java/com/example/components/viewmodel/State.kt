package com.example.components.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class State<State: ComposeState>(initialValue: State) {

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialValue)
    val state: StateFlow<State> = _state

    fun setState(state: (State) -> State) {
        _state.value = state(_state.value)
    }

}

package com.example.components.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class ComposeStateViewModel<State: ComposeState, Action: ComposeAction>(
    initialState: State
): ViewModel() {

    private val viewModelState = State(initialValue = initialState)
    private val viewModelAction = Action<Action>()

    val state = viewModelState.state
    val action = viewModelAction.action

    protected fun setState(state: (State) -> State){
        viewModelState.setState(state)
    }

    protected fun sendAction(action: () -> Action){
        viewModelScope.launch { viewModelAction.sendAction(action) }
    }

}

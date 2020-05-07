package me.tatocaster.core.base

import androidx.lifecycle.MutableLiveData

abstract class BaseStateViewModel : BaseViewModel() {
    var state = MutableLiveData<State<Payload>>()

    open fun reset() {
        state.value = JustInitial
    }
}

open class State<out T : Payload>
object JustInitial : State<Nothing>()
object JustLoading : State<Nothing>()
object JustSuccess : State<Nothing>()
object JustUnknownError : State<Nothing>()
class JustError(val exception: Throwable) : State<Nothing>()
object JustInvalidData : State<Nothing>()

open class Payload

class UndefinedStateException(state: State<Payload>) : Exception(state.toString())
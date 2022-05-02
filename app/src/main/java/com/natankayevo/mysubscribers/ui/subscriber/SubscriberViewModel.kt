package com.natankayevo.mysubscribers.ui.subscriber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natankayevo.mysubscribers.R
import com.natankayevo.mysubscribers.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(
    private val subscriberRepository: SubscriberRepository
) : ViewModel() {

    companion object {
        private val CLASS_NAME_TAG = SubscriberViewModel::class.java.simpleName
    }

    private val _subscriberStateEventData = MutableLiveData<SubscriberState>()
    val subscriberStateEventData: LiveData<SubscriberState>
        get() = _subscriberStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addSubscriber(name: String, email: String) = viewModelScope.launch {
        try {
            val subscriberId = subscriberRepository.insertSubscriber(name, email)
            if (subscriberId > 0) {
                _subscriberStateEventData.value = SubscriberState.Inserted
                _messageEventData.value = R.string.subscriber_inserted_successfully
            } else {
                _messageEventData.value = R.string.subscriber_error_to_insert
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.subscriber_error_to_insert
            Log.e(CLASS_NAME_TAG, ex.toString())
        }
    }

    fun updateSubscriber(name: String, email: String, id: Long = 0) = viewModelScope.launch {
        try {
            if (id > 0) {
                subscriberRepository.updateSubscriber(id, name, email)
                _subscriberStateEventData.value = SubscriberState.Updated
                _messageEventData.value = R.string.subscriber_success_update
            } else {
                _messageEventData.value = R.string.subscriber_error_update
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.subscriber_error_update
            Log.e(CLASS_NAME_TAG, ex.toString())
        }
    }

    sealed class SubscriberState {
        object Inserted : SubscriberState()
        object Updated : SubscriberState()
    }
}
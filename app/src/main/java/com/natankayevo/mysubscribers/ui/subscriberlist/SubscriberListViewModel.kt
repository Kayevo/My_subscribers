package com.natankayevo.mysubscribers.ui.subscriberlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.natankayevo.mysubscribers.data.database.entity.SubscriberEntity
import com.natankayevo.mysubscribers.repository.SubscriberRepository

class SubscriberListViewModel(private val repository : SubscriberRepository) : ViewModel() {

    val allSubscribersEvent: LiveData<List<SubscriberEntity>> = repository.getAllSubscribers()
}
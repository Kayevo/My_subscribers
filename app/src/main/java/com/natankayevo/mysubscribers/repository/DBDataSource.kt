package com.natankayevo.mysubscribers.repository

import androidx.lifecycle.LiveData
import com.natankayevo.mysubscribers.data.database.dao.SubscriberDao
import com.natankayevo.mysubscribers.data.database.entity.SubscriberEntity

class DBDataSource (private val subscriberDao: SubscriberDao) : SubscriberRepository{

    override suspend fun insertSubscriber(name: String, email: String): Long {
        var subscriberId : Long
        val subscriber = SubscriberEntity(
            name = name,
            email = email
        )

        subscriberId = subscriberDao.insert(subscriber)
        return subscriberId
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriber = SubscriberEntity(
            id = id,
            name = name,
            email = email
        )

        subscriberDao.update(subscriber)
    }

    override suspend fun deleteSubscriber(id: Long) {
        subscriberDao.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        subscriberDao.deleteAll()
    }

    override fun getAllSubscribers(): LiveData<List<SubscriberEntity>> {
        return subscriberDao.getAll()
    }
}
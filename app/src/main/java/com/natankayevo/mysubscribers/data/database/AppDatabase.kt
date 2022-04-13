package com.natankayevo.mysubscribers.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natankayevo.mysubscribers.data.database.dao.SubscriberDao
import com.natankayevo.mysubscribers.data.database.entity.SubscriberEntity

@Database(entities = [SubscriberEntity::class], version=1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun subscriberDao(): SubscriberDao
}
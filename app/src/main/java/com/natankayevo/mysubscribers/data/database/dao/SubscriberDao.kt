package com.natankayevo.mysubscribers.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.natankayevo.mysubscribers.data.database.entity.SubscriberEntity
import androidx.room.Query

@Dao
interface SubscriberDao {
    @Insert
    suspend fun insert(subscriber: SubscriberEntity): Long

    @Update
    suspend fun update(subscriber: SubscriberEntity)

    @Query("DELETE FROM subscriber WHERE id = :ida")
    public abstract suspend fun delete(id: Long)

    @Query("DELETE FROM subscriber")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber")
    fun getAll(): LiveData<List<SubscriberEntity>>
}
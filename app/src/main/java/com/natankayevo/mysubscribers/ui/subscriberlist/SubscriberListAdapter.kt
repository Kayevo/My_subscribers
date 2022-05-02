package com.natankayevo.mysubscribers.ui.subscriberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natankayevo.mysubscribers.data.database.entity.SubscriberEntity
import com.natankayevo.mysubscribers.databinding.SubscriberItemBinding

class SubscriberListAdapter(
    private val subscriberList: List<SubscriberEntity>
) : RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    var onItemClick: ((entity: SubscriberEntity) -> Unit)? = null

    // inside the onCreateViewHolder inflate the view of SubscriberItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        val view = SubscriberItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SubscriberListViewHolder(view)
    }

    // bind the items with each item
    // of the list subscriberList
    // which than will be
    // shown in recycler view
    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscriberList[position])
    }

    override fun getItemCount(): Int = subscriberList.size

    // create an inner class with name SubscriberListViewHolder
    // It takes a view argument, in which pass the generated class of subscriber_item.xml
    inner class SubscriberListViewHolder(val binding: SubscriberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val txtViewName: TextView = binding.textSubscriberName
        private val txtViewEmail: TextView = binding.textSubscriberEmail

        fun bindView(subscriber: SubscriberEntity) {
            txtViewName.text = subscriber.name
            txtViewEmail.text = subscriber.email
            binding.root.setOnClickListener {
                onItemClick?.invoke(subscriber)
            }
        }
    }

}
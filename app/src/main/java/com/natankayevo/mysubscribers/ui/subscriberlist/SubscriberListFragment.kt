package com.natankayevo.mysubscribers.ui.subscriberlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.natankayevo.mysubscribers.R
import com.natankayevo.mysubscribers.data.database.AppDatabase
import com.natankayevo.mysubscribers.data.database.dao.SubscriberDao
import com.natankayevo.mysubscribers.databinding.SubscriberListFragmentBinding
import com.natankayevo.mysubscribers.extension.navigateWithTransitions
import com.natankayevo.mysubscribers.repository.DBDataSource
import com.natankayevo.mysubscribers.repository.SubscriberRepository

class SubscriberListFragment : Fragment() {

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val subscriberDao: SubscriberDao =
                    AppDatabase.getInstance(requireContext()).subscriberDao

                val repository: SubscriberRepository = DBDataSource(subscriberDao)

                return SubscriberListViewModel(repository) as T
            }
        }
    }

    /*
        This property is only valid between onCreateView and
        onDestroyView.
    */
    private var _binding: SubscriberListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SubscriberListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        setViewListeners()
    }

    private fun observeViewModelEvents() {
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner) { allSubscribers ->
            val subscriberListAdapter = SubscriberListAdapter(allSubscribers)

            val subscriberRecyclerView: RecyclerView = binding.recyclerSubscribers

            binding.recyclerSubscribers.run {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }
    }

    private fun setViewListeners() {
        binding.fabAddSubscriber.setOnClickListener() {
            findNavController().navigateWithTransitions(R.id.subscriberFragment)
        }
    }


}
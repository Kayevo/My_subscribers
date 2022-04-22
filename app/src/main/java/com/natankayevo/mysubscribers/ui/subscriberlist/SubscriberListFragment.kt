package com.natankayevo.mysubscribers.ui.subscriberlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.natankayevo.mysubscribers.data.database.entity.SubscriberEntity
import com.natankayevo.mysubscribers.databinding.SubscriberListFragmentBinding

class SubscriberListFragment : Fragment() {

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

    private lateinit var viewModel: SubscriberListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subscriberListAdapter = SubscriberListAdapter(
            listOf(
                SubscriberEntity(1, "A", "A@A"),
                SubscriberEntity(2, "B", "B@B")
            )
        )

        binding.recyclerSubscribers.run {
            setHasFixedSize(true)
            adapter = subscriberListAdapter
        }


    }

}
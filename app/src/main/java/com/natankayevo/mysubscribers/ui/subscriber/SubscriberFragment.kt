package com.natankayevo.mysubscribers.ui.subscriber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.natankayevo.mysubscribers.R
import com.natankayevo.mysubscribers.data.database.AppDatabase
import com.natankayevo.mysubscribers.data.database.dao.SubscriberDao
import com.natankayevo.mysubscribers.data.database.entity.SubscriberEntity
import com.natankayevo.mysubscribers.databinding.SubscriberFragmentBinding
import com.natankayevo.mysubscribers.extension.hideKeyboard
import com.natankayevo.mysubscribers.extension.navigateWithTransitions
import com.natankayevo.mysubscribers.repository.DBDataSource
import com.natankayevo.mysubscribers.repository.SubscriberRepository

class SubscriberFragment : Fragment() {

    private val args: SubscriberFragmentArgs by navArgs()

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val subscriberDao: SubscriberDao =
                    AppDatabase.getInstance(requireContext()).subscriberDao

                val repository: SubscriberRepository = DBDataSource(subscriberDao)

                return SubscriberViewModel(repository) as T
            }
        }
    }

    /*
        This property is only valid between onCreateView and
        onDestroyView.
    */
    private var _binding: SubscriberFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SubscriberFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.subscriberArg?.let { subscriber ->
            setSubUpdate(subscriber)
        }

        observeEvents()
        setListeners()
    }

    private fun setSubUpdate(subscriber: SubscriberEntity) {
        binding.sendButton.text = getString(R.string.subscriber_button_update)
        binding.inputName.setText(subscriber.name)
        binding.inputEmail.setText(subscriber.email)
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberSate ->
            when (subscriberSate) {
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()
                    findNavController().navigateWithTransitions(R.id.subscriberListFragment)
                }
                is SubscriberViewModel.SubscriberState.Updated -> {
                    clearFields()
                    hideKeyboard()
                    findNavController().navigateWithTransitions(R.id.subscriberListFragment)
                }
            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        binding.inputName.text?.clear()
        binding.inputEmail.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()

        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        binding.sendButton.setOnClickListener() {
            val name = binding.inputName.text.toString()
            val email = binding.inputEmail.text.toString()

            if (binding.sendButton.text.toString() == getString(R.string.sign_up_text)) {
                viewModel.addSubscriber(name, email)
            }else{
                viewModel.updateSubscriber(name, email, args.subscriberArg?.id ?: 0)
            }



            viewModel
        }
    }

}
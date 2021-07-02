package com.alexchan.finite_state_machine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alexchan.finite_state_machine.R
import com.alexchan.finite_state_machine.core.state.State
import com.alexchan.finite_state_machine.databinding.HomeFragmentBinding

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private val adapter = HomeAdapter()
    private var binding: HomeFragmentBinding? = null
    private val _binding get() = requireNotNull(binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startInitialLoad()

        with(_binding) {
            dataLoadingPlaceholder.onRetry = {
                viewModel.retryInitialLoad()
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = StaggeredGridLayoutManager(
                2,
                androidx.recyclerview.widget.OrientationHelper.VERTICAL
            )
            viewModel.state.observe(viewLifecycleOwner, { state ->
                dataLoadingPlaceholder.bind(state)

                if (state is State.Loaded) {
                    adapter.items = state.data
                }
            })
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
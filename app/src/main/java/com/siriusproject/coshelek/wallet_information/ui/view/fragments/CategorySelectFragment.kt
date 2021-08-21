package com.siriusproject.coshelek.wallet_information.ui.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.siriusproject.coshelek.R
import com.siriusproject.coshelek.databinding.FragmentCategorySelectionBinding
import com.siriusproject.coshelek.wallet_information.data.model.CategoryUiModel
import com.siriusproject.coshelek.wallet_information.ui.adapters.CategoriesListAdapter
import com.siriusproject.coshelek.wallet_information.ui.view.view_models.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategorySelectFragment : Fragment(R.layout.fragment_category_selection) {

    private val binding by viewBinding(FragmentCategorySelectionBinding::bind)

    private lateinit var catListAdapter: CategoriesListAdapter

    private val viewModel: TransactionViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.catNextBtn.setOnClickListener { viewModel.onCategoryNextButton() }

        initRecyclerView()
        initCategories()

        binding.catNextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_categorySelectFragment_to_operationChangeFragment)
        }

        binding.catToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_categorySelectFragment_to_typeOperationFragment)
        }
    }

    private fun initCategories() {
        val categories = mutableListOf<CategoryUiModel>()
        categories.addAll(
            viewModel.getCategories()
                .filter { it.type == viewModel.type }
        )
        catListAdapter.setData(categories)
    }

    private fun initRecyclerView() {
        catListAdapter = CategoriesListAdapter(::onCategorySelected)
        binding.categoryList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = catListAdapter
        }
    }

    private fun onCategorySelected(cat: CategoryUiModel) {
        viewModel.category = cat.name
    }

}
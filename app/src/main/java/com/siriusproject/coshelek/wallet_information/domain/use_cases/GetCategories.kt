package com.siriusproject.coshelek.wallet_information.domain.use_cases

import android.graphics.Color
import com.siriusproject.coshelek.wallet_information.data.model.Category
import com.siriusproject.coshelek.wallet_information.data.model.CategoryUiModel
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val categoriesMapper: CategoriesMapper
) : () -> List<CategoryUiModel> {

    override fun invoke(): List<CategoryUiModel> {
        return listOf(
            Category(
                id = 0,
                name = "Зарплата",
                type = 1,
                picture = "ic_cat_multivalue_cards",
                color = Color.GREEN),
            Category(
                id=1,
                name = "Подработка",
                type = 1,
                picture = "ic_cat_multivalue_cards",
                color = Color.GREEN),
            Category(
                id=2,
                name = "Подарок",
                type = 1,
                picture = "ic_cat_other_gift",
                color = Color.GREEN),
            Category(
                id=3,
                name = "Капитализация",
                type = 1,
                picture = "ic_cat_other_percent",
                color = Color.GREEN)
        ).map(categoriesMapper)
    }
}
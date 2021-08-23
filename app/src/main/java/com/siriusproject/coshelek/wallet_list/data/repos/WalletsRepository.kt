package com.siriusproject.coshelek.wallet_list.data.repos

import com.siriusproject.coshelek.wallet_list.data.model.WalletChangeBody
import com.siriusproject.coshelek.wallet_list.ui.model.WalletUiModel
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal


interface WalletsRepository {

    suspend fun getWallets(): Flow<List<WalletUiModel>>

    suspend fun getWalletInfo(id: Int, currency: String, visibility: Boolean): Flow<WalletUiModel>

    suspend fun createWallet(name: String, currency: String, balance: BigDecimal, limit: BigDecimal)

    suspend fun changeWallet(id: Int, body: WalletChangeBody)

    suspend fun deleteWallet(id: Int)
}
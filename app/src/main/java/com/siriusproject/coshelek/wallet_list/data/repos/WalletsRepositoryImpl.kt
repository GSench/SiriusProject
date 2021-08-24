package com.siriusproject.coshelek.wallet_list.data.repos

import com.siriusproject.coshelek.wallet_list.data.model.WalletChangeBody
import com.siriusproject.coshelek.wallet_list.data.model.WalletCreateBody
import com.siriusproject.coshelek.wallet_list.data.remote.Result
import com.siriusproject.coshelek.wallet_list.data.remote.WalletService
import com.siriusproject.coshelek.wallet_list.domain.mappers.WalletMapper
import com.siriusproject.coshelek.wallet_list.ui.model.WalletUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.math.BigDecimal
import java.net.ConnectException
import javax.inject.Inject

class WalletsRepositoryImpl @Inject constructor(
    private val walletRemote: WalletService,
    private val mapper: WalletMapper
) :
    WalletsRepository {

    //TODO DB, error handling

    override suspend fun getWallets(): Flow<Result<List<WalletUiModel>>> =
        flow {
            try {
                val response = walletRemote.getWalletsList()
                emit(Result.Success(response.map {
                    mapper.map(it)
                }))
            } catch (e: Exception) {
                if (e is ConnectException) {
                    emit(Result.NoConnection(e))
                } else {
                    emit(Result.Error(e))

                }
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getWalletInfo(
        id: Int,
        currency: String,
        visibility: Boolean
    ): Flow<WalletUiModel> {
        return flow {
            emit(mapper.map(walletRemote.getWalletInfo(id), id, currency, visibility))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun createWallet(
        name: String,
        currency: String,
        balance: BigDecimal,
        limit: BigDecimal
    ) {
        walletRemote.createWallet(WalletCreateBody(name, currency, balance, limit))
    }


    override suspend fun changeWallet(
        id: Int, name: String?,
        currency: String?,
        limit: BigDecimal?,
        visibility: Boolean?
    ) {
        walletRemote.changeWallet(id, WalletChangeBody(name, currency, limit, visibility))
    }

    override suspend fun deleteWallet(id: Int) {
        walletRemote.deleteWallet(id)
    }


}
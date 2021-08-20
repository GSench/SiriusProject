package com.siriusproject.coshelek.wallet_information.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class TransactionAddingActivityContract : ActivityResultContract<Unit, String?>() {
    companion object {
        const val RESULT_TAG = "Model"
    }
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, TransactionAddingActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? = when {
        resultCode != Activity.RESULT_OK -> null
        else -> intent?.getStringExtra(MainScreenActivity.RESULT_KEY)
    }

}
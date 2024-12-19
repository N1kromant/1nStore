package com.n1kromant.instore.ViewModels

import com.n1kromant.instore.models.BuyCartIntent
import com.n1kromant.instore.models.CartData
import com.n1kromant.instore.models.ChangeCartIntent
import com.n1kromant.instore.models.ChangeCartIntentTypes
import com.n1kromant.instore.models.ChangePageIntent
import com.n1kromant.instore.models.Intent
import com.n1kromant.instore.models.MVI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainModelViewIntent @Inject constructor(): MVI() {
    private var _cartData: MutableStateFlow<CartData> = MutableStateFlow(CartData(mutableListOf()))
    val cartData: StateFlow<CartData> = _cartData


    override fun newIntent(intent: Intent) {
        when(intent) {
            is ChangeCartIntent -> changeCartIntent(intent)
            is ChangePageIntent -> changePageIntent(intent)
            is BuyCartIntent -> buyCartIntent(intent)
        }
    }

    private fun changeCartIntent(intent: ChangeCartIntent) {
        when(intent.action) {
            ChangeCartIntentTypes.AddItem -> {
                val existingItem = _cartData.value.items.find { it[0] == intent.id }

                if (existingItem != null) {
                    existingItem[1] += intent.count
                    _cartData.value = _cartData.value.copy()
                } else {
                    _cartData.value.items.add(mutableListOf(intent.id, 1))
                    _cartData.value = _cartData.value.copy()
                }
            }
            ChangeCartIntentTypes.RemoveItem -> {
                val existingItem = _cartData.value.items.find { it[0] == intent.id }

                if (existingItem != null) {
                    if (existingItem[1] > 1)
                        existingItem[1] -= intent.count
                    else
                        _cartData.value.items.remove(existingItem)
                    _cartData.value = _cartData.value.copy()
                }
            }
            ChangeCartIntentTypes.RemoveAllItems -> {
                val existingItem = _cartData.value.items.find { it[0] == intent.id }

                if (existingItem != null) {
                    _cartData.value.items.remove(existingItem)
                    _cartData.value = _cartData.value.copy()
                }
            }
        }
    }

    private fun changePageIntent(intent: ChangePageIntent) {
        TODO("Not yet implemented")
    }

    private fun buyCartIntent(intent: BuyCartIntent) {
        TODO("Not yet implemented")
    }
}


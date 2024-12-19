package com.n1kromant.instore.models

import androidx.lifecycle.ViewModel

enum class ChangeCartIntentTypes {
    AddItem, RemoveItem, RemoveAllItems
}

abstract class MVI: ViewModel() {
    abstract fun newIntent(intent: Intent)
}

data class CartData(
    val items: MutableList<MutableList<Int>>
)
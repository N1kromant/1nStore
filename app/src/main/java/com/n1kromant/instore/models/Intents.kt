package com.n1kromant.instore.models

sealed class Intent

class ChangeCartIntent(
    val id: Int,
    val count: Int,
    val action: ChangeCartIntentTypes,
): Intent()

class BuyCartIntent: Intent()

class ChangePageIntent(
    val page: Int
): Intent()
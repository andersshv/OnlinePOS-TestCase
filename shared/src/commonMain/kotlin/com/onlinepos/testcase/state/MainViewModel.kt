package com.onlinepos.testcase.state

import androidx.compose.runtime.*
import com.onlinepos.testcase.data.MockData
import com.onlinepos.testcase.model.CartItem
import com.onlinepos.testcase.model.Product
import com.onlinepos.testcase.model.ProductGroup

class MainViewModel {

    // Product Groups
    val productGroups: List<ProductGroup> = MockData.productGroups

    // Selected Group
    var selectedGroup by mutableStateOf(productGroups.first())
        private set

    // Filtered Products for the selected group
    val currentProducts: List<Product>
        get() = MockData.products.filter { it.groupId == selectedGroup.id }

    // Cart items
    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
        private set

    // --- Actions ---

    init {
        preloadCartItems()
    }

    private fun preloadCartItems() {
        val itemsToPreload = MockData.products.take(8) // first 8 products
        cartItems = itemsToPreload.map { product ->
            CartItem(product = product, quantity = 1)
        }
    }

    fun selectGroup(group: ProductGroup) {
        selectedGroup = group
    }

    fun addToCart(product: Product) {
        val existing = cartItems.find { it.product.id == product.id }
        cartItems = if (existing != null) {
            cartItems.map {
                if (it.product.id == product.id)
                    it.copy(quantity = it.quantity + 1)
                else it
            }
        } else {
            cartItems + CartItem(product)
        }
    }

    fun increaseQuantity(productId: String) {
        cartItems = cartItems.map {
            if (it.product.id == productId) it.copy(quantity = it.quantity + 1) else it
        }
    }

    fun decreaseQuantity(productId: String) {
        cartItems = cartItems.mapNotNull {
            if (it.product.id == productId) {
                if (it.quantity > 1) it.copy(quantity = it.quantity - 1) else null
            } else it
        }
    }

    fun removeAllFromCart(productId: String) {
        cartItems = cartItems.filterNot { it.product.id == productId }
    }

    fun removeOneFromCart(productId: String) {
        cartItems = cartItems.flatMap {
            if (it.product.id == productId) {
                if (it.quantity > 1) listOf(it.copy(quantity = it.quantity - 1))
                else emptyList() // remove if quantity is 1
            } else {
                listOf(it)
            }
        }
    }

    fun clearCart() {
        cartItems = emptyList()
    }

    fun simulatePayment() {
        // In a real app, you'd trigger a payment API. Here we just clear.
        clearCart()
    }

    // Total Price
    val totalPrice: Double
        get() = cartItems.sumOf { it.totalPrice }
}

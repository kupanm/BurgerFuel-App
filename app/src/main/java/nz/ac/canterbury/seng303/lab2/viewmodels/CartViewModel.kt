package nz.ac.canterbury.seng303.lab2.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import nz.ac.canterbury.seng303.lab2.datastore.Storage
import nz.ac.canterbury.seng303.lab2.models.MenuItem
import nz.ac.canterbury.seng303.lab2.models.MenuStorageItem

class CartViewModel(
    private val cartStorage: Storage<MenuStorageItem>
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<MenuStorageItem>>(emptyList())
    val cartItems : StateFlow<List<MenuStorageItem>> get() = _cartItems

    var totalSize = MutableStateFlow(0)


    fun getSize() = viewModelScope.launch{
        var tempTotalSize = 0

        val items = _cartItems.first()

        if(items.isNotEmpty()) {
            items.forEach { item ->
                tempTotalSize += item.amount
            }
        }

        totalSize.value = tempTotalSize
    }


    fun getCartItems() = viewModelScope.launch {
        cartStorage.getAll().catch {
            Log.e("CART_VIEW_MODEL", it.toString())
        } .collect {
            _cartItems.emit(it)
        }
    }


    fun addItem(item : MenuItem) = viewModelScope.launch {

        val foundItem = _cartItems.first().find { it.id == item.id }

        var menuStorageItem = MenuStorageItem(
            id = item.id,
            amount = item.amount,
            icon = item.icon,
            name = item.name,
            description = item.description,
            price = item.price
        )

        if(foundItem != null) {
            menuStorageItem.amount += foundItem.amount

            cartStorage.edit(item.id, menuStorageItem).collect()
            cartStorage.getAll().catch { Log.e("CART_VIEW_MODEL", it.toString()) }
                .collect { _cartItems.emit(it) }
        } else {

            cartStorage.insert(menuStorageItem).catch {
                Log.e("CART_VIEW_MODEL", "Could not insert cart item")
            }.collect()
            cartStorage.getAll().catch { Log.e("CART_VIEW_MODEL", it.toString()) }
                .collect { _cartItems.emit(it) }


        }
    }

    fun addSingleCartItem(cartId: Int?) = viewModelScope.launch {

        val foundItem = _cartItems.first().find { it.id == cartId }

        if (foundItem != null) {
            //foundItem.amount ++

            cartStorage.edit(foundItem.id, foundItem).collect()
            cartStorage.getAll().catch { Log.e("CART_VIEW_MODEL", it.toString()) }
                .collect { _cartItems.emit(it) }
        } else {
            Log.e("CART_VIEW_MODEL", "Item not in cart")
        }
    }


    fun clearCart() = viewModelScope.launch {
        Log.d("CART_VIEW_MODEL", "Clearing cart")

        _cartItems.value.forEach() { item ->
            cartStorage.delete(item.id).collect()
        }


    }


    fun deleteCartById(cartId: Int?) = viewModelScope.launch {
        Log.d("CART_VIEW_MODEL", "Deleting item: $cartId")


        if (cartId != null) {
            val foundItem = _cartItems.first().find { it.id == cartId }

            // Check that item is in the cart
            if(foundItem != null) {
                // check if item needs to be removed or edited
                if(foundItem.amount <= 1) {

                    cartStorage.delete(cartId).collect()
                    cartStorage.getAll().catch { Log.e("CART_VIEW_MODEL", it.toString()) }
                        .collect { _cartItems.emit(it) }
                } else {
                    cartStorage.edit(foundItem.id, foundItem).collect()
                    cartStorage.getAll().catch { Log.e("CART_VIEW_MODEL", it.toString()) }
                        .collect { _cartItems.emit(it) }
                }
            }
        }
    }
}
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
        val menuStorageItem = MenuStorageItem(
            id = item.id,
            amount = item.amount,
            icon = item.icon,
            name = item.name,
            description = item.description,
            price = item.price
        )

        cartStorage.insert(menuStorageItem).catch {
            Log.e("CART_VIEW_MODEL", "Could not insert cart item")
        }.collect()
        cartStorage.getAll().catch { Log.e("CART_VIEW_MODEL", it.toString()) }
            .collect{_cartItems.emit(it)}
    }

    fun deleteNoteById(cartId: Int?) = viewModelScope.launch {
        Log.d("CART_VIEW_MODEL", "Deleting item: $cartId")
        if (cartId != null) {
            cartStorage.delete(cartId).collect()
            cartStorage.getAll().catch { Log.e("CART_VIEW_MODEL", it.toString()) }
                .collect { _cartItems.emit(it) }
        }
    }
}
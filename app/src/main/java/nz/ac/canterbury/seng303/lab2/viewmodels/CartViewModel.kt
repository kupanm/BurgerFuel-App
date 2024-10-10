package nz.ac.canterbury.seng303.lab2.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import nz.ac.canterbury.seng303.lab2.datastore.Storage
import nz.ac.canterbury.seng303.lab2.models.MenuItem

class CartViewModel(
    private val cartStorage: Storage<MenuItem>
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val cartItems : StateFlow<List<MenuItem>> get() = _cartItems


    fun loadDefaultNotesIfNoneExist() = viewModelScope.launch {
        val currentNotes = cartStorage.getAll().first()
        if (currentNotes.isEmpty()) {
            Log.d("CART_VIEW_MODEL", "Empty cart")
        }
    }


    fun getCartItems() = viewModelScope.launch {
        cartStorage.getAll().catch {
            Log.e("CART_VIEW_MODEL", it.toString())
        } .collect {
            _cartItems.emit(it)
        }
    }


    fun addItem(item : MenuItem) = viewModelScope.launch {
        cartStorage.insert(item).catch {
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
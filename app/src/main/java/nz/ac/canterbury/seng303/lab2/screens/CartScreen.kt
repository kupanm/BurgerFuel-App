package nz.ac.canterbury.seng303.lab2.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import nz.ac.canterbury.seng303.lab2.models.MenuItem
import nz.ac.canterbury.seng303.lab2.viewmodels.CartViewModel


@Composable
fun ItemCart(navController: NavController, cartView: CartViewModel)
{
    cartView.getCartItems()

    val cartItems: List<MenuItem> by cartView.cartItems.collectAsState(emptyList())
    LazyColumn {
        items(cartItems) { food ->
            CartRow(navController = navController, food = food, deleteFn = {id: Int -> cartView.deleteNoteById(id) })
        }
    }
}


@Composable
fun CartRow(navController: NavController, food: MenuItem, deleteFn: (id: Int) -> Unit)
{
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(8.dp)
        .background(Color.Cyan)) {

        Box(
            modifier = Modifier.align(Alignment.Top)
            .background(Color.Magenta)
            ) {


            Icon(
                modifier = Modifier.scale(1f),
                painter = painterResource(id = food.icon),
                contentDescription = food.name,
                tint = Color.Unspecified
            )
        }

        Text(text = food.name)

        Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.background(Color.Blue)) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Add Item")
            }
        }

        Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.background(Color.Blue).padding(8.dp)) {

            IconButton(onClick = {
                deleteFn(food.id)
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Remove Item"
                )
            }
        }
    }

    Divider(thickness = 2.dp) // Add a divider between items
}
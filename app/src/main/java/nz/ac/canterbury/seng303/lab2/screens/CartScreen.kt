package nz.ac.canterbury.seng303.lab2.screens


import android.graphics.Paint.Align
import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import nz.ac.canterbury.seng303.lab2.R
import nz.ac.canterbury.seng303.lab2.models.MenuStorageItem
import nz.ac.canterbury.seng303.lab2.util.NotificationHelper
import nz.ac.canterbury.seng303.lab2.viewmodels.CartViewModel
import nz.ac.canterbury.seng303.lab2.viewmodels.SettingViewModel


@Composable
fun ItemCart(
    navController: NavController,
    cartView: CartViewModel,
    settingViewModel: SettingViewModel,
    notificationHelper: NotificationHelper
)
{
    val context = LocalContext.current
    val isDarkMode by settingViewModel.isDarkMode.collectAsState()
    val backgroundColor = if (isDarkMode) {
        colorResource(id = R.color.black)
    } else {
        colorResource(id = R.color.white)
    }

    val textColor = if (isDarkMode) {
        colorResource(id = R.color.white)
    } else {
        colorResource(id = R.color.black)
    }
    cartView.getCartItems()

    val cartItems: List<MenuStorageItem> by cartView.cartItems.collectAsState(emptyList())

    if (cartItems.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(context.getString(R.string.default_cart_string))
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .background(backgroundColor)
        ){
            items(cartItems, key = {it.id}) { food ->
                CartRow(navController = navController,
                    food = food, deleteFn = {id: Int -> cartView.deleteCartById(id) },
                    addFn = {id: Int -> cartView.addSingleCartItem(id)},
                    notificationHelper = notificationHelper)
            }
        }
    }
}


@Composable

fun CartRow(navController: NavController, 
    food: MenuStorageItem, 
    deleteFn: (id: Int) -> Unit, 
    addFn: (id: Int) -> Unit,
    notificationHelper: NotificationHelper)
{
    var itemQuantity by remember { mutableStateOf(food.amount) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(8.dp)) {

        Row (modifier = Modifier.fillMaxWidth(0.5f)){


            Box(
                modifier = Modifier
                    .align(Alignment.Top)
            ) {


                Icon(
                    modifier = Modifier.scale(1f),
                    painter = painterResource(id = food.icon),
                    contentDescription = food.name,
                    tint = Color.Unspecified
                )
            }

            Text(
                text = food.name,
                textAlign = TextAlign.Center
            )

        }

        Row (modifier = Modifier.fillMaxWidth()
            .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.Absolute.Right){


            Box(
                contentAlignment = Alignment.CenterEnd, modifier = Modifier
            ) {

                IconButton(onClick = {
                        deleteFn(food.id)
                        food.amount--
                        itemQuantity--

                        val decimalFormat = DecimalFormat("#.00")
                        val formattedPrice = decimalFormat.format(food.price)
                        notificationHelper.createNotification(notificationTitle = "Item Removed From Cart", notificationDesc = "1x " + food.name + " $"+formattedPrice)

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = "Remove Item"
                    )
                }
            }

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "$itemQuantity",
                textAlign = TextAlign.Center
            )

            Box(
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(onClick = {
                    addFn(food.id)
                    food.amount++
                    itemQuantity++
                    val decimalFormat = DecimalFormat("#.00")
                    val formattedPrice = decimalFormat.format(food.price)
                    notificationHelper.createNotification(notificationTitle = "Item Added To Cart", notificationDesc = "1x " + food.name + " $"+formattedPrice)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add Item"
                    )
                }
            }
        }
    }

    Divider(thickness = 2.dp) // Add a divider between items
}
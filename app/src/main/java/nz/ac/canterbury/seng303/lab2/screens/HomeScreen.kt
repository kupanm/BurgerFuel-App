package nz.ac.canterbury.seng303.lab2.screens

import android.icu.text.DecimalFormat
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import nz.ac.canterbury.seng303.lab2.R
import nz.ac.canterbury.seng303.lab2.models.MenuIcon
import nz.ac.canterbury.seng303.lab2.models.MenuItem
import nz.ac.canterbury.seng303.lab2.viewmodels.CartViewModel
import nz.ac.canterbury.seng303.lab2.viewmodels.MenuUiState
import nz.ac.canterbury.seng303.lab2.viewmodels.MenuViewModel

@Composable
fun Home(
    navController: NavController,
    cartViewModel: CartViewModel,
    menuViewModel: MenuViewModel = viewModel()

) {
    val menuItems : List<MenuItem> = MenuItem.getMenuItems() /* Items for the scrollable grid for menu selection */
    val menuIcons : List<MenuIcon> = MenuIcon.getMenuIcons() /* Items for the scrollable row for filtering the menu */
    val menuUiState by menuViewModel.uiState.collectAsState() /* State of the menu. Changes on click of icon */
    val isClicked = remember { mutableStateOf(false) } /* Items default as not clicked on */
    Column {
        LazyRow( /* This row is the left/right scrollable menu with icons to filter menu */
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight(0.3f) /*Changes the height of lazy row*/
        ) {
            menuIcons.forEach { foodIcon ->
                item {
                    MenuRowIcon(foodIcon, menuUiState, menuViewModel)
                }
            }
        }
        LazyVerticalGrid( /* This vertical grid is the up/down scrollable menu with cards for relevant menu items */
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .background(Color.Black)
        ) {
            val filteredMenu = menuItems.filter { it.type.contains(menuUiState.currentSelectedIcon.text) } /* Filter the menu so that only items that are relavant are passed into the LazyVerticalGrid */
            filteredMenu.forEach { foodItem ->
                item {
                    MenuItemCard(foodItem, cartViewModel)
                }
            }
        }
    }
}


@Composable
fun MenuRowIcon(item: MenuIcon, menuUiState: MenuUiState, menuViewModel: MenuViewModel) { /* TODO EDIT SO THAT ONLY ONE ICON CAN BE CLICKED AT A TIME */
    var icon: Int = item.defaultIcon

    if (item.text == menuUiState.currentSelectedIcon.text) { /* loads the default screen */
        icon = menuUiState.currentSelectedIcon.clickedIcon
    }

    Column(Modifier.width(150.dp)) {
        Box( /* This box is for each menu row icon in the LazyRow */
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            IconButton(
                onClick = { menuViewModel.newSelectedIcon(item)},
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(12.dp)
                    .scale(2.5f)
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = item.text,
                    tint = Color.Unspecified,
                    modifier = Modifier.scale(1.0f)
                )
            }
        }
        Box( /* This is for the text underneath each menu LazyRow item */
            modifier = Modifier
                .fillMaxHeight()
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = item.text,
                textAlign = TextAlign.Center,
                maxLines = 2,
            )
        }
    }
}

@Composable
fun MenuItemCard(item: MenuItem, cartViewModel: CartViewModel) {
    var isClicked = remember { mutableStateOf(false) }

    if (isClicked.value) { /* Checks if menu item card is clicked. If it is it will display the Dialog with the item description */
        displayItemDialog(item, isClicked, cartViewModel)
    }

    Column(modifier = Modifier
        .background(Color.Transparent))
    {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .size(width = 210.dp, height = 220.dp)
                .padding(4.dp)
                .clickable(onClick = {
                    isClicked.value = true
                }) ,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Box( /* Box for the image for each menu item */
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
//                    .background(color = Color.Magenta),
                ,contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.name,
                    tint = Color.Unspecified,
                )
            }
            Box( /* Box for the text + price for each menu item */
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(color = Color.Yellow)
            ) {
                val decimalFormat = DecimalFormat("#.00")
                val formattedPrice = decimalFormat.format(item.price)
                Text(text = item.name + "\n$" + formattedPrice)
            }
            ElevatedButton( /* Button to add item to cart */
                onClick = {/*TODO THIS IS WHERE YOU ADD ITEM TO CART*/},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(8.dp)), /* Changes how rounded the corners are for the Add button */
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.burger_fuel_purple), /* Background color of the button */
                    contentColor = Color.White /* Color of the text in the button */
                )
            ) {
                Text(text = "ADD")
            }
        }
    }
}

@Composable
fun displayItemDialog(item: MenuItem, isClicked: MutableState<Boolean>, cartViewModel: CartViewModel) { /* Will display the item description */
    //var itemQuantity by remember { mutableStateOf(item.amount) }
    Dialog(onDismissRequest = { isClicked.value = false }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(720.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row( /* Row for the item name and the close dialog button */
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .background(color = Color.White),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
//                        .background(color = Color.Red)
                ) {
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center,
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(color = Color.Blue)
                        .fillMaxHeight()
                ) {
                    IconButton(
                        onClick = { isClicked.value = false },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Remove Dialog",
                            tint = Color.Black
                        )
                    }
                }
            }

            Box( /* Box for menu item image */
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.name,
                    modifier = Modifier.scale(1.5f)
                )
            }

            Box(
                /* Box for menu item description */
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .background(color = Color.White),
            ) {
                Text(
                    text = item.description,
                    textAlign = TextAlign.Center,
                )
            }

            Row( /* Row for the item quantity */
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.62f)
                    .background(color = Color.White),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically

            ) {
                IconButton(
                    onClick = { item.dec() },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = "Remove",
                        tint = Color.Black
                    )
                }

                Text(
                    text = "${item.amount.value}",
                    textAlign = TextAlign.Center
                )

                IconButton(
                    onClick = { item.inc() },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add",
                        tint = Color.Black
                    )
                }
            }

            Box( /* Box for Add to cart button */
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                ElevatedButton( /*Add to cart button*/
                    onClick = {/*TODO THIS IS WHERE YOU ADD ITEM TO CART*/
                        cartViewModel.addItem(item)
                    },
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(8.dp)), /* Changes how rounded the corners are for the Add button */
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.burger_fuel_purple), /* Background color of the button */
                        contentColor = Color.White /* Color of the text in the button */
                    )
                ) {
                    val total = item.price * item.amount.value
                    val decimalFormat = DecimalFormat("#.00")
                    val formattedTotal = decimalFormat.format(total)
                    Text(text = "ADD - $${formattedTotal}")
                }
            }
        }
    }
}

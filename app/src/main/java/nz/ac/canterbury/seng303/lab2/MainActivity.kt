package nz.ac.canterbury.seng303.lab2

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nz.ac.canterbury.seng303.lab2.models.MenuIcon
import nz.ac.canterbury.seng303.lab2.models.MenuItem
import nz.ac.canterbury.seng303.lab2.screens.ItemCart
import nz.ac.canterbury.seng303.lab2.screens.Locations
import nz.ac.canterbury.seng303.lab2.screens.PastOrders
import nz.ac.canterbury.seng303.lab2.screens.Profile
import nz.ac.canterbury.seng303.lab2.ui.theme.Lab1Theme
import nz.ac.canterbury.seng303.lab2.viewmodels.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel as koinViewModel

class MainActivity : ComponentActivity() {

    private val noteViewModel: NoteViewModel by koinViewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel.loadDefaultNotesIfNoneExist()

        setContent {
            Lab1Theme {
                val navController = rememberNavController()
                Scaffold(
                    topBar =  {
                        // Add your AppBar content here
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Yellow),
                            title = { Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.scale(0.5f)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.borgor_logo),
                                    contentDescription = "BurgerFuel Logo",
                                    tint = Color.Unspecified
                                )
                            } },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "Location"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { navController.navigate("Cart") }) {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingCart,
                                        contentDescription = "Cart",
                                        tint = Color.Black
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar( /*This is the bottom navigation bar for the app*/
                            containerColor = Color.Transparent,
                            content = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    IconButton(onClick = { navController.navigate("Home") }) {
                                        Icon(
                                            imageVector = Icons.Default.Home,
                                            contentDescription = "Home",
                                        )
                                    }
                                    IconButton(onClick = { navController.navigate("Locations") }) {
                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = "Location",
                                        )
                                    }
                                    IconButton(onClick = { navController.navigate("PastOrders") }) {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(id = R.drawable.receipt_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                                            contentDescription = "PastOrder"
                                        )
                                    }
                                    IconButton(onClick = { navController.navigate("Profile") }) {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "Profile",
                                        )
                                    }
                                }
                            }
                        )
                    }
                ) {

                    Box(modifier = Modifier.padding(it)) {

                        NavHost(navController = navController, startDestination = "Home") {
                            composable("Home") {
                                Home(navController = navController)
                            }
                            composable("Locations") {
                                Locations(navController = navController)
                            }
                            composable("ItemCart") {
                                ItemCart(navController = navController)
                            }
                            composable("Profile") {
                                Profile(navController = navController)
                            }
                            composable("PastOrders") {
                                PastOrders(navController = navController)
                            }
                            composable("Cart") {
                                ItemCart(navController = navController)
                            }
                        }

                        /*
                        //region Old Notes data
                        val createNoteViewModel: CreateNoteViewModel = viewModel()
                        val editNoteViewModel: EditNoteViewModel = viewModel()
                        NavHost(navController = navController, startDestination = "Home") {
                            composable("Home") {
                                Home(navController = navController)
                            }
                            composable(
                                "NoteCard/{noteId}",
                                arguments = listOf(navArgument("noteId") {
                                    type = NavType.StringType
                                })
                            ) { backStackEntry ->
                                val noteId = backStackEntry.arguments?.getString("noteId")
                                noteId?.let { noteIdParam: String -> NoteCard(noteIdParam, noteViewModel) }
                            }
                            composable("EditNote/{noteId}", arguments = listOf(navArgument("noteId") {
                                type = NavType.StringType
                            })
                            ) { backStackEntry ->
                                val noteId = backStackEntry.arguments?.getString("noteId")
                                noteId?.let { noteIdParam: String -> EditNote(noteIdParam, editNoteViewModel, noteViewModel, navController = navController) }
                            }
                            composable("NoteList") {
                                NoteList(navController, noteViewModel)
                            }
                            composable("NoteGrid") {
                                NoteGrid(navController, noteViewModel)
                            }
                            composable("CreateNote") {
                                CreateNote(navController = navController, title = createNoteViewModel.title,
                                    onTitleChange = {newTitle ->
                                        val title = newTitle.replace("badword", "*******")
                                        createNoteViewModel.updateTitle(title)
                                    },
                                    content = createNoteViewModel.content, onContentChange = {newContent -> createNoteViewModel.updateContent(newContent)},
                                    createNoteFn = {title, content -> noteViewModel.createNote(title, content)}
                                )
                                //CreateNoteStandAlone(navController = navController)
                            }
                        }
                        // endregion

                        */
                    }
                }
            }
        }
    }
}


@Composable
fun Home(navController: NavController) {
    val menuItems : List<MenuItem> = MenuItem.getMenuItems() /* Items for the scrollable grid for menu selection */
    val menuIcons : List<MenuIcon> = MenuIcon.getMenuIcons() /* Items for the scrollable row for filtering the menu */
    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(false) } /* MAYBE USE THIS TO ONLY SELECT ONE MENU ITEM AT A TIME */
    Column {
        LazyRow( /* This row is the left/right scrollable menu with icons to filter menu */
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight(0.25f) /*Changes the height of lazy row*/
        ) {
            menuIcons.forEach { foodIcon ->
                item {
                    MenuRowIcon(foodIcon)
                }
            }
        }
        LazyVerticalGrid( /* This vertical grid is the up/down scrollable menu with cards for relevant menu items */
            columns = GridCells.Fixed(2)
        ) {
            menuItems.forEach { foodItem ->
                item {
                    if (foodItem.type == "Specials") {
                        MenuItemCard(foodItem)
                    }
                }
            }
        }
        }
}

@Composable
fun MenuRowIcon(item: MenuIcon) {
    val context = LocalContext.current
    val isClicked = remember { mutableStateOf(false)}
    val icon = if (!isClicked.value) item.defaultIcon else item.clickedIcon
    Column(Modifier.width(150.dp)) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .align(alignment = Alignment.CenterHorizontally)
                .background(color = Color.Blue)
        ) {
            IconButton(
                onClick = { isClicked.value = !isClicked.value},
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
        Box(
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
fun MenuItemCard(item: MenuItem) {
    val context = LocalContext.current
    var isClicked = remember { mutableStateOf(false) }

    if (isClicked.value) { /* Checks if menu item card is clicked. If it is it will display the Dialog with the item description */
        displayItemDialog(item, isClicked)
    }

    Column(modifier = Modifier.background(Color.Black))
    {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .size(width = 210.dp, height = 220.dp)
                .padding(4.dp)
                .clickable(onClick = {
//                    dialog.show()
                    isClicked.value = true
                }) ,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .background(color = Color.Magenta),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.name,
                    tint = Color.Unspecified,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Yellow)
            ) {
                Text(text = item.name + "\n$" + item.price)
            }
            ElevatedButton(
                onClick = {/*TODO THIS IS WHERE YOU ADD ITEM TO CART*/},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(8.dp)), /* Changes how rounded the corners are for the Add button */
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id =R.color.burger_fuel_purple), /* Background color of the button */
                    contentColor = Color.White /* Color of the text in the button */
                )
            ) {
                Text(text = "ADD")
            }
        }
    }
}

@Composable
fun displayItemDialog(item: MenuItem, isClicked: MutableState<Boolean>) { /* Will display the item description */
    Dialog(onDismissRequest = { isClicked.value = false }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row( /* Row for the item name and the close dialog button */
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .background(color = Color.Cyan)
            ){
                Text(
                    text = item.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
                /* TODO FIX THIS FUTURE ME. YOU NEED TO ADD A CLOSE BUTTON BUT HASNT BEEN WORKING IDK WHY COME TO THIS LATER */
//                IconButton(onClick = {isClicked.value = false},
//                    ) {
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = "Remove Dialog"
//                    )
//                }
            }

            Box( /* Box for menu item image */
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(color = Color.Green),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.name,
                    modifier = Modifier.scale(1.5f)
                )
            }

            Box( /* Box for menu item description */
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .background(color = Color.Red),
            ) {
                Text(
                    text = item.description,
                    textAlign = TextAlign.Center,
                )
            }

//            Box( /* Box for editing quantity of item for cart */
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(0.7f)
//                    .background(color = Color.Magenta),
//                contentAlignment = Alignment.Center
//            ) {
//                IconButton(
//                    onClick = { /*TODO UPDATE QUATITY IN SOME WAY. IDK HOW THIS WILL WORK YET I WILL IMPLEMENT*/ },
////                    modifier = Modifier
////                        .align(alignment = Alignment.Center)
////                        .padding(12.dp)
////                        .scale(2.5f)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.remove),
//                        contentDescription = "Remove",
//                        tint = Color.Black
//                    )
//                }
//
//                Text(
//                    text = "1"
//                )
//
//                IconButton(
//                    onClick = { /*TODO UPDATE QUATITY IN SOME WAY. IDK HOW THIS WILL WORK YET I WILL IMPLEMENT*/ },
////                    modifier = Modifier
////                        .align(alignment = Alignment.Center)
////                        .padding(12.dp)
////                        .scale(2.5f)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.add),
//                        contentDescription = "Add",
//                        tint = Color.Black
//                    )
//                }
//
//            }

            Box( /* Box for Add to cart button */
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.Yellow),
                contentAlignment = Alignment.Center
            ) {
                ElevatedButton( /*Add to cart button*/
                    onClick = {/*TODO THIS IS WHERE YOU ADD ITEM TO CART*/},
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(8.dp)), /* Changes how rounded the corners are for the Add button */
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id =R.color.burger_fuel_purple), /* Background color of the button */
                        contentColor = Color.White /* Color of the text in the button */
                    )
                ) {
                    Text(text = "ADD - $${item.price}")
                }
            }
        }
    }
}

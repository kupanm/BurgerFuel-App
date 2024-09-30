package nz.ac.canterbury.seng303.lab2

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import nz.ac.canterbury.seng303.lab2.R
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nz.ac.canterbury.seng303.lab2.models.MenuIcon
import nz.ac.canterbury.seng303.lab2.models.MenuItem
import nz.ac.canterbury.seng303.lab2.models.MenuItem.Companion.getMenuItems
import nz.ac.canterbury.seng303.lab2.screens.CreateNote
import nz.ac.canterbury.seng303.lab2.screens.EditNote
import nz.ac.canterbury.seng303.lab2.screens.ItemCart
import nz.ac.canterbury.seng303.lab2.screens.Locations
import nz.ac.canterbury.seng303.lab2.screens.NoteCard
import nz.ac.canterbury.seng303.lab2.screens.NoteGrid
import nz.ac.canterbury.seng303.lab2.screens.NoteList
import nz.ac.canterbury.seng303.lab2.screens.PastOrders
import nz.ac.canterbury.seng303.lab2.screens.Profile
import nz.ac.canterbury.seng303.lab2.ui.theme.Lab1Theme
import nz.ac.canterbury.seng303.lab2.ui.theme.Purple40
import nz.ac.canterbury.seng303.lab2.viewmodels.CreateNoteViewModel
import nz.ac.canterbury.seng303.lab2.viewmodels.EditNoteViewModel
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
                            title = { Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.scale(0.5f)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.borgorlogo),
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
    val menuItems : List<MenuItem> = MenuItem.getMenuItems()
    val menuIcons : List<MenuIcon> = MenuIcon.getMenuIcons()
    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(false) }
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
        ) {
            IconButton(
                onClick = { isClicked.value = !isClicked.value},
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = item.text,
                    tint = Color.Unspecified,
                    modifier = Modifier.scale(2.5f)
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
    val imageView = ImageView(context)
    imageView.setImageResource(item.icon)
    imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    val layout = LinearLayout(context)
    layout.orientation = LinearLayout.VERTICAL
    layout.addView(imageView)
    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    builder.setMessage(item.name).setView(layout)
    val dialog: AlertDialog = builder.create()
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
                    dialog.show()
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
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(4.dp)), /* Changes how rounded the corners are for the Add button */
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

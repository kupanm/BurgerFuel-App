package nz.ac.canterbury.seng303.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import nz.ac.canterbury.seng303.lab2.R
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
                        BottomAppBar(
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
    Column {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight(0.25f) /*Changes the height of lazy row*/
        ) {
            item {
                Column(Modifier.width(150.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        IconButton(
                            onClick = { navController.navigate("Home") },
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.specials),
                                contentDescription = "Specials",
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
                            text = "Specials",
                            textAlign = TextAlign.Center,
                            maxLines = 2,
//                        modifier = Modifier.width(75.dp)
                        )
                    }
                }
            }
            item {
                Column(Modifier.width(150.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        IconButton(
                            onClick = { navController.navigate("Home") },
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.beef),
                                contentDescription = "NZ Beef",
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
                            text = "NZ Beef",
                            textAlign = TextAlign.Center,
                            maxLines = 2,
//                        modifier = Modifier.width(75.dp)
                        )
                    }
                }
            }

            item {
                Column(Modifier.width(150.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        IconButton(
                            onClick = { navController.navigate("Home") },
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.chicken),
                                contentDescription = "Free Range Chicken Burgers",
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
                            text = "Free Range Chicken",
                            textAlign = TextAlign.Center,
                            maxLines = 2,
//                        modifier = Modifier.width(75.dp)
                        )
                    }
                }
            }

            item {
                Column(Modifier.width(150.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        IconButton(
                            onClick = { navController.navigate("Home") },
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.plant),
                                contentDescription = "Plant-Powered",
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
                            text = "Plant-Powered",
                            textAlign = TextAlign.Center,
                            maxLines = 2,
//                        modifier = Modifier.width(75.dp)
                        )
                    }
                }
            }

            item {
                Column(Modifier.width(150.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        IconButton(
                            onClick = { navController.navigate("Home") },
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.minis),
                                contentDescription = "Minis",
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
                            text = "Minis",
                            textAlign = TextAlign.Center,
                            maxLines = 2,
//                        modifier = Modifier.width(75.dp)
                        )
                    }
                }
            }

            item {
                Column(Modifier.width(150.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        IconButton(
                            onClick = { navController.navigate("Home") },
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.sides),
                                contentDescription = "Sides",
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
                            text = "Sides",
                            textAlign = TextAlign.Center,
                            maxLines = 2,
//                        modifier = Modifier.width(75.dp)
                        )
                    }
                }
            }

            item {
                Column(Modifier.width(150.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        IconButton(
                            onClick = { navController.navigate("Home") },
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.drinks),
                                contentDescription = "Thickshakes & Drinks",
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
                            text = "Thickshakes & Drinks",
                            textAlign = TextAlign.Center,
                            maxLines = 2,
//                        modifier = Modifier.width(75.dp)
                        )
                    }
                }
            }

            item {
                Column(Modifier.width(150.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        IconButton(
                            onClick = { navController.navigate("Home") },
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.kids),
                                contentDescription = "Kids Ink. Meals",
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
                            text = "Kids Ink. Meals",
                            textAlign = TextAlign.Center,
                            maxLines = 2,
//                        modifier = Modifier.width(75.dp)
                        )
                    }
                }
            }

            item {
                Column(Modifier.width(150.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        IconButton(
                            onClick = { navController.navigate("Home") },
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.sauces),
                                contentDescription = "Sauces",
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
                            text = "Sauces",
                            textAlign = TextAlign.Center,
                            maxLines = 2,
//                        modifier = Modifier.width(75.dp)
                        )
                    }
                }
            }

        }
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            item{
                Box(
                    modifier = Modifier.background(color = Color.Magenta),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ranch_feed_combo),
                        contentDescription = "Ranch Feed Combo",
                        tint = Color.Unspecified
                    )
                }
            }
            item{
                Box(
                    modifier = Modifier.background(color = Color.Red),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.rebel_feed_combo),
                        contentDescription = "Rebel Feed Combo",
                        tint = Color.Unspecified,
                        modifier = Modifier
                    )
                }
            }
            item{
                Box(
                    modifier = Modifier.background(color = Color.Blue),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ranch_wrangler_burger),
                        contentDescription = "Ranch Wrangler Burger",
                        tint = Color.Unspecified
                    )
                }
            }
            item{
                Box(
                    modifier = Modifier.background(color = Color.Cyan),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.iron_rebel_burger),
                        contentDescription = "Iron Rebel Burger",
                        tint = Color.Unspecified
                    )
                }
            }
            item{
                Box(
                    modifier = Modifier.background(color = Color.Green),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.chev_brulee_drink),
                        contentDescription = "Chev Brulee",
                        tint = Color.Unspecified
                    )
                }
            }
            item{
                Box(
                    modifier = Modifier.background(color = Color.Gray),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.loaded_fries),
                        contentDescription = "Loaded Fries",
                        tint = Color.Unspecified
                    )
                }
            }



        }
        }
//    Divider(
//        thickness = 1.dp,
//        color = Color(R.color.burger_fuel_purple)
//    )
}

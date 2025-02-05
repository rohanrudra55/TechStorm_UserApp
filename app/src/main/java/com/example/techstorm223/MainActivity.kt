package com.example.techstorm223

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import com.example.techstorm223.R
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.GridCells
import androidx.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(){

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        }
    ) {
        Navigation(navController = navController)
    }

}

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState){

    TopAppBar(
        title = { Text(text = "Tech Storm 2.23", fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )

}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController){

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Schedule,
        NavigationItem.Results,
        NavigationItem.Sponcers,
        NavigationItem.Team,
        NavigationItem.Developers,
        NavigationItem.Aboutus

    )

    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            )

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { items ->
            DrawerItem(item = items, selected = currentRoute == items.route, onItemClick = {

                navController.navigate(items.route){
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route){
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }

                scope.launch {
                    scaffoldState.drawerState.close()
                }

            })
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Tech Storm 2.23",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )

    }
}

@Composable
fun DrawerItem(item: NavigationItem, selected: Boolean, onItemClick: (NavigationItem) -> Unit){
    val background = if(selected) R.color.grey else android.R.color.transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(45.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)
    ) {

        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black
        )

    }

}

@Composable
fun HomeScreen(navController: NavHostController){

    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        item{
            Box(
                modifier = Modifier

                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .clickable {
                        navController.navigate(NavigationItem.Rovers_List.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_1
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier

                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .clickable {
                        navController.navigate(NavigationItem.Rovers_List.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_1
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier

                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .clickable {
                        navController.navigate(NavigationItem.Rovers_List.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_1
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier

                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .clickable {
                        navController.navigate(NavigationItem.Rovers_List.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_1
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier

                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .clickable {
                        navController.navigate(NavigationItem.Rovers_List.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_1
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier

                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .clickable {
                        navController.navigate(NavigationItem.Rovers_List.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_1
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier

                    .clip(RectangleShape)
                    .background(Color.Red)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(NavigationItem.Rovers_List.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_1
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier

                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .clickable {
                        navController.navigate(NavigationItem.Rovers_List.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_1
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier

                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .clickable {
                        navController.navigate(NavigationItem.Rovers_List.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_1
                    ), contentDescription = "Null"
                )
            }
        }
    }

}






@Composable
fun ResultsScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Settings Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun SponcersScreen(){

        Text(

            text = "Sponcer Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun ScheduleScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Schedule Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun AboutusScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Schedule Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun ContactScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Contact Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

    }
}
@Composable
fun TeamScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Team Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun DevelopersScreen() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        Card(

        ) {
            val imageModifier = Modifier
                .size(250.dp)

            Image(
                painter = painterResource(id = R.drawable.image_1),
                contentDescription = null,
                modifier = imageModifier
            )
            Column(modifier = Modifier.padding(5.dp)) {
                Text("Maaitrayo Das", fontWeight = FontWeight.W700)
                Text("+0 12345678")
                Text("XYZ city", fontWeight = FontWeight.W300)

            }
        }


        Card(

        ) {
            val imageModifier = Modifier
                .size(250.dp)
            Image(painter = painterResource(id = R.drawable.image_1), contentDescription = null,modifier =imageModifier)
            Column(modifier = Modifier.padding(5.dp)) {
                Text("Dipnarayan Sen", fontWeight = FontWeight.W700)
                Text("+0 12345678")
                Text("XYZ city", fontWeight = FontWeight.W300)

            }
        }
        Card(

        ) {
            val imageModifier = Modifier
                .size(250.dp)
            Image(painter = painterResource(id = R.drawable.image_1), contentDescription = null,modifier =imageModifier)
            Column(modifier = Modifier.padding(5.dp)) {
                Text("Anushka Mukherjee", fontWeight = FontWeight.W700)
                Text("+0 12345678")
                Text("XYZ city", fontWeight = FontWeight.W300)

            }
            }

        }

}

@Composable
fun RoversList(navController: NavHostController){

    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        item{
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RectangleShape)
                    .background(Color.Red)
                    .clickable {
//                        navController.navigate(NavigationItem.Rovers_List2.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_2
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RectangleShape)
                    .background(Color.Red)
                    .clickable {
//                        navController.navigate(NavigationItem.Rovers_List2.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_2
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RectangleShape)
                    .background(Color.Red)
                    .clickable {
//                        navController.navigate(NavigationItem.Rovers_List2.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_2
                    ), contentDescription = "Null"
                )
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RectangleShape)
                    .background(Color.Red)
                    .clickable {
//                        navController.navigate(NavigationItem.Rovers_List2.route)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.image_2
                    ), contentDescription = "Null"
                )
            }

        }
    }

}


@Composable
fun Navigation(navController: NavHostController){

    NavHost(navController, startDestination = NavigationItem.Home.route){

        composable(NavigationItem.Home.route){
            HomeScreen(navController)
        }
        composable(NavigationItem.Schedule.route){
            ScheduleScreen()
        }

        composable(NavigationItem.Rovers_List.route){
            RoversList(navController)
        }

        composable(NavigationItem.Share.route){
            ShareScreen()
        }
        composable(NavigationItem.Team.route){
            TeamScreen()
        }
        composable(NavigationItem.Contact.route){
            ContactScreen()
        }
        composable(NavigationItem.Sponcers.route){
            SponcersScreen()
        }
        composable(NavigationItem.Results.route){
            ResultsScreen()
        }

        composable(NavigationItem.Developers.route){
            DevelopersScreen()
        }
        composable(NavigationItem.Aboutus.route){
            AboutusScreen()
        }


    }
}

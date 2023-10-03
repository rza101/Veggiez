package com.rhezarijaya.veggiez.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rhezarijaya.veggiez.R
import com.rhezarijaya.veggiez.ui.component.FavoriteFloatingActionButton
import com.rhezarijaya.veggiez.ui.navigation.Screen
import com.rhezarijaya.veggiez.ui.screen.about.AboutScreen
import com.rhezarijaya.veggiez.ui.screen.detail.DetailScreen
import com.rhezarijaya.veggiez.ui.screen.favorite.FavoriteScreen
import com.rhezarijaya.veggiez.ui.screen.main.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeggiezApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    veggiezAppViewModel: VeggiezAppViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    )
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var isOnFavoriteState by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    when (currentRoute) {
                        Screen.Main.route -> {
                            Text(stringResource(R.string.app_name))
                        }

                        Screen.Detail.route -> {
                            Text(stringResource(R.string.detail))
                        }

                        Screen.Favorite.route -> {
                            Text(stringResource(R.string.favorite))
                        }

                        Screen.About.route -> {
                            Text(stringResource(R.string.about))
                        }
                    }
                },
                actions = {
                    if (currentRoute == Screen.Main.route) {
                        IconButton(onClick = {
                            navHostController.navigate(Screen.Favorite.route)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "favorite_page",
                            )
                        }

                        IconButton(onClick = {
                            navHostController.navigate(Screen.About.route)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "about_page",
                            )
                        }
                    }
                },
                navigationIcon = {
                    when (currentRoute) {
                        Screen.Detail.route, Screen.Favorite.route, Screen.About.route -> {
                            IconButton(onClick = {
                                navHostController.navigateUp()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.back),
                                )
                            }
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            if (currentRoute == Screen.Detail.route) {
                val vegetableId = navBackStackEntry?.arguments?.getString("id")

                vegetableId?.let { id ->
                    isOnFavoriteState = veggiezAppViewModel.isOnFavorite(id)

                    FavoriteFloatingActionButton(
                        isFavorite = isOnFavoriteState,
                        vegetableId = vegetableId,
                        onClick = {
                            if (!isOnFavoriteState) {
                                veggiezAppViewModel.addFavorite(id)
                            } else {
                                veggiezAppViewModel.removeFavorite(id)
                            }

                            isOnFavoriteState = veggiezAppViewModel.isOnFavorite(id)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Main.route,
            modifier = modifier.padding(innerPadding),
        ) {
            composable(Screen.Main.route) {
                MainScreen { id ->
                    navHostController.navigate("detail/$id")
                }
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.StringType
                    }
                )
            ) {
                val vegetableId = it.arguments?.getString("id")
                vegetableId?.let { id ->
                    DetailScreen(vegetableId = id)
                }
            }

            composable(Screen.Favorite.route) {
                FavoriteScreen { id ->
                    navHostController.navigate("detail/$id")
                }
            }

            composable(Screen.About.route) {
                AboutScreen()
            }
        }
    }
}

@Composable
@Preview(
    device = Devices.PIXEL_4,
    showBackground = true,
)
fun VeggiezAppPreview() {
    MaterialTheme {
        Surface {
            VeggiezApp()
        }
    }
}
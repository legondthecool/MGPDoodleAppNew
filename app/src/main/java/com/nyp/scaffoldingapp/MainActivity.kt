package com.nyp.scaffoldingapp

import android.os.Bundle
import android.preference.PreferenceActivity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyp.scaffoldingapp.ui.pages.*
import com.nyp.scaffoldingapp.ui.theme.ScaffoldingTheme
import kotlinx.coroutines.launch

/**
 * The four bottom tabs. An enum keeps the list in one place — add a tab here
 * and the navigation bar updates itself.
 */
enum class Tab(val label: String, @DrawableRes val icon: Int) {
    PAGE1("Page 1", R.drawable.ic_home),
    PAGE2("Page 2", R.drawable.ic_email),
    PAGE3("Page 3", R.drawable.ic_list),
    PAGE4("Page 4", R.drawable.ic_star)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldingTheme {
                AppScaffold()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    // rememberSaveable, not remember: the selected tab must survive a rotation.
    var selectedTab by rememberSaveable { mutableStateOf(Tab.PAGE1) }
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {
            ModalDrawerSheet {

                Text(
                    "Menu",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = true,
                    onClick = {
                        selectedTab = Tab.PAGE1
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = " ",
                            modifier = Modifier.size(16.dp)
                        )
                    },
                )
                //settings
                NavigationDrawerItem(
                    label = { Text("Settings") },
                    selected = false,
                    onClick = {
                        selectedTab = Tab.PAGE2
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = " ",
                            modifier = Modifier.size(16.dp)
                        )
                    },
                )

                NavigationDrawerItem(
                    label = { Text("Sketch")},
                    selected = false,
                    onClick = {
                        selectedTab = Tab.PAGE3
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.BorderColor,
                            contentDescription = " ",
                            modifier = Modifier.size(16.dp)
                        )
                    },

                )

                NavigationDrawerItem(
                    label = { Text("Log in")},
                    selected = false,
                    onClick = {
                        selectedTab = Tab.PAGE4
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = " ",
                            modifier = Modifier.size(16.dp)
                        )
                    },
                )

            }
        }
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("doodle") },

                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { padding ->

            Box(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                // Text("Test")
            }
        }
        when (selectedTab) {
            Tab.PAGE1 -> Page1()
            Tab.PAGE2 -> Page2()
            Tab.PAGE3 -> Page3()
            Tab.PAGE4 -> Page4()
        }
    }
}


@Composable
fun Status(loading: Boolean) {
    if (loading) {
        Text("Loading...")
    } else {
        Text("Done")
    }
}
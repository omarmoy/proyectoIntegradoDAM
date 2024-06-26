package com.dam2.proyectocliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.models.Category
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.models.Role
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.ui.screens.consumer.ListActivities
import com.dam2.proyectocliente.ui.menus.consumer.SearchMenu
import com.dam2.proyectocliente.ui.menus.ChatsMenu
import com.dam2.proyectocliente.ui.menus.consumer.MainMenu
import com.dam2.proyectocliente.ui.menus.consumer.UserMenu
import com.dam2.proyectocliente.ui.screens.consumer.ViewActivity
import com.dam2.proyectocliente.ui.screens.consumer.VistaAnuncio
import com.dam2.proyectocliente.ui.screens.Chat
import com.dam2.proyectocliente.ui.forms.FormActivity
import com.dam2.proyectocliente.ui.forms.FormAdvertisement
import com.dam2.proyectocliente.ui.forms.EditActivity
import com.dam2.proyectocliente.ui.forms.EditAdvertisement
import com.dam2.proyectocliente.ui.forms.SelectPicture
import com.dam2.proyectocliente.ui.home.ErrorScreen
import com.dam2.proyectocliente.ui.home.Home
import com.dam2.proyectocliente.ui.home.LoadingScreen
import com.dam2.proyectocliente.ui.home.Login
import com.dam2.proyectocliente.ui.menus.pro.SearchAdsMenu
import com.dam2.proyectocliente.ui.menus.pro.ReservationMenu
import com.dam2.proyectocliente.ui.menus.pro.MainMenuPro
import com.dam2.proyectocliente.ui.forms.signUp.ChooiceAvatar
import com.dam2.proyectocliente.ui.forms.signUp.ConfirmarRegistro
import com.dam2.proyectocliente.ui.forms.signUp.ChooseRole
import com.dam2.proyectocliente.ui.forms.signUp.ChooseTypePro
import com.dam2.proyectocliente.ui.forms.signUp.CompanyData
import com.dam2.proyectocliente.ui.forms.signUp.NuevoUsuarioDatos
import com.dam2.proyectocliente.ui.forms.signUp.FormMailPassword
import com.dam2.proyectocliente.ui.menus.DeleteUser
import com.dam2.proyectocliente.ui.resources.LoandigDialogo
import com.dam2.proyectocliente.ui.screens.pro.ViewActivityPro
import com.dam2.proyectocliente.ui.screens.pro.ViewAdvertisementsPro
import com.dam2.proyectocliente.ui.screens.pro.ActivityReserves
import com.dam2.proyectocliente.utils.Painter
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    vm: AppViewModel = viewModel()
) {
    val uiState by vm.uiState.collectAsState()
    var loandig by rememberSaveable { mutableStateOf(false) }
    var refresh by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {},
        content = { innerPadding -> Host(innerPadding, navController, vm, uiState) },
        bottomBar = {
            if (uiState.proMode)
                NavigationPanelPro(navController, vm, uiState)
            else
                NavigationPanel(navController, vm, uiState)
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {

            if (vm.login && uiState.showNavigationPanel) {
                IconButton(onClick = {
                    loandig = true
                    refresh = true
                }) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "refresh",
                        tint = NegroClaro
                    )

                }
            }
        }

    )

    if(loandig){
        LoandigDialogo()
    }

    if(refresh){
        LaunchedEffect(Unit) {
            loandig = vm.refresh()
            refresh=false
            // Navega de nuevo a la pantalla actual para forzar un refresco
            navController.currentBackStackEntry?.destination?.route?.let {
                navController.navigate(it)
            }
        }
    }
}

@Composable
fun Host(
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState
) {
    NavHost(

        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Screens.inicio.name
    ) {

        //Pantallas principales
        composable(route = Screens.afterLogging.name) {
            if (uiState.user != null) {
                when (vm.userUiState) {
                    is UserUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
                    is UserUiState.Success -> {
                        vm.cambiarBotonNav(0)
                        if (uiState.user.role == Role.PROVIDER)
                            MainMenuPro(navController, vm, uiState)
                        else {
                            MainMenu(navController, vm, uiState)
                        }
                    }

                    is UserUiState.Error -> ErrorScreen(navController)
                }
            } else
                ErrorScreen(navController)
        }

        composable(route = Screens.menuPrincipal.name) {
            MainMenu(navController, vm, uiState)
        }

        composable(route = Screens.menuBuscar.name) {
            SearchMenu(navController, vm, uiState)
        }

        composable(route = Screens.menuBusquedaDirecta.name) {
            SearchMenu(navController, vm, uiState, true)
        }

        composable(route = Screens.menuMensajes.name) {
            ChatsMenu(navController, vm, uiState)
        }

        composable(route = Screens.menuUsuario.name) {
            UserMenu(navController = navController, vm, uiState)
        }

        //SubPantallas
        composable(route = Screens.listaReservas.name) {
            ListActivities(
                "Mis reservas",
                uiState.user!!.activitiesReserved,
                navController,
                vm,
                uiState
            )
        }
        composable(route = Screens.listaFavoritos.name) {
            ListActivities(
                "Favoritos",
                uiState.user!!.activitiesFav,
                navController,
                vm,
                uiState
            )
        }
        composable(route = Screens.vistaActividad.name) {
            ViewActivity(navController, uiState.selectedActivity!!, vm, uiState)
        }
        composable(route = Screens.chat.name) {
            Chat(navController, uiState.selectedChat, vm, uiState)
        }
        composable(route = Screens.vistaAnuncio.name) {
            VistaAnuncio(navController, uiState.advertisementSeleccionado, vm)
        }

        //Login
        composable(route = Screens.inicio.name) {
            Home(navController = navController)
        }
        composable(route = Screens.login.name) {
            Login(navController = navController, vm)
        }

        //Registro
        composable(route = Screens.elegirRol.name) {
            ChooseRole(navController = navController, vm)
        }
        composable(route = Screens.elegirTipoPro.name) {
            ChooseTypePro(navController = navController, vm)
        }
        composable(route = Screens.nuevoUsuario.name) {
            FormMailPassword(navController = navController, vm, uiState)
        }
        composable(route = Screens.nuevoUsuarioDatos.name) {
            NuevoUsuarioDatos(navController = navController, vm, uiState)
        }
        composable(route = Screens.nuevaEmpresaDatos.name) {
            CompanyData(navController = navController, vm)
        }
        composable(route = Screens.addImagen.name) {
            ChooiceAvatar(navController = navController, vm, uiState)
        }
        composable(route = Screens.confirmarRegistro.name) {
            ConfirmarRegistro(navController = navController)
        }

        //formularios y previstas Anuncio
        composable(route = Screens.formularioAnuncio.name) {
            FormAdvertisement(navController = navController, vm)
        }
        composable(route = Screens.previewNuevoAnuncio.name) {
            VistaAnuncio(navController, uiState.newAdvertisement!!, vm, true)
        }
        composable(route = Screens.modificarAnuncio.name) {
            EditAdvertisement(navController, vm, uiState.modAdvertisement!!)
        }

        //formularios y previstas ACTIVIDAD
        composable(route = Screens.formularioActividad.name) {
            FormActivity(navController = navController, vm, uiState)
        }
        composable(route = Screens.previewNuevaActividad.name) {
            ViewActivityPro(navController, uiState.newActivity!!, vm, true)
        }
        composable(route = Screens.vistaActividadPro.name) {
            ViewActivityPro(navController, uiState.selectedActivity!!, vm)
        }

        composable(route = Screens.modificarActividad.name) {
            EditActivity(navController, vm, uiState, uiState.modActivity!!)
        }

        //Menus Pro
        composable(route = Screens.menuPrincipalPro.name) {
            MainMenuPro(navController, vm, uiState)
        }
        composable(route = Screens.menuBusquedaAnuncios.name) {
            SearchAdsMenu(navController, vm, uiState)
        }
        composable(route = Screens.vistaAnuncioPro.name) {
            ViewAdvertisementsPro(navController, uiState.advertisementSeleccionado, vm)
        }
        composable(route = Screens.menuReservas.name) {
            ReservationMenu(navController, vm, uiState)
        }
        composable(route = Screens.vistaReservasActividad.name) {
            ActivityReserves(navController, vm, uiState.selectedActivityWithReservation!!)
        }

        //Selectors Pictures
        composable(route = Screens.selectActivityPicture.name) {
            SelectPicture(navController, vm, Painter.activityPictures)
        }
        composable(route = Screens.selectProfilePicture.name) {
            SelectPicture(navController, vm, Painter.profilePictures)
        }
        composable(route = Screens.deleteuser.name) {
            DeleteUser(navController, vm)
        }

    }
}

@Composable
fun NavigationPanel(navController: NavHostController, vm: AppViewModel, uiState: UiState) {
    if (uiState.showNavigationPanel) {
        Box(
            modifier = Modifier
                .background(Gris2)
                .padding(top = 1.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(BlancoFondo)
                    .padding(12.dp)
            ) {
                IconButton(onClick = {
                    vm.cambiarBotonNav(0)
                    vm.setCategoryIndex()
                    navController.navigate(Screens.menuPrincipal.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Inicio",
                        tint = if (uiState.buttonsNav[0]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(1)
                    vm.selectCategory(Category.TODO)
                    navController.navigate(Screens.menuBuscar.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = if (uiState.buttonsNav[1]) AmarilloPastel else NegroClaro
                    )
                }

                IconButton(onClick = {
                    vm.cambiarBotonNav(2)
                    navController.navigate(Screens.menuMensajes.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = "Mensajes",
                        tint = if (uiState.buttonsNav[2]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(3)
                    navController.navigate(Screens.menuUsuario.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Mi Cuenta",
                        tint = if (uiState.buttonsNav[3]) AmarilloPastel else NegroClaro
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationPanelPro(navController: NavHostController, vm: AppViewModel, uiState: UiState) {
    if (uiState.showNavigationPanel) {
        Box(
            modifier = Modifier
                .background(Gris2)
                .padding(top = 1.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(BlancoFondo)
                    .padding(12.dp)
            ) {
                IconButton(onClick = {
                    vm.cambiarBotonNav(0)
                    navController.navigate(Screens.menuPrincipalPro.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menú principal Pro",
                        tint = if (uiState.buttonsNav[0]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(1)
                    navController.navigate(Screens.menuReservas.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Reservas",
                        tint = if (uiState.buttonsNav[1]) AmarilloPastel else NegroClaro
                    )
                }

                IconButton(onClick = {
                    vm.cambiarBotonNav(2)
                    navController.navigate(Screens.menuMensajes.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = "Mensajes",
                        tint = if (uiState.buttonsNav[2]) AmarilloPastel else NegroClaro
                    )
                }
                IconButton(onClick = {
                    vm.cambiarBotonNav(3)
                    navController.navigate(Screens.menuBusquedaAnuncios.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "buscar",
                        tint = if (uiState.buttonsNav[3]) AmarilloPastel else NegroClaro
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationPanelPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
//    NavigationPanelPro(navController, vm, uiState)
    NavigationPanel(navController, vm, uiState)
}
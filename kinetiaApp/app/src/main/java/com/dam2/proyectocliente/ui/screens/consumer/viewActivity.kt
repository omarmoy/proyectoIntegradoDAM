package com.dam2.proyectocliente.ui.screens.consumer

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.moker.Moker
import com.dam2.proyectocliente.utils.Painter
import com.dam2.proyectocliente.utils.dateToString
import com.dam2.proyectocliente.utils.showDate
import com.dam2.proyectocliente.utils.timeToString
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAguaClaro
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewActivity(
    navController: NavHostController,
    activity: Activity,
    vm: AppViewModel,
    estado: UiState
) {

    Scaffold(
        topBar = {
            TopBarActivity(navController, activity, vm, estado)
        },
        content = { innerPadding ->
            ContentActivity(
                innerPadding,
                navController,
                activity,
                vm,
                estado
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarActivity(
    navController: NavHostController, activity: Activity, vm: AppViewModel, estado: UiState
) {

    val triggerFav = remember { mutableIntStateOf(0) }
    fun refreshFav() {
        triggerFav.intValue++
    }
    LaunchedEffect(triggerFav.intValue) {
    }

    TopAppBar(
        title = {
            //Text(actividad.titulo, overflow = TextOverflow.Ellipsis)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = {
                vm.mostrarPanelNavegacion()
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Cerrar"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                if (estado.isFavorite(activity))
                    vm.deleteFav(activity)
                else
                    vm.addFav(activity)
                refreshFav()
            }) {
                Icon(
                    imageVector = if (estado.isFavorite(activity)) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Fav",
                    tint = AzulAguaOscuro
                )
            }

        }
    )
}


@Composable
fun ContentActivity(
    innerPadding: PaddingValues,
    navController: NavHostController,
    activity: Activity,
    vm: AppViewModel,
    estado: UiState
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = BlancoFondo)
    ) {


        val triggerReservation = remember { mutableIntStateOf(0) }
        val refreshReservation: () -> Unit = { triggerReservation.intValue++ }
        LaunchedEffect(triggerReservation.intValue) {
        }

        Image(
            painter = painterResource(id = Painter.getActivityPictureInt(activity.picture)),
            contentDescription = activity.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f),
            contentScale = ContentScale.Crop
        )

        TitlePanel(activity)
        DataPanel(activity)
        ButtonsPanel(navController, activity, vm, estado, refreshReservation)
        ContentActivity(activity, vm, estado, refreshReservation)
    }
}

@Composable
fun TitlePanel(activity: Activity) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(top = 12.dp, end = 12.dp, start = 12.dp, bottom = 1.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = activity.title, color = AzulAguaOscuro, fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = activity.location, color = AzulAguaClaro, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = activity.userName, color = AzulAguaClaro, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Plazas disponibles: " + activity.availableVacancies,
                    color = AzulAguaClaro, fontSize = 14.sp
                )
            }
            IconButton(
                modifier = Modifier.weight(.2f),
                onClick = {
                    /*TODO botón compartir, probarlo en el movil*/
                    shareActivity(context, activity.toString())
                }) {
                Icon(
                    imageVector = Icons.Filled.Share, contentDescription = "compartir",
                    tint = AzulAguaOscuro,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Text(
            text = "publicado: " + showDate(
                activity.createdAt.atZone(ZoneId.systemDefault()).toLocalDateTime()
            ),
            textAlign = TextAlign.End, color = AzulAguaClaro, fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun DataPanel(activity: Activity) {
    Surface(
        modifier = Modifier
            .background(color = AmarilloPastel)
            .padding(top = 1.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = BlancoFondo)
                .padding(top = 12.dp)
        ) {
            val tamIcon = 40.dp
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.DateRange, contentDescription = "fecha",
                    tint = AzulAguaClaro,
                    modifier = Modifier.size(tamIcon)
                )
                Text(
                    text = dateToString(activity.date),
                    color = AzulAguaClaro,
                    fontSize = 14.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_clock),
                    contentDescription = "duración",
                    tint = AzulAguaClaro,
                    modifier = Modifier.size(tamIcon)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = timeToString(activity.date),
                    color = AzulAguaClaro,
                    fontSize = 14.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_euro),
                    contentDescription = "euro",
                    tint = AzulAguaClaro,
                    modifier = Modifier.size(tamIcon)
                )
                Text(
                    text = activity.price.toString() + " euros",
                    color = AzulAguaClaro,
                    fontSize = 14.sp
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonsPanel(
    navController: NavHostController,
    activity: Activity,
    vm: AppViewModel,
    estado: UiState,
    refreshComposable: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 70.dp, end = 70.dp, bottom = 12.dp)
    ) {
        Button(
            onClick = { vm.reserveActivity(activity); refreshComposable() },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(AmarilloPastel),
            contentPadding = PaddingValues(8.dp, 0.dp),
            enabled = activity.availableVacancies != 0
                    && !estado.user!!.activitiesReserved.contains(activity)
        ) {
            val texto =
                if (estado.user!!.activitiesReserved.contains(activity))
                    "Reservado"
                else
                    "Reservar"
            Text(text = texto, color = Color.Black)
        }

        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = AzulAguaOscuro),
            modifier = Modifier.size(50.dp),
            onClick = {
                vm.createChatIfNoExist(activity)
                vm.ocultarPanelNavegacion()
                navController.navigate(Screens.chat.name)
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "contactar",
                    tint = Color.White,
                    modifier = Modifier.size(38.dp)
                )
            }
        }

    }
}


private fun shareActivity(context: Context, activity: String) {
    // Create an ACTION_SEND implicit intent with order details in the intent extras
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "BÚSCALO EN KINETIA")
        putExtra(Intent.EXTRA_TEXT, activity)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            "Kinetia App"
        )
    )
}

@Composable
fun ContentActivity(
    activity: Activity,
    vm: AppViewModel,
    estado: UiState,
    refreshComposable: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = activity.description,
                textAlign = TextAlign.Justify,
                fontSize = 14.sp
            )
        }

        if (activity.description.length > 1399) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { vm.reserveActivity(activity); refreshComposable() },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(AmarilloPastel),
                contentPadding = PaddingValues(8.dp, 0.dp),
                enabled = activity.availableVacancies != 0
                        && !estado.user!!.activitiesReserved.contains(activity)
            ) {
                val texto =
                    if (estado.user!!.activitiesReserved.contains(activity))
                        "Reservado"
                    else
                        "Reservar"
                Text(text = texto, color = Color.Black)
            }

        }

        if (estado.user!!.activitiesReserved.contains(activity)) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { vm.cancelReservation(activity); refreshComposable() },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(AzulAguaOscuro),
                contentPadding = PaddingValues(8.dp, 0.dp)
            ) {
                Text(text = "Cancelar Reserva", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}


/**
 * VISTA PREVIA
 */
@Preview(showBackground = true)
@Composable
fun ActivityPreview() {
    val vm: AppViewModel = viewModel()
    val navController = rememberNavController()
    val a = Moker.activity
    val estado by vm.uiState.collectAsState()
    ViewActivity(navController = navController, activity = a, vm, estado)
}
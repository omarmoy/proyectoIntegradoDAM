package com.dam2.proyectocliente.ui.forms

import ComboBox
import ComboBoxCategory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.utils.textFieldEmpty
import com.dam2.proyectocliente.utils.isDateActivityValid
import com.dam2.proyectocliente.models.Category
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.ui.resources.DialogInfo
import com.dam2.proyectocliente.ui.resources.TextFieldWithHeader
import com.dam2.proyectocliente.ui.resources.TextFieldEnterNumber
import com.example.proyectocliente.R
import com.example.proyectocliente.ui.theme.AmarilloPastel
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo
import com.example.proyectocliente.ui.theme.Gris2
import com.example.proyectocliente.ui.theme.NegroClaro
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormActivity(
    navController: NavHostController,
    vm: AppViewModel,
    uiState: UiState,
) {

    val categories: ArrayList<Category> =
        ArrayList(Category.values().filter { it != Category.TODO })
    val listaHoras = arrayListOf(
        "00",
        "01",
        "02",
        "04",
        "05",
        "06",
        "07",
        "08",
        "09",
        "10",
        "11",
        "12",
        "13",
        "14",
        "15",
        "16",
        "17",
        "18",
        "19",
        "20",
        "21",
        "22",
        "23"
    )
    val listaMinutos = arrayListOf("00", "15", "30", "45")

    var titulo by rememberSaveable { mutableStateOf("") }
    var precioT by rememberSaveable { mutableStateOf("") }
    var ubicacion by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf<Category?>(null) }
    var destacado by rememberSaveable { mutableStateOf(false) }
    var contenido by rememberSaveable { mutableStateOf("") }
    var diaT by rememberSaveable { mutableStateOf("") }
    var mesT by rememberSaveable { mutableStateOf("") }
    var anioT by rememberSaveable { mutableStateOf(LocalDate.now().year.toString()) }
    var horaT by rememberSaveable { mutableStateOf("") }
    var minutosT by rememberSaveable { mutableStateOf("") }
    var nPlazasT by rememberSaveable { mutableStateOf("") }

    var error by rememberSaveable { mutableStateOf("") }
    val setError: (String) -> Unit = { e -> error = e }

    Scaffold(
        topBar = { TopBarFA(navController, vm) },
        bottomBar = {
            BottomBarCA(
                vm, navController, uiState, titulo, precioT, ubicacion, category, destacado,
                contenido, diaT, mesT, anioT, horaT, minutosT, nPlazasT, setError
            )
        }

    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            val padd = 40.dp
            Column(
                modifier = Modifier
                    .background(BlancoFondo)
                    .padding(start = padd, end = padd, top = 8.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(
                            id = if (uiState.selectedPicture == 0)
                                R.drawable.noimagen else uiState.selectedPicture
                        ),
                        contentDescription = "imagen",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f / 2f),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(Screens.selectActivityPicture.name)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AddCircle,
                                contentDescription = "addImagen",
                                tint = AzulAguaOscuro
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldWithHeader(
                    header = "Título",
                    value = titulo,
                    onValueChange = { titulo = it }
                )
                TextFieldWithHeader(
                    header = "Ubicación",
                    value = ubicacion,
                    onValueChange = { ubicacion = it }
                )

                Text(text = "Categoria", color = NegroClaro, fontSize = 16.sp)
                ComboBoxCategory(
                    options = categories,
                    onOptionChosen = { category = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Fecha y hora de inicio", color = NegroClaro, fontSize = 16.sp)
                Row(
//                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextFieldEnterNumber(
                        label = "día",
                        value = diaT,
                        onValueChange = { diaT = it },
                        modifier = Modifier
                            .width(60.dp)
                            .padding(end = 2.dp)
                    )
                    TextFieldEnterNumber(
                        label = "mes",
                        value = mesT,
                        onValueChange = { mesT = it },
                        modifier = Modifier
                            .width(60.dp)
                            .padding(end = 2.dp)
                    )
                    TextFieldEnterNumber(
                        label = "año",
                        value = anioT,
                        onValueChange = { anioT = it },
                        modifier = Modifier.width(80.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {

                    ComboBox(
                        labelText = { Text(text = "hora", fontSize = 12.sp) },
                        options = listaHoras,
                        onOptionChosen = { horaT = it },
                        modifier = Modifier.width(95.dp)
                    )

                    Spacer(modifier = Modifier.width(2.dp))
                    ComboBox(
                        labelText = { Text(text = "min.", fontSize = 12.sp) },
                        options = listaMinutos,
                        onOptionChosen = { minutosT = it },
                        modifier = Modifier.width(95.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = contenido,
                    onValueChange = { contenido = it },
                    label = { Text("Descripción") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = AzulAguaOscuro),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Precio: ", color = NegroClaro, fontSize = 16.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextFieldEnterNumber(
                            showLabel = false,
                            //NumberFormat.getCurrencyInstance().format(propina)
                            value = precioT,
                            onValueChange = { precioT = it },
                            modifier = Modifier.width(100.dp),
//                            imeAction = ImeAction.Done
                        )
                        Icon(
                            painter = painterResource(R.drawable.icon_euro),
                            contentDescription = "",
                            tint = AzulAguaOscuro
                        )
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Número de plazas: ", color = NegroClaro, fontSize = 16.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextFieldEnterNumber(
                            showLabel = false,
                            //NumberFormat.getCurrencyInstance().format(propina)
                            value = nPlazasT,
                            onValueChange = { nPlazasT = it },
                            modifier = Modifier.width(80.dp),
                            imeAction = ImeAction.Done
                        )

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Promocionar: ", color = NegroClaro, fontSize = 16.sp)
                    Switch(
                        checked = destacado,
                        onCheckedChange = { destacado = it },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = AmarilloPastel,
                        )
                    )
                }


                //fin
                Spacer(modifier = Modifier.height(20.dp))
            }

            // control de entrada
            when (error) {
                "campoVacio" -> {
                    DialogInfo(
                        onConfirmation = { error = "" },
                        dialogText = "Todos los campos son obligatorios"
                    )
                }

                "fecha" -> {
                    DialogInfo(
                        onConfirmation = { error = "" },
                        dialogText = "Introduzca una fecha válida, superior al día de hoy"
                    )
                }

                "precioNoInt" -> {
                    DialogInfo(
                        onConfirmation = { error = "" },
                        dialogText = "El precio debe tener formato numérico"
                    )
                }

                "plazasNoInt" -> {
                    DialogInfo(
                        onConfirmation = { error = "" },
                        dialogText = "El número de plazas debe tener formato numérico"
                    )
                }

                "noFoto" -> {
                    DialogInfo(
                        onConfirmation = { error = "" },
                        dialogText = "No ha elegido ninguna foto"
                    )
                }
            }


        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFA(navController: NavHostController, vm: AppViewModel) {
    TopAppBar(
        title = { Text(text = "Formulario Actividad") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
                vm.mostrarPanelNavegacion()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "volver",
                    tint = AzulAguaOscuro
                )
            }
        }
    )
}

@Composable
fun BottomBarCA(
    vm: AppViewModel,
    navController: NavHostController,
    uiState: UiState,
    titulo: String,
    precioT: String,
    ubicacion: String,
    category: Category?,
    destacado: Boolean,
    contenido: String,
    diaT: String,
    mesT: String,
    anioT: String,
    horaT: String,
    minutosT: String,
    nPlazasT: String,
    setError: (String) -> Unit
) {

    val day = diaT.toIntOrNull() ?: 0
    val month = mesT.toIntOrNull() ?: 0
    val year = anioT.toIntOrNull() ?: 0

    val fields = arrayListOf(
        titulo, precioT, ubicacion, category?.toString() ?: "", diaT, mesT, anioT, horaT,
        minutosT, contenido, nPlazasT, destacado.toString()
    )

    Box(
        modifier = Modifier
            .background(Gris2)
            .padding(top = 1.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .background(BlancoFondo)
        ) {
            TextButton(onClick = {
                if (textFieldEmpty(fields))
                    setError("campoVacio")
                else if (!isDateActivityValid(day, month, year))
                    setError("fecha")
                else if (precioT.toDoubleOrNull() == null)
                    setError("precioNoInt")
                else if (nPlazasT.toIntOrNull() == null) {
                    setError("plazasNoInt")
                } else if (uiState.selectedPicture == 0)
                    setError("noFoto")
                else {
                    vm.newActivity(fields)
                    navController.navigate(Screens.previewNuevaActividad.name)

                }

            }) {
                Text(text = "Vista previa", color = AzulAguaOscuro, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CAPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val uiState by vm.uiState.collectAsState()
    FormActivity(navController, vm, uiState)
}
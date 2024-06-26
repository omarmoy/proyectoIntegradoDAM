package com.dam2.proyectocliente.ui.forms.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dam2.proyectocliente.AppViewModel
import com.dam2.proyectocliente.models.Screens
import com.dam2.proyectocliente.ui.resources.DialogInfo
import com.dam2.proyectocliente.ui.resources.TextFieldWithHeader
import com.dam2.proyectocliente.utils.textFieldEmpty
import com.example.proyectocliente.ui.theme.AzulAguaOscuro
import com.example.proyectocliente.ui.theme.BlancoFondo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyData(navController: NavHostController, vm: AppViewModel) {

    var nombreEmpresa by rememberSaveable { mutableStateOf("") }
    var direccion by rememberSaveable { mutableStateOf("") }
    var cif by rememberSaveable { mutableStateOf("") }
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellido1 by rememberSaveable { mutableStateOf("") }
    var apellido2 by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = BlancoFondo),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "volver",
                            tint = AzulAguaOscuro
                        )
                    }
                }
            )
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlancoFondo)
            ) {
                TextButton(onClick = {
                    if (textFieldEmpty(
                            arrayListOf(nombre, apellido1, nombreEmpresa, direccion, cif))
                    ) {
                        error = true
                    } else {
                        vm.addFieldFormSignUp("nombre", nombre)
                        vm.addFieldFormSignUp("apellido1", apellido1)
                        vm.addFieldFormSignUp("apellido2", apellido2)
                        vm.addFieldFormSignUp("nombreEmpresa", nombreEmpresa)
                        vm.addFieldFormSignUp("direccion", direccion)
                        vm.addFieldFormSignUp("cif", cif)
                        navController.navigate(Screens.addImagen.name)
                    }

                }) {
                    Text(text = "Siguiente", color = AzulAguaOscuro, fontSize = 16.sp)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F4F4))
                .verticalScroll(rememberScrollState())
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Datos de tu Empresa",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = AzulAguaOscuro
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F4F4))
                    .padding(40.dp)
            ) {
                TextFieldWithHeader(
                    header = "Denominación",
                    value = nombreEmpresa,
                    onValueChange = { nombreEmpresa = it })
                TextFieldWithHeader(
                    header = "Dirección",
                    value = direccion,
                    onValueChange = { direccion = it })
                TextFieldWithHeader(
                    header = "CIF", value = cif, onValueChange = { cif = it }
                )

                Text(text = "Persona de contacto",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulAguaOscuro)
                Spacer(modifier = Modifier.height(8.dp))

                TextFieldWithHeader(
                    header = "Nombre",
                    value = nombre,
                    onValueChange = { nombre = it })
                TextFieldWithHeader(
                    header = "Primer apellido",
                    value = apellido1,
                    onValueChange = { apellido1 = it })
                TextFieldWithHeader(
                    header = "Segundo apellido",
                    value = apellido2,
                    onValueChange = { apellido2 = it },
                    imeAction = ImeAction.Done)

            }

            when {
                error -> {
                    DialogInfo(
                        onConfirmation = { error = false },
                        dialogText = "Todos los campos son obligatorios"
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ERegistroPreview() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    CompanyData(navController, vm)
}


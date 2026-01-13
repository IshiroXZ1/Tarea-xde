package com.example.tarea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavegacion()
        }
    }
}

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "contador"
    ) {
        composable("contador") {
            PantallaContador(navController)
        }
        composable("resultado/{valor}") { backStackEntry ->
            val valor = backStackEntry.arguments?.getString("valor") ?: "0"
            PantallaResultado(valor, navController)
        }
    }
}

@Composable
fun PantallaContador(navController: NavController) {
    var contador by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Contador: $contador", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { contador++ }) {
            Text("Sumar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { contador-- }) {
            Text("Restar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val resultado = contador * 2
            navController.navigate("resultado/$resultado")
        }) {
            Text("Ver Resultado x2")
        }
    }
}

@Composable
fun PantallaResultado(valor: String, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Valor final: $valor", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Regresar")
        }
    }
}

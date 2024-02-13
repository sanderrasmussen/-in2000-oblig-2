package no.uio.ifi.in2000.sanderas.oblig2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import no.uio.ifi.in2000.sanderas.oblig2.ui.home.HomeScreen
import no.uio.ifi.in2000.sanderas.oblig2.ui.party.PartyScreen
import no.uio.ifi.in2000.sanderas.oblig2.ui.theme.Sanderas_oblig2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeScreen: HomeScreen = HomeScreen()
        val PartyScreen: PartyScreen = PartyScreen()
        setContent {
            Sanderas_oblig2Theme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "HomeScreen") {
                        composable("HomeScreen") { homeScreen.homeScreen(navController = navController) }
                        composable(
                            "PartyScreen/{PartyId}",
                            arguments = listOf(navArgument("PartyId") { type = NavType.StringType })
                        ) { navBackStackEntry ->
                            val partyId = navBackStackEntry.arguments?.getString("PartyId")
                            PartyScreen.partyScreen(partyId)
                        }
                    }
                }


            }


        }
    }

}
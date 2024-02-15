package no.uio.ifi.in2000.sanderas.oblig2.ui.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartyInfo


class HomeScreen {


    @Composable
    fun homeScreen(homeScreenViewModel: HomeScreenViewModel = viewModel(), navController: NavController) {
        val partiesUIState by homeScreenViewModel.partiesUIState.collectAsState()

        val votesUIState by homeScreenViewModel.votesUIState.collectAsState()


        LazyColumn(
            modifier = Modifier

                .padding(20.dp)
                .fillMaxSize()
        ) {
            items(partiesUIState.parties.parties) { party ->
                Modifier.size(96.dp)
                    AlpacaCard(party = party, navController = navController)
                }
            item{
                dropDownMenu()
            }
            item {
                Text(text = "Henter stemmedata, dette kan ta noe tid.")
                showVoteList(votelist = votesUIState.votes)
            }

            }
        }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AlpacaCard(modifier: Modifier = Modifier, party: PartyInfo, navController: NavController) {

        Card(modifier = modifier, onClick = {
            navController.navigate("PartyScreen/"+party.id)

        } ) {
            Row(  modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Column (modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("${party.name}")
                    AsyncImage(
                        modifier = modifier
                            .size(96.dp)
                            .clip(CircleShape),
                        model = party.img,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Text(text = " ${party.leader}")

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .background(color = Color(android.graphics.Color.parseColor(party.color))), // Endre fargen som ønsket

                    )
                }
            }
        }

    }

    @Composable
    fun dropDownMenu() {
        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Button(onClick = { expanded = true }) {
                Text(text = "Velg district")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("District 1") },
                    onClick = { /* Handle edit! */ }
                    )
                DropdownMenuItem(
                    text = { Text("District 2") },
                    onClick = { /* Handle settings! */ },
                    )
                //HorizontalDivider()
                DropdownMenuItem(
                    text = { Text("District 3") },
                    onClick = { /* Handle send feedback! */ })

            }
        }
    }

}




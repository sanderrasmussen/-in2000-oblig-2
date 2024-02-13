package no.uio.ifi.in2000.sanderas.oblig2.ui.party

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartyInfo


class PartyScreen {
    @Composable
    fun partyScreen(Id: String?, PartyViewModel: PartyViewModel = viewModel(), navController: NavController) {
        val partyUIState by PartyViewModel.partyUIState.collectAsState()
        //val Id: String = Id ?: "0" //standardverdi på string i tillfelle den er null

        Row {
            PartyCard(party = partyUIState.party)
        }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PartyCard(modifier: Modifier = Modifier, party: PartyInfo) {

        Card(modifier = modifier) {
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Column(
                    modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("${party.name}")
                    AsyncImage(
                        modifier = Modifier.size(96.dp),
                        model = party.img,
                        contentDescription = null,
                    )
                    Text(text = " ${party.leader}")
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            //.height(20.dp)
                            .background(color = Color(android.graphics.Color.parseColor(party.color))), // Endre fargen som ønsket

                    )
                    Text(party.description)
                }
            }
        }
    }





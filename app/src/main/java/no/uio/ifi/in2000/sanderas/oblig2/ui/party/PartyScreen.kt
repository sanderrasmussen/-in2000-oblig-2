package no.uio.ifi.in2000.sanderas.oblig2.ui.party

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartyInfo



class PartyScreen {
    @Composable
    fun partyScreen(
        Id: String?,
        PartyViewModel: PartyViewModel = viewModel(),
        navController: NavController
    ) {
        val partyUIState by PartyViewModel.partyUIState.collectAsState()
        //val Id: String = Id ?: "0" //standardverdi på string i tillfelle den er null

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .fillMaxSize(),
        ) {
            item {
                PartyCard(party = partyUIState.party)

            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PartyCard(modifier: Modifier = Modifier, party: PartyInfo) {

        Card(modifier = modifier) {

            Column(
                modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                Text(party.description)
            }

        }
    }
    @Composable
    fun SmallTopAppBarExample() {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Small Top App Bar")
                    }
                )
            },
        ) { innerPadding ->
            (innerPadding)
        }
    }
}





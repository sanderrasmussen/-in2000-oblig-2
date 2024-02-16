package no.uio.ifi.in2000.sanderas.oblig2.ui.party

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
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
        val errorUIState by PartyViewModel.ErrorStateFlow.collectAsState()


        if (errorUIState){
            Snackbar()
        }

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .fillMaxSize(),
        ) {
            item {

                TopAppBar(
                    onBackClick = { navController.navigate("HomeScreen") },
                    title = partyUIState.party.name)
            }
            item{
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
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar(
        onBackClick: () -> Unit,
        title: String,
    ) {
        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }
    @Composable
    fun Snackbar() {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            floatingActionButton = {

                ExtendedFloatingActionButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "OOPs noe gikk galt"
                            )
                        }
                    }
                ) { Text("OOPs noe gikk galt") }
            },
            content = { innerPadding ->
                Text(
                    text = "OOPs noe gikk galt",
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .wrapContentSize()
                )
            }
        )
    }

}





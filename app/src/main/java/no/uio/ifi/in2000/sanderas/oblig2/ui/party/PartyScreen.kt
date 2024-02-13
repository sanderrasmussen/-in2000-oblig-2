package no.uio.ifi.in2000.sanderas.oblig2.ui.party

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class PartyScreen {
    @Composable
    fun partyScreen(Id: String?) {
        val Id: String = Id ?: "0" //standardverdi på string i tillfelle den er null
        Column(
            modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(Id)
        }
    }

}



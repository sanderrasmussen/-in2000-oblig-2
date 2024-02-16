package no.uio.ifi.in2000.sanderas.oblig2.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.uio.ifi.in2000.sanderas.oblig2.data.alpacas.PartiesResults
import no.uio.ifi.in2000.sanderas.oblig2.data.alpacas.partyResult




@Composable
fun showVoteList( votelist : PartiesResults) {

    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize()
            .padding(1.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(votelist.list) { party ->
            Column( modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = party.name,fontSize = 14.sp)
                Text(text = party.totalVotes.toString(), fontSize = 14.sp)
            }


        }
    }
}

@Preview
@Composable
fun VoteListPreview() {
    val data: PartiesResults = PartiesResults(listOf(
        partyResult("Party A", 100),
        partyResult("Party B", 150),
        partyResult("Party C", 80),
        partyResult("Party D", 120),
    ))

    //}
    Surface {
        showVoteList(data)
    }
}


package no.uio.ifi.in2000.sanderas.oblig2.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class VoteList {
    @Preview
    @Composable
    fun VoteList(){
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .fillMaxSize()){
            item() {


            }
        }

    }
}
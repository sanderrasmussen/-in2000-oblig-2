package no.uio.ifi.in2000.sanderas.oblig2.data.votes

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import no.uio.ifi.in2000.sanderas.oblig2.ui.home.District
import no.uio.ifi.in2000.sanderas.oblig2.ui.home.DistrictVotes

@Serializable
data class Party(
    val partyId : String,
    val votes : Int
)

@Serializable
data class Parties(val parties : List<Party>)
class AggregatedVotesDataSource {

    suspend fun getAggregatedVotesFromAPI(): List<DistrictVotes> {

        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json{ ignoreUnknownKeys = true })
            }
        }


        val partiesResult: Parties = client.get("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district3.json").body()
        client.close()

        return makeDistrictVoteList(partiesResult)

    }

    fun makeDistrictVoteList(list: Parties): List<DistrictVotes>{
        //lage DistrictVotes objecter
        var returnList : List<DistrictVotes> = emptyList()
        list.parties.forEach{
            val el = DistrictVotes(District.DISTRICT_3, it.partyId, it.votes)
            returnList= returnList + el
        }
        return returnList

    }
}
package no.uio.ifi.in2000.sanderas.oblig2.data.votes

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartiesInfo

class AggregatedVotesDataSource {

    suspend fun getPartyInfoFromAPI(): PartiesInfo {

        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json{ ignoreUnknownKeys = true })
            }
        }


        val parties: PartiesInfo = client.get("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/alpacaparties.json").body()
        client.close()

        return parties

    }
}
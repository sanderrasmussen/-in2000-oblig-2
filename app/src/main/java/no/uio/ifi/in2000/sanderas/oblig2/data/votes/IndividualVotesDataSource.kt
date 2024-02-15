package no.uio.ifi.in2000.sanderas.oblig2.data.votes

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import no.uio.ifi.in2000.sanderas.oblig2.ui.home.District
import no.uio.ifi.in2000.sanderas.oblig2.ui.home.DistrictVotes
import no.uio.ifi.in2000.sanderas.oblig2.ui.home.vote

//fetch data from district 1 and 2

class IndividualVotesDataSource {
    suspend fun getIndividualVotesFromAPI(): List<DistrictVotes> {

        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json{ ignoreUnknownKeys = true })
            }
        }


        val voteListDistrict1: List<vote> = client.get("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district1.json").body()
        val voteListDistrict2: List<vote> = client.get("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v24/obligatoriske-oppgaver/district2.json").body()
        client.close()

        val District1Reslult : List<DistrictVotes> = lagDistrictVotesListe(voteListDistrict1, "1")
        val District2Reslult : List<DistrictVotes> = lagDistrictVotesListe(voteListDistrict2, "2")

        return District1Reslult + District2Reslult

    }

    //spagethi kode under her
    fun lagDistrictVotesListe(DistrictVotes: List<vote>, districtId : String) : List<DistrictVotes>{
        var party1 :Int = 0 //number og votes
        var party2 :Int = 0
        var party3 :Int = 0
        var party4 :Int = 0


        DistrictVotes.forEach{
            if (it.id == "1"){party1+=1}
            else if (it.id == "2"){party2+=1}
            else if (it.id == "3"){party3+=1}
            else if (it.id == "4"){party4+=1}
        }

        var district = District.DISTRICT_1
        if (districtId == "2"){
            district = District.DISTRICT_2
        }


        val party1Result = DistrictVotes(district ,"1", party1)
        val party2Result = DistrictVotes(district , "2", party2)
        val party3Result = DistrictVotes(district , "3", party3)
        val party4Result = DistrictVotes(district , "1", party4)

        return listOf(party1Result, party2Result , party3Result, party4Result)

    }
}
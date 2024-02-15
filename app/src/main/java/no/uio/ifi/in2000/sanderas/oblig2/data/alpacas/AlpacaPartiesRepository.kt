package no.uio.ifi.in2000.sanderas.oblig2.data.alpacas

import no.uio.ifi.in2000.sanderas.oblig2.data.votes.VotesRepository
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartiesInfo
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.sanderas.oblig2.ui.home.DistrictVotes

data class partyResult(val name :String, val totalVotes : Int)
data class PartiesResults(val list : List<partyResult>)
class AlpacaPartiesRepository {
    val votesRepository = VotesRepository()
    suspend fun getPartyInfoList():PartiesInfo{

        return getPartyInfoFromAPI()
    }

    suspend fun getPartyInfo(id:String):PartyInfo{
        val list:PartiesInfo = getPartyInfoList()
        list.parties.forEach{
            if (it.id == id){
                return it //returner parti
            }
        }
        throw NoSuchElementException("Party with id $id not found")
    }

    suspend fun getDistrictVotes(id : String, district: String) : DistrictVotes{
        return votesRepository.getPartyVoteFromDistrict(id, district)
    }

    //DETTE ER HER JEG HAR KOMMET TIL

    suspend fun CountAllDistrictVotes(listOfParty_id: List<String>) : PartiesResults{
        var returnList : List<partyResult> = listOf()
        listOfParty_id.forEach {
            val dist1 = votesRepository.getPartyVoteFromDistrict(it, "1")
            val dist2 = votesRepository.getPartyVoteFromDistrict(it, "2")
            val dist3 = votesRepository.getPartyVoteFromDistrict(it, "3")

            val totalCount = dist1.numberOfVotesForParty + dist2.numberOfVotesForParty + dist3.numberOfVotesForParty
            val partyName = getPartyInfo(it).name
            returnList = returnList + partyResult(partyName, totalCount)
        }

        return PartiesResults(returnList)
    }

}

package no.uio.ifi.in2000.sanderas.oblig2.data.alpacas

import no.uio.ifi.in2000.sanderas.oblig2.data.votes.VotesRepository
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartiesInfo
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.sanderas.oblig2.ui.home.DistrictVotes


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
    suspend fun CountAllDistrictVotes(partyid : String) {
        val dist1 = votesRepository.getPartyVoteFromDistrict(partyid, "1")
        val dist2 = votesRepository.getPartyVoteFromDistrict(partyid, "2")
        val dist3 = votesRepository.getPartyVoteFromDistrict(partyid, "3")
        val totalCount = dist1.numberOfVotesForParty + dist2.numberOfVotesForParty + dist3.numberOfVotesForParty
        val partyName = getPartyInfo(partyid)
        return partyName.name , totalCount
    }
}

package no.uio.ifi.in2000.sanderas.oblig2.data.alpacas

import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartiesInfo
import no.uio.ifi.in2000.sanderas.oblig2.model.alpacas.PartyInfo

class AlpacaPartiesRepository {

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

}

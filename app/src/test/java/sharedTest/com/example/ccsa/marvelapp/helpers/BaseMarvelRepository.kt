package sharedTest.com.example.ccsa.marvelapp.helpers

import com.example.ccsa.marvelapp.data.MarvelRepository
import com.example.ccsa.marvelapp.model.MarvelCharacter
import io.reactivex.Single

class BaseMarvelRepository(
        val onGetCharacters: (String?) -> Single<List<MarvelCharacter>>
): MarvelRepository {

    override fun getAllCharacters(searchQuery: String?) = onGetCharacters(searchQuery)
}
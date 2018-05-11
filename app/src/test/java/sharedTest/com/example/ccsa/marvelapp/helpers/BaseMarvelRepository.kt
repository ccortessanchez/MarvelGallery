package sharedTest.com.example.ccsa.marvelapp.helpers

import com.example.ccsa.marvelapp.data.MarvelRepository
import com.example.ccsa.marvelapp.model.MarvelCharacter
import io.reactivex.Single

class BaseMarvelRepository(
        val onGetCharacters: () -> Single<List<MarvelCharacter>>
): MarvelRepository {

    override fun getAllCharacters() = onGetCharacters()
}
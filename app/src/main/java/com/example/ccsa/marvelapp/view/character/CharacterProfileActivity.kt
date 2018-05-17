package com.example.ccsa.marvelapp.view.character

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.ccsa.marvelapp.R
import com.example.ccsa.marvelapp.model.MarvelCharacter
import com.example.ccsa.marvelapp.view.common.extra
import com.example.ccsa.marvelapp.view.common.getIntent
import com.example.ccsa.marvelapp.view.common.loadImage
import kotlinx.android.synthetic.main.activity_character_profile.*

class CharacterProfileActivity : AppCompatActivity() {

    val character: MarvelCharacter by extra(CHARACTER_ARG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_profile)
        setUpToolbar()
        supportActionBar?.title = character.name
        descriptionView.text = character.description
        occurrencesView.text = makeOccurrencesText()
        headerView.loadImage(character.imageUrl, centerCropped = true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when {
        item.itemId == android.R.id.home -> onBackPressed().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun makeOccurrencesText(): String = ""
            .addList(R.string.ocurrences_comics_list_introduction, character.comics)
            .addList(R.string.ocurrences_series_list_introduction, character.series)
            .addList(R.string.ocurrences_stories_list_introduction, character.stories)
            .addList(R.string.ocurrences_events_list_introduction, character.events)

    private fun String.addList(introductionTextId: Int, list: List<String>): String {
        if (list.isEmpty()) return this
        val introductionText = getString(introductionTextId)
        val listText = list.joinToString(transform = {"$bullet $it"}, separator = "\n")
        return this + "$introductionText\n$listText\n\n"
    }

    companion object {
        private const val bullet = '\u2022'
        private const val CHARACTER_ARG = "com.example.marvelgallery.view.character.CharacterProfileActivity"

        fun start(context: Context, character: MarvelCharacter) {
            val intent = context.getIntent<CharacterProfileActivity>()
                    .apply { putExtra(CHARACTER_ARG, character)}
            context.startActivity(intent)
        }
    }
}

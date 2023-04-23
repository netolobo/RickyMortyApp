package com.netoloboapps.rickymortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.netoloboapps.rickymortyapp.ui.theme.RickyMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickyMortyAppTheme {
                val viewModel: RickMortyViewModel = hiltViewModel()
                val charactersFlow = viewModel.characters
                val lazyCharacterItem: LazyPagingItems<Character> =
                    charactersFlow.collectAsLazyPagingItems()
                CharactersScreen(lazyCharacterItem)
            }
        }
    }
}


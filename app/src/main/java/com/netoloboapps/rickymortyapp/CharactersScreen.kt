package com.netoloboapps.rickymortyapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.netoloboapps.rickymortyapp.ui.theme.RickyMortyAppTheme

@Composable
fun CharactersScreen(characters: LazyPagingItems<Character>) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp,
        ),
    ) {
        itemsIndexed(characters) { _, character ->
            if (character != null) {
                CharacterItem(
                    item = character
                )
            }
        }

        when (val refreshLoadState = characters.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    LoadingItem(
                        Modifier.fillParentMaxWidth()
                    )
                }
            }
            is LoadState.Error -> {
                val error = refreshLoadState.error
                item {
                    ErrorItem(
                        message = error.localizedMessage ?: "",
                        modifier = Modifier.fillParentMaxSize(),
                        onClick = { characters.retry() }
                    )
                }
            }
            else -> {}
        }

        when (val appendLoadState = characters.loadState.append) {
            is LoadState.Loading -> {
                item {
                    LoadingItem(Modifier.fillParentMaxWidth())
                }
            }
            is LoadState.Error -> {
                val error = appendLoadState.error
                item {
                    ErrorItem(
                        message = error.localizedMessage ?: "",
                        onClick = { characters.retry() }
                    )
                }
            }
            else -> {}
        }
    }
}

@Composable
fun CharacterItem(
    item: Character
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .size(240.dp),
                model = item.image,
                contentDescription = null
            )
            Text(
                text = item.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.paddingFromBaseline(
                    top = 24.dp,
                    bottom = 8.dp
                )
            )
            Text(
                text = item.species,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = item.gender,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = item.status,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 2,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color.Red
        )
        Button(
            onClick = onClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Try again!")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ItemPreview() {
    RickyMortyAppTheme {
        CharacterItem(
            item = Character(
                id = 1,
                name = "Rick Sanchez",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                status = "Alive",
                gender = "Male",
                species = "Human"
            )
        )
    }
}

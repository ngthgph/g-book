package com.example.bookstore.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookstore.data.local.MockData
import com.example.bookstore.data.model.BookStoreUiState
import com.example.bookstore.ui.utils.Screen
import com.example.bookstore.ui.theme.BookStoreTheme

@Composable
fun RailScreen(
    uiState: BookStoreUiState,
    onIconClick: (Screen) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
    ) {
        Row(
            modifier = Modifier
                .padding(it)
        ) {
            AppNavigationRail(
                currentScreen = uiState.currentScreen,
                onIconClick = onIconClick
            )
            Column(
                modifier = modifier
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .fillMaxSize()
            ) {
                AppHeaderBar(
                    uiState = uiState,
                    onIconClick = onIconClick,
                    onBack = onBack)
                content()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun RailScreenPreview() {
    BookStoreTheme {
        RailScreen(
            uiState = MockData.categoriesUiState,
            onIconClick = {}, onBack = { /*TODO*/ }) {}
    }
}
package com.example.gbook.ui.screens.book

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.GBookViewModel
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun ListDetailHandler(
    navigationType: NavigationType,
    viewModel: GBookViewModel,
    uiState: GBookUiState,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier,
    isLibrary: Boolean = false,
    content: @Composable () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        if(uiState.currentBook == null) {
            content()
        } else {
            BackHandler { viewModel.onBackFromBookDetail() }
            if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
                Row (modifier = Modifier.fillMaxSize()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        //                AdsBanner()
                        content()
                    }
                    BookDetailScreen(
                        navigationType = navigationType,
                        viewModel = viewModel,
                        uiState = uiState,
                        isLibrary = isLibrary,
                        onFunction = onFunction,
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                BookDetailScreen(
                    navigationType = navigationType,
                    viewModel = viewModel,
                    uiState = uiState,
                    isLibrary = isLibrary,
                    onFunction = onFunction,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
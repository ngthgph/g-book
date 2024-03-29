package com.example.gbook.ui.screens.book

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.fake.FakeDataSource.fakeViewModel
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.fake.MockData.fakeOnFunction
import com.example.gbook.ui.GBookViewModel
import com.example.gbook.ui.items.BookPhoto
import com.example.gbook.ui.items.ButtonCard
import com.example.gbook.ui.items.DrawerBookHeader
import com.example.gbook.ui.theme.GBookTheme

@Composable
fun BookDetailScreen(
    navigationType: NavigationType,
    viewModel: GBookViewModel,
    uiState: GBookUiState,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    isLibrary: Boolean = false,
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
            DrawerBookHeader(title = uiState.currentBook!!.title)
        }
        BookDetailContent(
            navigationType = navigationType,
            book = uiState.currentBook!!,
            onFunction = onFunction,
            isLibrary = isLibrary,
        )
    }
}

@Composable
fun BookDetailContent(
    navigationType: NavigationType,
    book: Book,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    isLibrary: Boolean = false,
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = book.author,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_small))
                )
                BookDetailCard(
                    navigationType = navigationType,
                    book = book,
                    modifier = modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_large),
                        end = dimensionResource(id = R.dimen.padding_large),
                        top = dimensionResource(id = R.dimen.padding_small),
                        bottom = dimensionResource(id = R.dimen.padding_medium),
                    ),
                )
                val context = LocalContext.current
                DetailsButtonRow(
                    onFunction = onFunction,
                    book = book,
                    context = context,
                    isLibrary = isLibrary,
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(id = R.dimen.padding_large),
                            bottom = dimensionResource(id = R.dimen.padding_large),
                            start = dimensionResource(id = R.dimen.padding_large),
                        )
                )
            }
        }
    }
}
@Composable
fun BookDetailCard(
    navigationType: NavigationType,
    modifier: Modifier = Modifier,
    book: Book,
) {
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            BookPhoto(
                title = book.title,
                imgSrc = book.imageLinks,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            BookDetailInfo(
                book = book,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}
@Composable
fun RailBookDetailInfo(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ){
        Row {
            Column(modifier = Modifier.weight(1f)) {
                BookInfoRow(stringResource(R.string.categories), book.categories)
                BookInfoRow(stringResource(R.string.publisher), book.publisher)
                BookInfoRow(stringResource(R.string.publish_date), book.publishedDate)
            }
            Column(modifier = Modifier.weight(1f)) {
                BookInfoRow(stringResource(R.string.isbn_13), book.isbn13)
                BookInfoRow(stringResource(R.string.isbn_10), book.isbn10)
                BookInfoRow(stringResource(R.string.page), book.pageCount.toString())
            }
        }

        ContentDescription(false,stringResource(R.string.content), book.description)
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness_large))
        val price = book.retailPrice.toString()
        Row {
            Column(modifier = Modifier.weight(1f)) {
                BookInfoRow(
                    stringResource(R.string.price),
                    stringResource(R.string.price_display, price, book.currencyCode),
                )
            }
            Column(modifier = Modifier.weight(1f)){}
        }

    }
}

@Composable
fun BookDetailInfo(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ){
        BookInfoRow(stringResource(R.string.categories), book.categories)
        BookInfoRow(stringResource(R.string.publisher), book.publisher)
        BookInfoRow(stringResource(R.string.publish_date), book.publishedDate)

        BookInfoRow(stringResource(R.string.isbn_13), book.isbn13)
        BookInfoRow(stringResource(R.string.isbn_10), book.isbn10)
        BookInfoRow(stringResource(R.string.page), book.pageCount.toString())
        ContentDescription(true, stringResource(R.string.content), book.description)
        Divider(thickness = dimensionResource(id = R.dimen.divider_thickness_large))
        val price = book.retailPrice.toString()
        BookInfoRow(
            stringResource(R.string.price),
            stringResource(R.string.price_display, price, book.currencyCode),
        )
    }
}
@Composable
fun ContentDescription(
    compact: Boolean,
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Row (
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ){
        Text(
            text = title,
            modifier = Modifier.weight(1f)
        )
        Box(modifier = Modifier
            .weight(if (compact) 2f else 5f)
            .clickable { expanded = !expanded }
        ) {
            Column {
                Text(
                    text = content,
                    maxLines = if (!expanded) 3 else Int.MAX_VALUE,
                    modifier = Modifier
                )
                if(expanded) {
                    Text(text = "")
                }
            }
            Text(
                text = if(!expanded) "...Show More" else "Show Less",
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}
@Composable
fun BookInfoRow(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text = title,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = content,
            modifier = Modifier
                .weight(2f)
        )
    }
}
@Composable
fun DetailsButtonRow(
    modifier: Modifier = Modifier,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    isLibrary: Boolean = false,
    book: Book? = null,
    collection: BookCollection? = null,
    account: Account? = null,
    string: String? = null,
    context: Context? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            LocalContext.current
            val functions =
                if(isLibrary) arrayOf(Function.RemoveFromLibrary, Function.Cart, Function.Share)
                else arrayOf(Function.AddToLibrary, Function.Cart, Function.Share)
            for (function in functions) {
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                ButtonCard(
                    function = function,
                    book = book,
                    context = context,
                    modifier = Modifier.weight(1f),
                    onFunction = onFunction,
                )
            }
        }
    }
}

@Preview
@Composable
fun ContentDescriptionPreview(
) {
    GBookTheme {
        ContentDescription(
            compact = true,
            title = stringResource(R.string.content),
            content = MockData.bookUiState.currentBook!!.description
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun RailBookDetailInfoPreview() {
    GBookTheme {
        RailBookDetailInfo(MockData.bookUiState.currentBook!!)
    }
}
@Preview
@Composable
fun BookDetailInfoPreview() {
    GBookTheme {
        BookDetailInfo(MockData.bookUiState.currentBook!!)
    }
}
@Preview
@Composable
fun BookInfoRowPreview() {
    GBookTheme {
        BookInfoRow(
            title = stringResource(R.string.page),
            content = MockData.bookUiState.currentBook!!.pageCount.toString())
    }
}

@Preview
@Composable
fun ButtonRowPreview() {
    GBookTheme {
        DetailsButtonRow(onFunction = fakeOnFunction)
    }
}

@Preview(showBackground = true)
@Composable
fun CompactBookScreenPreview() {
    GBookTheme {
        BookDetailScreen(
            viewModel = LocalContext.current.fakeViewModel,
            uiState = MockData.bookUiState,
            navigationType = NavigationType.BOTTOM_NAVIGATION,
            onFunction = fakeOnFunction,
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumBookScreenPreview() {
    GBookTheme {
        BookDetailScreen(
            viewModel = LocalContext.current.fakeViewModel,
            uiState = MockData.bookUiState,
            navigationType = NavigationType.NAVIGATION_RAIL,
            onFunction = fakeOnFunction,
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ExpandedBookScreenPreview() {
    GBookTheme {
        BookDetailScreen(
            viewModel = LocalContext.current.fakeViewModel,
            uiState = MockData.bookUiState,
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
            onFunction = fakeOnFunction,
        )
    }
}
package com.example.gbook.ui.screens.account

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.gbook.R
import com.example.gbook.data.database.account.Account
import com.example.gbook.data.database.books.Book
import com.example.gbook.data.database.collection.BookCollection
import com.example.gbook.data.fake.MockData
import com.example.gbook.data.fake.MockData.fakeOnFunction
import com.example.gbook.data.model.GBookUiState
import com.example.gbook.ui.items.ClickableText
import com.example.gbook.ui.items.DescriptionButton
import com.example.gbook.ui.items.InputTextField
import com.example.gbook.ui.theme.GBookTheme
import com.example.gbook.ui.utils.Function
import com.example.gbook.ui.utils.NavigationType

@Composable
fun AccountScreen(
    uiState: GBookUiState,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier,
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
) {
    AccountContent(
        onFunction = onFunction,
        navigationType = navigationType,
        modifier = modifier,
    )
}

@Composable
fun AccountContent(
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    navigationType: NavigationType,
    modifier: Modifier = Modifier
) {
    var padding = dimensionResource(id = R.dimen.padding_large)
    if(navigationType != NavigationType.BOTTOM_NAVIGATION) {
        padding = dimensionResource(id = R.dimen.padding_extra_large)
    }
    Surface(
        shadowElevation = dimensionResource(id = R.dimen.elevation),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)),
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        SignInScreen(
            navigationType = navigationType,
            onFunction = onFunction,
        )
    }
}

@Composable
fun SignInScreen(
    navigationType: NavigationType,
    onFunction: (Function, Book?, BookCollection?, Account?, String?, Context?) -> Unit,
    modifier: Modifier = Modifier
) {
    var padding = dimensionResource(id = R.dimen.padding_small)
    var padding_medium = dimensionResource(id = R.dimen.padding_medium)
    var padding_large = dimensionResource(id = R.dimen.padding_large)
    if(navigationType != NavigationType.BOTTOM_NAVIGATION) {
        padding = dimensionResource(id = R.dimen.padding_medium)
        padding_medium = dimensionResource(id = R.dimen.padding_large)
        padding_large = dimensionResource(id = R.dimen.padding_extra_large)
    }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.sign_in_to_your_account),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(padding_medium)
        )
        Divider()
        Column(
            verticalArrangement = Arrangement.spacedBy(padding_medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding_large)
                .sizeIn(maxWidth = dimensionResource(id = R.dimen.text_field)),
        ) {
            InputTextField(
                onInput = {onFunction(Function.Input,null,null,null,it,null)},
                placeholder = stringResource(R.string.email_address),
                modifier = Modifier
                    .sizeIn(maxWidth = dimensionResource(id = R.dimen.text_field) )
            )
            InputTextField(
                onInput = {onFunction(Function.Input,null,null,null,it,null)},
                placeholder = "Password"
            )
            Text(
                text = "Please register or login to be able to save your own data",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(horizontal = padding)
            )
            DescriptionButton(
                function = Function.SignIn,
                onFunction = onFunction,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            ClickableText(
                function = Function.ForgetPassword,
                onFunction = onFunction,
            )
            Text(text = " / ")
            ClickableText(
                function = Function.SignUp,
                onFunction = onFunction,
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CompactAccountScreenPreview() {
    GBookTheme {
        AccountScreen(
            uiState = MockData.accountUiState,
            onFunction = fakeOnFunction
        )
    }
}
@Preview(showBackground = true, widthDp = 700)
@Composable
fun MediumAccountScreenPreview() {
    GBookTheme {
        AccountScreen(
            uiState = MockData.accountUiState,
            navigationType = NavigationType.NAVIGATION_RAIL,
            onFunction = fakeOnFunction
        )
    }
}
package com.pats1337.vksynergy


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SignHost(config: SignConfig) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = config.iconResourceId),
            contentDescription = "icon",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 100.dp)
                .size(80.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = config.welcomeText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .height(72.dp)
                .padding(top = 24.dp, start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            onClick = { config.onButtonClick.invoke() }
        ) {
            Text(
                text = stringResource(id = config.buttonTextId),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
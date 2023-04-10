package ca.josue.homefinder.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ca.josue.homefinder.R
import ca.josue.homefinder.presentation.components.CircularProgressBar
import ca.josue.homefinder.ui.theme.HomeFinderTheme
import ca.josue.homefinder.ui.theme.dimensions
import timber.log.Timber

/**
 * created by Josue Lubaki
 * date : 2023-04-06
 * version : 1.0.0
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreenRoute(
    windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    viewModel: LoginViewModel,
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val errorMessage = rememberSaveable { mutableStateOf<String?>(null) }

    val onLoginPressed: (String, String) -> Unit = { username, password ->
        viewModel.onLogin(username, password)
    }

    LaunchedEffect(key1 = state){
        when(state) {
            is LoginState.Success -> {
                onNavigateToHome()
            }
            is LoginState.Error -> {
                errorMessage.value = (state as LoginState.Error).exception.message
            }
            else -> Unit
        }
    }

    // show Snackbar when errorMessage is not null
    if (errorMessage.value != null) {
        val scaffoldState: ScaffoldState = rememberScaffoldState()

        Scaffold(scaffoldState = scaffoldState) {
            LaunchedEffect(key1 = scaffoldState){
                scaffoldState.snackbarHostState.showSnackbar(
                    message = errorMessage.value!!,
                    actionLabel = "Do something"
                )
            }
        }
    }

    LoginScreen(
        state = state,
        onLoginPressed = onLoginPressed,
        onRegisterPressed = onNavigateToRegister
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LoginScreen(
    state : LoginState,
    onLoginPressed: (username : String, password : String) -> Unit,
    onRegisterPressed: () -> Unit,
) {
    val gradientColors = listOf(
        Color(0xFF182233).copy(alpha = 0.4f),
        Color(0xFF080F1A).copy(alpha = 0.4f),
    )
    val scrollState = rememberScrollState()
    val usernameValue = rememberSaveable { mutableStateOf("josuelubaki") }
    val passwordValue = rememberSaveable { mutableStateOf("Josue2022@") }
    val isPasswordValid = remember(passwordValue.value) { passwordValue.value.length >= 6 }
    val isFormValid = remember(usernameValue.value, passwordValue.value) {
        usernameValue.value.isNotEmpty() && isPasswordValid
    }

    Image(
        painter = painterResource(id = R.drawable.login4),
        contentDescription = "Logo image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors
                )
            ),
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .imePadding()
                .imeNestedScroll()
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            Column(
                modifier = Modifier.padding(top = 220.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.medium),
            ) {
                Text(
                    modifier = Modifier.padding(bottom = MaterialTheme.dimensions.extraLarge),
                    text = stringResource(R.string.home_finder),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Bold
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.dimensions.large,
                            vertical = MaterialTheme.dimensions.medium
                        ),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimensions.medium),
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = stringResource(id = R.string.username))
                        },
                        value = usernameValue.value,
                        onValueChange = usernameValue::value::set,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                        shape = CircleShape
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = stringResource(id = R.string.password))
                        },
                        value = passwordValue.value,
                        onValueChange = passwordValue::value::set,
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                        shape = CircleShape
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.dimensions.medium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(MaterialTheme.dimensions.medium)
                            .height(MaterialTheme.dimensions.semiXLarge)
                        ,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF9219),
                            contentColor = Color.Black
                        ),
                        onClick = { onRegisterPressed() }
                    ) {
                        Text(
                            text = stringResource(R.string.register).uppercase(),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black.copy(alpha = 0.8f),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = MaterialTheme.dimensions.medium)
                            .weight(.3f)
                            .height(MaterialTheme.dimensions.xLarge),
                        onClick = { onLoginPressed(
                            usernameValue.value,
                            passwordValue.value
                        ) },
                        enabled = isFormValid,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowCircleRight,
                            contentDescription = null,
                            tint =
                            if (isFormValid) Color(0xFF12B32D)
                            else Color(0xFFB2BBB4),
                            modifier = Modifier.size(MaterialTheme.dimensions.xLarge)
                        )
                    }
                }

                CircularProgressBar(state is LoginState.Loading)
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    HomeFinderTheme {
        LoginScreen(
            state = LoginState.Idle,
            onLoginPressed = {_,_ -> },
            onRegisterPressed = { }
        )
    }
}
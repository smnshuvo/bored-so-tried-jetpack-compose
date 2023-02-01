package shouman.iss.bored.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shouman.iss.bored.ui.activity.viewModel.HomePageViewModel
import shouman.iss.bored.ui.theme.BoredTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import shouman.iss.bored.data.entity.BoredDataEntity
import shouman.iss.bored.ui.theme.Teal200

class HomePageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BoredTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BoredRow()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoredRow(label: String = "label", data: String = "data") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = label, color = Color.Blue, modifier = Modifier
                .padding(6.dp)
        )
        Text(
            text = data, color = Color.Gray, modifier = Modifier
                .padding(6.dp)
        )
    }
}


@Composable
fun BoredCard(entity: BoredDataEntity, viewModel: HomePageViewModel) {

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp,

        ) {
        Column() {
            Text(
                text = "Try this activity!!!",
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom =  10.dp)
                    .background(color = Color.Gray)
            )
            BoredRow(label = "Activity", entity.activity)
            BoredRow(label = "Type", entity.type)
            BoredRow(label = "Key", entity.key)
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                BoredButton(viewModel = viewModel, "Still bored!")
            }

        }
    }

}


@Composable
fun BoredButton(viewModel: HomePageViewModel, buttonLabel: String) {
    Button(
        onClick = {
            viewModel.getBoredEntity()
        }) {
        Text(text = buttonLabel)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel: HomePageViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val animateScale by animateFloatAsState(
        targetValue = if (uiState.isLoading) 3F else 1F,
        animationSpec = tween(durationMillis = 500, delayMillis = 0, easing = LinearOutSlowInEasing)
    )

    BoredTheme {
        Surface(
            shape = RoundedCornerShape(8),
            elevation = 12.dp,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessLow
                    )

                )
                .scale(scale = animateScale)
                .padding(12.dp)


        )

        {
            if (uiState.initialState && !uiState.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BoredButton(viewModel, "I am bored")
                }
            }
            if (uiState.boredDataEntity != null && !uiState.isLoading) {
                BoredCard(
                    viewModel = viewModel,
                    entity = uiState.boredDataEntity!!
                )
            }
            if (uiState.isLoading) {
                Column(
                    verticalArrangement = Arrangement.Center, modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center, modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        CircularProgressIndicator(color = Color.Black)
                    }
                }


            }


        }


    }
}
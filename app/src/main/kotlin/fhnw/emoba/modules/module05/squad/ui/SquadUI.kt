package fhnw.emoba.modules.module05.squad.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.emoba.modules.module05.squad.data.Member
import fhnw.emoba.modules.module05.squad.data.Squad
import fhnw.emoba.modules.module05.squad.viewmodel.SquadModel

@Composable
fun SquadUI(viewModel: SquadModel) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primaryContainer = Color(0xFFAD1C00)
        )
    ) {
        Scaffold(topBar = { Bar(viewModel.title) }) {
            Body(viewModel.squad, it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(title: String) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
private fun Body(squad: Squad, paddingValues: PaddingValues) {
    with(squad) {
        ConstraintLayout(Modifier
            .padding(paddingValues)
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxSize())
        {
            val (squadname,
                 homeTownLabel,   homeTownValue,
                 formedLabel,     formedLabelValue,
                 secretBaseLabel, secretBaseValue,
                 activeLabel,     activeValue,
                 membersLabel,
                 membersList) = createRefs()
            Text(text     = squadName,
                 style    = MaterialTheme.typography.headlineLarge,
                 modifier = Modifier.constrainAs(squadname) {
                     //centerHorizontallyTo(parent)
                     start.linkTo(parent.start)
                     top.linkTo(parent.top, margin = 20.dp)
                 })
            
            Text(text     = "Home Town",
                 style    = MaterialTheme.typography.bodyLarge,
                 modifier = Modifier.constrainAs(homeTownLabel) {
                     start.linkTo(parent.start)
                     top.linkTo(squadname.bottom, margin = 20.dp)
                 })
            Text(text     = homeTown,
                 style    = MaterialTheme.typography.bodyLarge,
                 modifier = Modifier.constrainAs(homeTownValue) {
                     start.linkTo(homeTownLabel.end, margin = 30.dp)
                     centerVerticallyTo(homeTownLabel)
                 })
            Text(text     = "Formed",
                 modifier = Modifier.constrainAs(formedLabel) {
                     start.linkTo(homeTownLabel.start)
                     top.linkTo(homeTownLabel.bottom, margin = 10.dp)
            })
            Text(text     = "%d".format(formed),
                 modifier = Modifier.constrainAs(formedLabelValue) {
                     start.linkTo(homeTownValue.start)
                     centerVerticallyTo(formedLabel)
            })
            Text(text     = "Secret Base",
                 modifier = Modifier.constrainAs(secretBaseLabel) {
                     start.linkTo(homeTownLabel.start)
                     top.linkTo(formedLabel.bottom, margin = 10.dp)
            })
            Text(text     = secretBase,
                 modifier = Modifier.constrainAs(secretBaseValue) {
                     start.linkTo(homeTownValue.start)
                     centerVerticallyTo(secretBaseLabel)
            })
            Text(text = "Active",
                 modifier = Modifier.constrainAs(activeLabel) {
                     start.linkTo(homeTownLabel.start)
                     top.linkTo(secretBaseLabel.bottom, margin = 10.dp)
            })
            Text(text     = if (active) "Yes" else "No",
                 modifier = Modifier.constrainAs(activeValue) {
                     start.linkTo(homeTownValue.start)
                     centerVerticallyTo(activeLabel)
            })
            Text(text     = "Members",
                 modifier = Modifier.constrainAs(membersLabel) {
                     start.linkTo(homeTownLabel.start)
                     top.linkTo(activeLabel.bottom, margin = 10.dp)
            })
            
            MemberList(members, Modifier.constrainAs(membersList) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(membersLabel.bottom, 10.dp)
                bottom.linkTo(parent.bottom, 10.dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            })
        }
    }
}

@Composable
private fun MemberList(members : List<Member>, modifier: Modifier){
    val scrollState = rememberScrollState()                  //das macht die Column scrollable
    LaunchedEffect(Unit) { // animation when the composable is first displayed
        scrollState.animateScrollBy(value = 500f, animationSpec = tween(durationMillis = 600))
        scrollState.animateScrollBy(value = -500f, animationSpec = tween(durationMillis = 800))
    }
    Column(modifier = modifier.verticalScroll(scrollState)) {
        members.forEach {MemberCard(it) }
    }
}

@Composable
private fun MemberCard(member: Member) {
    Card(Modifier.padding(20.dp)) {
        Column(modifier            = Modifier
            .fillMaxWidth()
            .padding(25.dp),
               verticalArrangement = Arrangement.spacedBy(5.dp)) {
            with(member) {
                Text(text  = "$name, $age",
                     style = MaterialTheme.typography.titleLarge,
                     modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(text      = secretIdentity,
                     style     = MaterialTheme.typography.bodyMedium,
                     fontStyle = FontStyle.Italic)
                Text(text     = powers.joinToString(", "),
                     modifier = Modifier.fillMaxWidth(),
                     style    = MaterialTheme.typography.bodyMedium,
                     maxLines = 2,
                     overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Preview
@Composable
private fun MemberCardPreview(){
    val member = Member(name           = "Molecule Man",
                        age            = 29,
                        secretIdentity = "Dan Jukes",
                        powers         = listOf("Radiation resistance",
                                                "Turning tiny",
                                                "Radiation blast"))
    MaterialTheme(colorScheme = lightColorScheme(primary = Color(0xFFF3B423),
                                       surface = Color(0xFFFFFCED))
    ) {
        MemberCard(member)
    }
}
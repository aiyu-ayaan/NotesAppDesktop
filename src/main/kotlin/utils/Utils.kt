import kotlin.random.Random

data class Note(
    val title: String,
    val des: String,
    val created: Long = 0L
)


private val paragraph = """
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam fringilla arcu eget urna tincidunt accumsan. Fusce sapien leo,
facilisis vitae vehicula at, accumsan nec erat. Fusce ut hendrerit purus, a venenatis purus. Aliquam viverra, turpis id ultricies 
scelerisque, lacus velit pharetra velit, eget convallis risus arcu ut neque. Cras vitae dignissim risus, quis iaculis sem. Phasellus 
maximus venenatis laoreet. Pellentesque augue urna, condimentum at imperdiet at, auctor ac risus. Donec sit amet malesuada nulla.

Donec pellentesque massa in est volutpat consequat. Suspendisse at justo nec odio facilisis posuere. Ut neque 
magna, dapibus nec iaculis a, finibus sed lorem. Proin euismod imperdiet urna vitae efficitur. Vivamus scelerisque
 interdum imperdiet. Integer tellus massa, facilisis nec nulla quis, condimentum placerat nunc. Donec leo urna, 
 tempus posuere suscipit sed, lobortis at mi. Vivamus porttitor auctor purus, non bibendum sem lacinia at. Morbi 
 ultricies arcu non dolor dictum, non fermentum lorem egestas. Nam maximus erat nisl, sed aliquet ante lacinia quis.
  Aliquam tortor nunc, consectetur dignissim quam vitae, pellentesque vestibulum diam. Donec pretium nunc ac mi suscipit
   efficitur non ut erat. Donec lectus neque, blandit in molestie in, aliquet eget nulla. Sed nec nibh orci. Sed ullamcorper 
   sem quis lorem ultrices, ac fringilla diam egestas. Phasellus fermentum nulla a elit vestibulum ornare.
""".trimIndent()


val notesList = (1..101).toList()
    .map {
        Note(
            title = "Note $it",
            des = paragraph.take(
                Random.nextInt(100, paragraph.length)
            )
        )
    }
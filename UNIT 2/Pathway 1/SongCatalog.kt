fun main() {
    val song1 = Song("Bohemian Rhapsody", "Queen", 1975, 1000000)
    val song2 = Song("Stairway to Heaven", "Led Zeppelin", 1971, 999)
    val song3 = Song("Hotel California", "Eagles", 1977, 5000)
    
    song1.printSongDescription()
    song2.printSongDescription()
    song3.printSongDescription()
    
    println("Is ${song1.title} popular? ${song1.popular}")
    println("Is ${song2.title} popular? ${song2.popular}")
    println("Is ${song3.title} popular? ${song3.popular}")
}


class Song (
    var title: String,
    var artist: String,
    var yearPublished: Int,
    var playCount: Int
){
    
    var popular: Boolean = false
        get() = playCount >= 1000    

    fun printSongDescription() {
        println("$title, performed by $artist, was released in $yearPublished.")
    }
}
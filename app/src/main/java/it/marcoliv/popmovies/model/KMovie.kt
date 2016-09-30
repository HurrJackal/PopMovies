package it.marcoliv.popmovies.model


/**
 * Created by marcoliv on 9/27/2016.
 * This is a POJO writed in Kotlin all methods (getter, setter, costructors) are embedded
 * For correct JSON parsing it's needed to mantain the same parameter name in the class, like
 * the JSON in the response. This procedure is needed for the correct mapping.
 * Otherwise if you use Gson you can insert the annotation to mapping JSON fields in the POJO.
 * ex.
 *      @SerializedName("poster_path")
 *      @Expose
 *      public String posterPath;
 */

data class KMovie(  val poster_path: String,
                    val adult: Boolean,
                    val overview: String,
                    val release_date: String,
                    val genre_ids: List<Int>,
                    val id: Int,
                    val original_title: String,
                    val original_language: String,
                    val title: String,
                    val backdrop_path: String,
                    val popularity: Float,
                    val vote_count: Int,
                    val video: Boolean,
                    val vote_average: Float  )
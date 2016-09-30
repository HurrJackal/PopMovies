package it.marcoliv.popmovies.model


/**
 * Created by marcoliv on 9/27/2016.
 * This is a POJO writed in Kotlin all methods (getter, setter, costructors) are embedded
 * For correct JSON parsing it's needed to mantain the same parameter name in the class, like
 * the JSON in the response. This procedure is needed for the correct mapping.
 * Otherwise if you use Gson you can insert the annotation to mapping JSON fields in the POJO.
 * ex.
 *      @SerializedName("results")
 *      @Expose
 *      public List<KMovie> results = new ArrayList<KMovie>();
 */
data class KMovies (    val page: String,
                        val results: List<KMovie>,
                        val total_results: String,
                        val total_pages: String )
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class HelloWorld : DefaultTask() {
    @TaskAction
    fun sayHello() {
        val foo = if (project.hasProperty("foo")) project.property("foo") else null
        println("Hello, World: ${foo ?: "error"}")
        bye()
    }

    private fun bye() {
        println("Bb")
    }
}
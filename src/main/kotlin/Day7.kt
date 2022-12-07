import java.util.*
import kotlin.math.max

class Day7 {

    companion object {

        fun puzzleA(data: List<String>): Int {
            val root = Dir("\\")
            parseFileSystem(data, root)

            val smallFolders = root.getDirsWithMaxSize(100000)
            println(root.getDirsWithMaxSize(100000).map { it.name })

            return smallFolders.sumOf { it.getSize() }
        }

        private fun parseFileSystem(data: List<String>, root: Dir) {
            var currentDir = root
            for (line in data.slice(1 until data.size)) {
                val oper = line.split(" ")
                val listing = false
                var parent = root

                if (oper[0] == "$") {               // executing a command
                    if (oper[1] == "cd") {          // changing dir
                        if (oper[2] == "..") {      // moving back to parent
                            currentDir = currentDir?.parent ?: root
                        } else {                    // going down one level
                            val newDir = Dir(oper[2],currentDir)
                            currentDir.dirs.add(newDir)
                            currentDir = newDir
                        }
                    } else if (oper[1] == "ls") {   // starting a list
                    }
                } else {                            // not a command, receiving a list
                    if (oper[0] == "dir") {
                        // ignore
                    } else {
                        currentDir.files.add(File(oper[1], oper[0].toInt()))
                    }
                }
            }
        }

        fun puzzleB(data: List<String>): Int {
            val root = Dir("\\")
            parseFileSystem(data, root)

            val totalSpace = 70_000_000
            val required = 30_000_000
            val currentFree = totalSpace - root.getSize()

            val smallFolders = root.getDirsWithMinSize(required-currentFree)
            return smallFolders.sortedBy { it.getSize() }.first().getSize()
        }
    }
}

class Dir(val name: String, val parent: Dir? = null, val dirs: MutableList<Dir> = mutableListOf(), val files: MutableList<File> = mutableListOf())
{
    fun getSize(): Int = (files.sumOf { it.size } + dirs.sumOf { it.getSize() })
    fun getDirsWithMaxSize(maxSize: Int): List<Dir> {
        val result = mutableListOf<Dir>()

        if (getSize() <= maxSize) {
            result.add(this)
        }
        result.addAll(dirs.map{ it.getDirsWithMaxSize(maxSize) }.flatten())
        return result
    }

    fun getDirsWithMinSize(minSize: Int): List<Dir> {
        val result = mutableListOf<Dir>()

        if (getSize() >= minSize) {
            result.add(this)
        }
        result.addAll(dirs.map{ it.getDirsWithMinSize(minSize) }.flatten())
        return result
    }
    override fun toString(): String {
        return "- $name (dir)\n" + dirs.joinToString{ it.toString() } + files.joinToString { it.toString() }
    }
}
class File(val name: String, val size: Int)
{
    override fun toString(): String {
        return "- $name (file, size=$size)\n"
    }
}
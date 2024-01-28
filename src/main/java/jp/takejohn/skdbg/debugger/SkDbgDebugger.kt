package jp.takejohn.skdbg.debugger

import ch.njol.skript.config.Config
import ch.njol.skript.lang.SyntaxElement
import ch.njol.skript.lang.TriggerItem
import org.bukkit.event.Event
import org.bukkit.util.Consumer
import org.skriptlang.skript.lang.debug.Debugger
import java.util.HashMap
import java.util.WeakHashMap

class SkDbgDebugger : Debugger {

    private val lines = WeakHashMap<SyntaxElement, Int>()

    private val actions = HashMap<ScriptPoint, Consumer<Event>>()

    override fun onParse(element: SyntaxElement) {
        val node = element.parser.node
        if (node !== null) {
            lines[element] = node.line
        }
    }

    override fun onWalk(triggerItem: TriggerItem, event: Event) {
        if (triggerItem !is SyntaxElement) {
            return
        }
        val config: Config? = triggerItem.trigger?.script?.config
        val line: Int? = lines[triggerItem]
        if (config !== null && line !== null) {
            actions[ScriptPoint(config, line)]?.accept(event)
        }
    }

}

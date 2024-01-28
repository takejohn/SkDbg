package jp.takejohn.skdbg

import ch.njol.skript.Skript
import jp.takejohn.skdbg.debugger.SkDbgDebugger
import org.bukkit.plugin.java.JavaPlugin
import org.skriptlang.skript.lang.debug.Debuggers

class SkDbg : JavaPlugin() {

    private var debugger: SkDbgDebugger? = null

    override fun onEnable() {
        Skript.registerAddon(this)
        debugger = SkDbgDebugger()
        Debuggers.attachDebugger(debugger)
    }

    override fun onDisable() {
        if (debugger !== null) {
            Debuggers.detachDebugger(debugger)
        }
    }

}

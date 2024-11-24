package xiaoxiao.ciallo.Logger;

import org.bukkit.plugin.Plugin;
import xiaoxiao.ciallo.Ciallo;

import java.util.logging.Level;

public class Logger {
    private static final Plugin plugin = Ciallo.getPlugin(Ciallo.class);
    public static void Log(String log, LogType e){
        if(e == null) return;
        switch (e){
            case LogType.Debug -> plugin.getLogger().log(Level.FINE,"[Loader] "+log);
            case LogType.Info -> plugin.getLogger().log(Level.INFO,log);
            case LogType.Warning -> plugin.getLogger().log(Level.WARNING,log);
            case LogType.Error -> plugin.getLogger().log(Level.SEVERE,log);
        }
    }
}

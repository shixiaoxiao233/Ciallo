package xiaoxiao.ciallo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xiaoxiao.ciallo.Logger.LogType;
import xiaoxiao.ciallo.Logger.Logger;

public final class Ciallo extends JavaPlugin implements Listener {
    private String chatSuffix;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger.Log("Ciallo～(∠・ω< )⌒★",LogType.Debug);
        saveDefaultConfig();
        chatSuffix = getConfig().getString("chat-suffix", " (∠・ω< )⌒★");
        getServer().getPluginManager().registerEvents(this, this);
        Logger.Log("XiaoxiaoPlugin 已启用",LogType.Info);
        getCommand("ciallo").setTabCompleter(new CialloTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger.Log("XiaoxiaoPlugin 已禁用",LogType.Info);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String originalMessage = event.getMessage();
        event.setMessage(originalMessage + chatSuffix);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ciallo")) {
            if (!sender.hasPermission("XiaoxiaoPlugin.*")) {
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("XiaoxiaoPlugin.reload")) {
                    sender.sendMessage("§c哎哟，丢失了重载权限！");
                    return true;
                }

                reloadConfig();
                chatSuffix = getConfig().getString("chat-suffix", " (∠・ω< )⌒★");

                sender.sendMessage("§a重载完毕喵！");
                getLogger().info("重载完毕喵！");
                return true;
            }
        }

        return false;
    }
}

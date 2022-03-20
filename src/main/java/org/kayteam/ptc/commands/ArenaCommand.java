package org.kayteam.ptc.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kayteam.api.input.inputs.BlockBreakInput;
import org.kayteam.api.world.WorldUtil;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.arena.Arena;
import org.kayteam.ptc.game.TeamColour;
import org.kayteam.ptc.util.PermissionChecker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArenaCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0){
            switch(args[0].toLowerCase()){
                case "list":{
                    if(PermissionChecker.check(sender, "ptc.cmd.arena.list")) {

                    }
                }
                case "info":{
                    if(PermissionChecker.check(sender, "ptc.cmd.arena.info")) {
                        if (args.length > 1) {
                            String arenaName = args[1];
                            if (PTC.getArenaManager().isArena(arenaName)) {
                                Arena arena = PTC.getArenaManager().getArena(arenaName);
                                // todo arena info menu
                            }
                        }
                    }
                }
                case "edit":{
                    if(sender instanceof Player) {
                        if (args.length > 1) {
                            Player player = (Player) sender;
                            String arenaName = args[1];
                            if(PTC.getArenaManager().isArena(arenaName)){
                                Arena arena = PTC.getArenaManager().getArena(arenaName);
                                if(args.length > 2){
                                    switch (args[2].toLowerCase()){
                                        case "setspawn":{
                                            if(PermissionChecker.check(player, "ptc.cmd.arena.setspawn")){
                                                if(args.length > 3){
                                                    try{
                                                        TeamColour teamColour = TeamColour.valueOf(args[3].toUpperCase());
                                                        Location teamSpawnLocation = player.getLocation();
                                                        arena.getSpawnLocations().put(teamColour, teamSpawnLocation);
                                                        PTC.getArenaManager().saveArena(arena);
                                                        PTC.messages.sendMessage(player, "arenaSpawnSetted", new String[][]{{"%arenaName%", arenaName}, {"%teamColour%", teamColour.toString()}});
                                                    }catch (IllegalArgumentException e){
                                                        PTC.messages.sendMessage(player, "invalidTeamColour");
                                                    }
                                                }else{
                                                    PTC.messages.sendMessage(player, "invalidArguments", new String[][]{{"%usage%", "arena edit <arenaName> setcore RED/BLUE/GREEN/YELLOW"}});
                                                }
                                            }
                                            break;
                                        }
                                        case "setwaitinglobby":{
                                            if(PermissionChecker.check(player, "ptc.cmd.arena.setwaitinglobby")){
                                                Location waitingLobby = player.getLocation();
                                                arena.setWaitingLobby(waitingLobby);
                                                PTC.getArenaManager().saveArena(arena);
                                                PTC.messages.sendMessage(player, "arenaWaitingLobbySetted", new String[][]{{"%arenaName%", arenaName}});
                                            }
                                            break;
                                        }
                                        case "setshop":{
                                            if(PermissionChecker.check(player, "ptc.cmd.arena.setshop")){
                                                if(args.length > 3){
                                                    try{
                                                        TeamColour teamColour = TeamColour.valueOf(args[3].toUpperCase());
                                                        PTC.messages.sendMessage(player, "breakBlockInput");
                                                        PTC.getInputManager().addInput(player, new BlockBreakInput() {
                                                            @Override
                                                            public boolean onBlockBreak(Player player, BlockBreakEvent event) {
                                                                event.setCancelled(true);
                                                                Location shopLocation = event.getBlock().getLocation();
                                                                arena.getShopLocations().put(teamColour, shopLocation);
                                                                PTC.getArenaManager().saveArena(arena);
                                                                PTC.messages.sendMessage(player, "arenaShopLocationSetted", new String[][]{{"%arenaName%", arenaName}, {"%teamColour%", teamColour.toString()}});
                                                                return true;
                                                            }

                                                            @Override
                                                            public void onPlayerSneak(Player player) {}
                                                        });
                                                    }catch (IllegalArgumentException e){
                                                        PTC.messages.sendMessage(player, "invalidTeamColour");
                                                    }
                                                }else{
                                                    PTC.messages.sendMessage(player, "invalidArguments", new String[][]{{"%usage%", "arena edit <arenaName> setcore RED/BLUE/GREEN/YELLOW"}});
                                                }
                                            }
                                            break;
                                        }
                                        case "setcore":{
                                            if(PermissionChecker.check(player, "ptc.cmd.arena.setcore")){
                                                if(args.length > 3){
                                                    try{
                                                        TeamColour teamColour = TeamColour.valueOf(args[3].toUpperCase());
                                                        PTC.messages.sendMessage(player, "breakBlockInput");
                                                        PTC.getInputManager().addInput(player, new BlockBreakInput() {
                                                            @Override
                                                            public boolean onBlockBreak(Player player, BlockBreakEvent event) {
                                                                event.setCancelled(true);
                                                                Location teamCoreLocation = event.getBlock().getLocation();
                                                                arena.getCoreLocations().put(teamColour, teamCoreLocation);
                                                                PTC.getArenaManager().saveArena(arena);
                                                                PTC.messages.sendMessage(player, "arenaShopLocationSetted", new String[][]{{"%arenaName%", arenaName}, {"%teamColour%", teamColour.toString()}});
                                                                return true;
                                                            }

                                                            @Override
                                                            public void onPlayerSneak(Player player) {}
                                                        });

                                                    }catch (IllegalArgumentException e){
                                                        PTC.messages.sendMessage(player, "invalidTeamColour");
                                                    }
                                                }else{
                                                    PTC.messages.sendMessage(player, "invalidArguments", new String[][]{{"%usage%", "arena edit <arenaName> setcore RED/BLUE/GREEN/YELLOW"}});
                                                }
                                            }
                                            break;
                                        }
                                        case "setmaxteamplayers":{
                                            if(PermissionChecker.check(player, "ptc.cmd.arena.setmaxteamplayers")){
                                                if(args.length > 3){
                                                    try{
                                                        int maxPlayers = Integer.parseInt(args[3]);
                                                        arena.setMaxTeamPlayers(maxPlayers);
                                                        PTC.getArenaManager().saveArena(arena);
                                                        PTC.messages.sendMessage(player, "arenaMaxTeamPlayersSetted", new String[][]{
                                                                {"%arenaName%", arenaName},
                                                                {"%maxTeamPlayers%", args[3]}
                                                        });
                                                    }catch (Exception e){
                                                        PTC.messages.sendMessage(player, "argumentMustBeNumber");
                                                    }
                                                }else{
                                                    PTC.messages.sendMessage(player, "invalidArguments", new String[][]{{"%usage%", "arena edit <arenaName> setmaxteamplayers <maxTeamPlayers>"}});
                                                }
                                            }
                                            break;
                                        }
                                        case "setdefaultkit":{
                                            if(PermissionChecker.check(player, "ptc.cmd.arena.setdefaultkit")){
                                                if(args.length > 3) {
                                                    try {
                                                        TeamColour teamColour = TeamColour.valueOf(args[3].toUpperCase());
                                                        arena.getDefaultKit().clear();
                                                        int slot = 0;
                                                        for(ItemStack itemStack : player.getInventory().getContents()){
                                                            arena.getDefaultKit().get(teamColour).put(slot, itemStack);
                                                            slot++;
                                                        }
                                                        PTC.getArenaManager().saveArena(arena);
                                                        PTC.messages.sendMessage(player, "defaultKitSetted", new String[][]{{"%arenaName%", arenaName}});
                                                    }catch (IllegalArgumentException e){
                                                        PTC.messages.sendMessage(player, "invalidTeamColour");
                                                    }
                                                }else {
                                                    PTC.messages.sendMessage(player, "invalidArguments", new String[][]{{"%usage%", "arena edit <arenaName> setdefaultkit RED/BLUE/GREEN/YELLOW"}});
                                                }
                                            }
                                            break;
                                        }
                                        default:{
                                            // todo command help
                                        }
                                    }
                                }
                            }else{
                                PTC.messages.sendMessage(player, "invalidArenaName");
                            }
                        }else{
                            PTC.messages.sendMessage(sender, "invalidArguments", new String[][]{{"%usage%", "arena <info/edit/create/delete/join> <arenaName>"}});
                        }
                    }else{
                        PTC.messages.sendMessage(sender, "onlyPlayerCommand");
                    }
                    break;
                }
                case "create":{
                    if(PermissionChecker.check(sender, "ptc.cmd.arena.create")) {
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            if (args.length > 1) {
                                String arenaName = args[1];
                                if (!PTC.getArenaManager().isArena(arenaName)) {
                                    WorldUtil.createWorldTemplate(player.getWorld(), PTC.getPTC().getDataFolder() + "/arenas", arenaName);
                                    Arena arena = new Arena(arenaName, new File(PTC.getPTC().getDataFolder() + "/arenas/" + arenaName));
                                    PTC.getArenaManager().saveArena(arena);
                                } else {
                                    PTC.messages.sendMessage(sender, "arenaAlreadyExist");
                                }
                            } else {
                                PTC.messages.sendMessage(sender, "invalidArguments", new String[][]{{"%usage%", "arena create <arenaName>"}});
                            }
                        } else {
                            PTC.messages.sendMessage(sender, "onlyPlayerCommand");
                        }
                    }
                    break;
                }
                case "delete":{
                    if(PermissionChecker.check(sender, "ptc.cmd.arena.delete")){
                        if(args.length > 1){
                            String arenaName = args[1];
                            if(!PTC.getArenaManager().isArena(arenaName)){

                            }else{
                                PTC.messages.sendMessage(sender, "invalidArenaName");
                            }
                        }
                    }
                    break;
                }
                case "join":{
                    if(PermissionChecker.check(sender, "ptc.cmd.arena.join")) {
                        if(sender instanceof Player){
                            Player player = (Player) sender;
                            if (args.length > 1) {
                                String arenaName = args[1];
                                if (!PTC.getArenaManager().isArena(arenaName)) {
                                    PTC.getGameManager().joinGame(player, arenaName);

                                }else{
                                    PTC.messages.sendMessage(sender, "invalidArenaName");
                                }
                            }else{
                                PTC.messages.sendMessage(sender, "invalidArguments", new String[][]{{"%usage%", "arena join <arenaName>"}});
                            }
                        }else{
                            PTC.messages.sendMessage(sender, "onlyPlayerCommand");
                        }
                    }
                    break;
                }
                default:{
                    
                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            if(sender.hasPermission("ptc.cmd.arena.list")){
                tabs.add("list");
            }
            if(sender.hasPermission("ptc.cmd.arena.info")){
                tabs.add("info");
            }
            if(sender.hasPermission("ptc.cmd.arena.create")){
                tabs.add("create");
            }
            if(sender.hasPermission("ptc.cmd.arena.delete")){
                tabs.add("delete");
            }
            if(sender.hasPermission("ptc.cmd.arena.join")){
                tabs.add("join");
            }
            if(sender.hasPermission("ptc.cmd.arena.edit")){
                tabs.add("edit");
            }
            return tabs;
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("delete")){
                if(sender.hasPermission("ptc.cmd.arena.delete")){
                    tabs.addAll(PTC.getArenaManager().arenas.keySet());
                }
            }else if(args[0].equalsIgnoreCase("edit")){
                if(sender.hasPermission("ptc.cmd.arena.edit")){
                    tabs.addAll(PTC.getArenaManager().arenas.keySet());
                }
            }else if(args[0].equalsIgnoreCase("join")) {
                if (sender.hasPermission("ptc.cmd.arena.join")) {
                    tabs.addAll(PTC.getArenaManager().arenas.keySet());
                }
            }
            return tabs;
        }
        if(args.length == 3){
            if(args[0].equalsIgnoreCase("edit")){
                if (sender.hasPermission("ptc.cmd.arena.edit")) {
                    tabs.add("setshop");
                    tabs.add("setcore");
                    tabs.add("setwaitinglobby");
                    tabs.add("setspawn");
                    tabs.add("setdefaultkit");
                    tabs.add("setmaxteamplayers");
                }
            }
            return tabs;
        }
        if(args.length == 4){
            if(args[0].equalsIgnoreCase("edit") && (args[2].equalsIgnoreCase("setshop") ||args[2].equalsIgnoreCase("setcore") ||
                    args[2].equalsIgnoreCase("setspawn") || args[2].equalsIgnoreCase("setdefaultkit"))) {
                if (sender.hasPermission("ptc.cmd.arena.edit")) {
                    tabs.add(TeamColour.BLUE.toString());
                    tabs.add(TeamColour.GREEN.toString());
                    tabs.add(TeamColour.RED.toString());
                    tabs.add(TeamColour.YELLOW.toString());
                }
            }
            return tabs;
        }
        return null;
    }
}
